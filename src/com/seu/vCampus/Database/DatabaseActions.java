package com.seu.vCampus.Database;
import com.seu.vCampus.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * @author Jamie , mbh
 * @className DatabaseActions
 * @createdTime 8/27/2019 10:21 AM
 * @description All the functions need to manipulate the database and interact with the server.
 */

public class DatabaseActions {
    private PreparedStatement stmt = null;

    /**
     * Compare course times (including their semesters) in the database and decide whether they conflict.
     * @param cA Course A
     * @param cB Course B
     * @return Boolean type
     */
    private boolean isConflicted(Course cA, Course cB) {
        if(cA.getCourseSemester().equals(cB.getCourseSemester())) {
            for (String elementA : cA.getCourseTime().split(";")
            ) {
                for (String elementB : cB.getCourseTime().split(";")
                ) {
                    if (elementA.equals(elementB)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Compare the user-entered account and password with the ones in the database and modify the passed login object
     * according to the compared result. If the account is not found or the password is not correct, then the login
     * object's message type will be set to FAIL, otherwise SUCCESS.
     *
     * @param conn  Database connection
     * @param login Login message object
     * @throws SQLException Exceptions such as database not found, and other common ones.
     */
    public void validatePassword(Connection conn, Login login) throws SQLException {
        try {
            String sql = "select*from Users where ECardNumber=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login.getECardNumber());
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                String PW = res.getString("PassWord");
                String AL = res.getString("AuthorityNumber");
                if (PW.equals(login.getPassWord())) {
                    login.setAuthorityLevel((short) Integer.parseInt(AL));
                    login.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
                } else login.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            } else {
                login.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            login.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Select one specific course by modifying the student count of this course and adding this course to the selected
     * courses of the specific student who wants to select the course now.
     *
     * @param conn   Database connection
     * @param course Course message object. Should contain the course information and the ECard number.
     * @throws SQLException Common exceptions as well as exceptions related to this specific action, such as maximum
     *                      student capacity reached.
     */
    public void selectCourse(Connection conn, Course course) throws SQLException {
        stmt = conn.prepareStatement("select courseTime, courseSemester from Courses where courseNumber = ?");
        stmt.setString(1,course.getCourseNumber());
        ResultSet timeInfoRes = stmt.executeQuery();
        if(timeInfoRes.next()) {
            course.setCourseTime(timeInfoRes.getString("courseTime"));
            course.setCourseSemester(timeInfoRes.getString("courseSemester"));
        }

        Person per = new Person();
        per.setECardNumber(course.getECardNumber());
        getCoursesSelected(conn,per);
        ArrayList<Course> coursesSelected = per.getCoursesSelected();

        boolean conflict = false;
        for (Course c:coursesSelected
             ) {
            if(isConflicted(c,course)) {
                conflict = true;
                break;
            }
        }

        //Proceed only if no conflict happens.
        if(!conflict) {
            try {
                String sql = "select * from Courses where courseNumber = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCourseNumber());
                ResultSet courseRes = stmt.executeQuery();

                if (courseRes.next()) {
                    if ((courseRes.getInt("maximumStudents") -
                            courseRes.getInt("enrolledStudents")) > 0) {
                        try {
                            sql = "insert into CoursesSelectedStatus values(?,?)";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, course.getECardNumber());
                            stmt.setString(2, course.getCourseNumber());
                            stmt.executeUpdate();

                            sql = "update Courses set enrolledStudents = ? where courseNumber = ?";
                            stmt = conn.prepareStatement(sql);
                            stmt.setInt(1, courseRes.getInt("enrolledStudents") + 1);
                            stmt.setString(2, course.getCourseNumber());
                            stmt.executeUpdate();
                            course.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            course.setType(Message.MESSAGE_TYPE.TYPE_COURSE_ALREADY_SELECTED);
                        }
                    } else {
                        course.setType(Message.MESSAGE_TYPE.TYPE_COURSE_STUDENTS_FULL);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                course.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            }
        }
        else {
            course.setType(Message.MESSAGE_TYPE.TYPE_COURSE_CONFLICT);
        }
    }

    /**
     * Delete a course selected by the student.
     * @param conn SQL connection object.
     * @param course Course Object that contains at least ECardNumber and CourseNumber.
     * @throws SQLException All exceptions are treated as FAIL.
     */
    public void deselectCourse(Connection conn, Course course) throws SQLException {
        try {
            String sql = "delete from CoursesSelectedStatus where courseNumber = ? and ECardNumber = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,course.getCourseNumber());
            stmt.setString(2,course.getECardNumber());
            stmt.executeUpdate();
            course.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            course.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Get all the courses taken by designated student.
     * @param conn SQL connection.
     * @param per Person, namely the student.
     * @throws SQLException Message type will be set to FAIL when exception happens.
     */
    public void getCoursesSelected(Connection conn, Person per)throws SQLException {
        try{
            ArrayList<Course> cst = new ArrayList<Course>();
            String sql = "select Courses.* from Users inner join (Courses inner join CoursesSelectedStatus" +
                    " on Courses.courseNumber = CoursesSelectedStatus.courseNumber) on Users.ECardNumber = " +
                    "CoursesSelectedStatus.EcardNumber where (((Users.EcardNumber)= ?))"; //SQL statement to
            // retrieve all the courses selected by designated student.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, per.getECardNumber());
            ResultSet courseRes = stmt.executeQuery();
            while(courseRes.next()) {
                String courseNumber = courseRes.getString("courseNumber");
                String courseName = courseRes.getString("courseName");
                String courseLecturer = courseRes.getString("courseLecturer");
                String courseSemester = courseRes.getString("courseSemester");
                String coursePlace = courseRes.getString("coursePlace");
                String courseTime = courseRes.getString("courseTime");
                String courseType = courseRes.getString("courseType");
                String courseCredit = courseRes.getString("courseCredit");
                int maxStuds = courseRes.getInt("maximumStudents");
                int erdStuds = courseRes.getInt("enrolledStudents");
                cst.add(new Course(courseNumber,courseName,courseSemester,courseLecturer,coursePlace,courseTime,
                        courseCredit,courseType,maxStuds,erdStuds));
            }
            per.setCoursesSelected(cst);
            per.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            per.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }



    public Person PersonMessageSend(Connection conn,Person p)throws SQLException { //将用户基本信息发给服务端

        String sql= "select*from Users where ECardNumber=?";
        this.stmt = conn.prepareStatement(sql);
        stmt.setString(1, p.getECardNumber());
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            String Name = res.getNString("userName");
            String SN = res.getString("StudentNumber");
            String AL = res.getString("AuthorityNumber");
            String LBN = res.getString("LendBooksNumber");
            String ECB = res.getString("ECardBalance");

            p.setName(Name);
            p.setStudentNumber(SN);
            p.setAuthorityLevel((short) Integer.parseInt(AL));
            p.setLendBooksNumber((short) Integer.parseInt(LBN));
            p.setECardBalance((short) Integer.parseInt(ECB));
        }
        return p;
    }

    public Goods GoodsMessageSend(Connection conn, Goods g)throws SQLException { return g; }            //传输商店所有商品信息

    public ShopManage ShopMessageSend(Connection conn, ShopManage SM)throws SQLException { return SM; } //传输商店管理信息

    public Bank BankMessage(Connection conn,Bank bankUsers)throws SQLException {                        //传输银行客户信息
        String sql = "select*from Bank where ECardNumber=?";
        this.stmt = conn.prepareStatement(sql);
        stmt.setString(1, bankUsers.getECardNumber());
        ResultSet res = stmt.executeQuery();

        if(res.next()){
            String BB=res.getString("BankBalance");
            String Exp=res.getString("Expenditure");
            String Inc=res.getString("Income");

            bankUsers.setBankBalance((short) Integer.parseInt(BB));
            bankUsers.setExpenditure((short) Integer.parseInt(Exp));
            bankUsers.setIncome((short) Integer.parseInt(Inc));
        }
        return bankUsers;
    }

}
