package com.seu.vCampus.Database;
import com.seu.vCampus.util.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Jamie , mbh
 * @className DatabaseActions
 * @createdTime 8/27/2019 10:21 AM
 * @description All the functions need to manipulate the database and interact with the server.
 */

public class DatabaseActions {
    private PreparedStatement stmt = null;

    /*
      The following is written by Jamie Liu
      Database functions relating to courses and grades.
     */

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
     *
     * @param conn SQL connection.
     * @param sql SQL statement to be executed.
     * @param person Person.
     * @param semester Semester, can be empty string.
     */
    private void setStudentCoursesList(Connection conn, String sql, Person person, String semester) {
        ArrayList<Course> cs = new ArrayList<Course>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, person.getECardNumber());
            if(!semester.isEmpty()){
                stmt.setString(2, semester);
            }
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
                cs.add(new Course(courseNumber,courseName,courseSemester,courseLecturer,coursePlace,courseTime,
                        courseCredit,courseType,maxStuds,erdStuds));
            }
            person.setCourses(cs);
            person.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            person.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Compare the user-entered account and password with the ones in the database and modify the passed login object
     * according to the compared result. If the account is not found or the password is not correct, then the login
     * object's message type will be set to FAIL, otherwise SUCCESS.
     *
     * @param conn  Database connection
     * @param login Login message object
     */
    public void validatePassword(Connection conn, Login login) {
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
     */
    public void selectCourse(Connection conn, Course course) {
        boolean conflict = false;

        try {
            stmt = conn.prepareStatement("select courseTime, courseSemester from Courses where courseNumber = ?");
            stmt.setString(1, course.getCourseNumber());
            ResultSet timeInfoRes = stmt.executeQuery();
            if (timeInfoRes.next()) {
                course.setCourseTime(timeInfoRes.getString("courseTime"));
                course.setCourseSemester(timeInfoRes.getString("courseSemester"));
            }

            Person per = new Person();
            per.setECardNumber(course.getECardNumber());
            getCoursesSelected(conn, per, "");
            ArrayList<Course> coursesSelected = per.getCourses();

            for (Course c : coursesSelected
            ) {
                if (isConflicted(c, course)) {
                    conflict = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conflict = true;
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
     */
    public void deselectCourse(Connection conn, Course course) {
        try {
            String sql = "delete from CoursesSelectedStatus where courseNumber = ? and ECardNumber = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,course.getCourseNumber());
            stmt.setString(2,course.getECardNumber());
            stmt.executeUpdate();

            sql = "select * from Courses where courseNumber = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getCourseNumber());
            ResultSet courseRes = stmt.executeQuery();

            sql = "update Courses set enrolledStudents = ? where courseNumber = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseRes.getInt("enrolledStudents") - 1);
            stmt.setString(2, course.getCourseNumber());
            stmt.executeUpdate();
            course.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            course.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Get all the courses taken by designated student at some semester.
     * @param conn SQL connection.
     * @param person Person, namely the student.
     * @param semester Specify which semester to be retrieved.
     */
    public void getCoursesSelected(Connection conn, Person person, String semester) {
        String sql = "select * from Courses where exists (select * from CoursesSelectedStatus, Users where " +
                "Courses.courseNumber = CoursesSelectedStatus.courseNumber and CoursesSelectedStatus.ECardNumber " +
                "= Users.ECardNumber and Users.ECardNumber = ?) and Courses.courseSemester = ?";
        setStudentCoursesList(conn,sql,person,semester);
    }
    /**
     * Get all the courses taken by designated student.
     * @param conn SQL connection.
     * @param person Person, namely the student.
     */
    public void getCoursesSelected(Connection conn, Person person) {
        String sql = "select * from Courses where exists (select * from CoursesSelectedStatus, Users where " +
                "Courses.courseNumber = CoursesSelectedStatus.courseNumber and CoursesSelectedStatus.ECardNumber " +
                "= Users.ECardNumber and Users.ECardNumber = ?) ";
        setStudentCoursesList(conn,sql,person,"");
    }

    /**
     * Get all the courses available to (not full) and not selected by this student this semester.
     * @param conn SQL connection.
     * @param person Person object. Should contain ECardNumber.
     */
    public void getCoursesAvailable(Connection conn, Person person, String semester) {
        String sql = "select * from Courses where not exists (select * from CoursesSelectedStatus, Users where " +
                "Courses.courseNumber = CoursesSelectedStatus.courseNumber and CoursesSelectedStatus.ECardNumber " +
                "= Users.ECardNumber and Users.ECardNumber = ?) and Courses.courseSemester = ? " +
                "and Courses.enrolledStudents < Courses.maximumStudents";
        setStudentCoursesList(conn,sql,person,semester);
    }

    public void getGrades(Connection conn, Person person) {

    }

    /*
    The following is written by mbh.
    Database functions relating to bank and shop.
     */

    public Person PersonMessageSend(Connection conn, Person p) { //将用户基本信息发给服务端
        try {
            String sql = "select*from Users where ECardNumber=?";
            this.stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getECardNumber());
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                String Name = res.getString("userName");
                String SN = res.getString("StudentNumber");
                String AL = res.getString("AuthorityNumber");
                String LBN = res.getString("LendBooksNumber");
                String ECB = res.getString("ECardBalance");
                String Sex=res.getString("Sex");

                p.setName(Name);
                p.setStudentNumber(SN);
                p.setAuthorityLevel((short) Integer.parseInt(AL));
                p.setLendBooksNumber((short) Integer.parseInt(LBN));
                p.setECardBalance((short) Integer.parseInt(ECB));
                p.setSex(Sex);
            }
            return p;
        }catch (SQLException E)
        {
            E.printStackTrace();
            p.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            return  p;
        }
    }

    public void deletePerson(Connection conn, Person p){
        try{
            String sql= "delete from Users where ECardNumber= ?";
            this.stmt=conn.prepareStatement(sql);
            stmt.setString(1,p.getECardNumber());
            stmt.executeUpdate();

        }catch (SQLException E)
        {
            E.printStackTrace();
            p.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public void insertPerson(Connection conn,Person p){
        try{
            PreparedStatement sql = conn.prepareStatement("insert into Users" +
                    "(ECardNumber,userName,password,Sex,AuthorityNumber,LendBooksNumber,ECardBalance,StudentNumber)" +
                    "values(?,?,?,?,?,?,?,?)");
            sql.setString(1, p.getECardNumber());
            sql.setString(2, p.getName());
            sql.setString(3, p.getPassWord());
            sql.setString(4, p.getSex());
            sql.setString(5, Integer.toString(p.getAuthorityLevel()));
            sql.setString(6, Integer.toString(p.getLendBooksNumber()));
            sql.setString(7, Integer.toString(p.getECardBalance()));
            sql.setString(8, p.getStudentNumber());
            sql.executeUpdate();

        }catch (SQLException E)
        {
            E.printStackTrace();
            p.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public ShopManage ShopMessageSend(Connection conn, ShopManage SM)throws SQLException {//传输商店商品信息
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from Goods");
            Goods temp = new Goods();

            while (res.next()) {
                String ID = res.getString("GID");
                String GN = res.getString("goodsName");
                String GP = res.getString("Price");
                String St = res.getString("Stock");

                temp.setGoodsNumber(ID);
                temp.setGoodsName(GN);
                temp.setGoodsPrice((short) Integer.parseInt(GP));
                temp.setGoodsStock((short) Integer.parseInt(St));
            }

            SM.addGoods(temp);
            return SM;
        }catch (SQLException E)
        {
            E.printStackTrace();
            return  SM;
        }
    }

        public void deleteGoods(Connection conn, Goods g){
        try{
            String sql= "delete from Goods where GID= ?";
            this.stmt=conn.prepareStatement(sql);
            stmt.setString(1,g.getGoodsNumber());
            stmt.executeUpdate();

        }catch (SQLException E)
        {
            E.printStackTrace();
            g.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public void insertGoods(Connection conn,Goods g,ShopManage SM) {
        try{
            SM.addGoods(g);
            PreparedStatement sql = conn.prepareStatement("insert into Goods(GID,goodsName,Price,Stock)" +
                    "values(?,?,?,?)");
            sql.setString(1, g.getGoodsNumber());
            sql.setString(2, g.getGoodsName());
            sql.setString(3, Integer.toString(g.getGoodsPrice()));
            sql.setString(4, Integer.toString(g.getGoodsStock()));
            sql.executeUpdate();
        }catch (SQLException E)
        {
            E.printStackTrace();
            g.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

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
