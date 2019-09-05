package com.seu.vCampus.Database;
import com.seu.vCampus.util.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Jamie , mbh
 * @className DatabaseActions
 * @createdTime 8/27/2019 10:21 AM
 * @description All the functions need to manipulate the database and interact with the server.
 */

@SuppressWarnings("JpaQueryApiInspection")
public class DatabaseActions {
    private PreparedStatement stmt = null;
    private Connection conn;

    public DatabaseActions(Connection conn) {
        this.conn = conn;
    }


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
     * @param sql SQL statement to be executed.
     * @param person Person.
     * @param semester Semester, can be empty string.
     */
    private void setStudentCoursesList(String sql, Person person, String semester) {
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
     * @param login Login message object
     */
    public void validatePassword(Login login) {
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
     * @param course Course message object. Should contain the course information and the ECard number.
     */
    public void selectCourse(Course course) {
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
            getCoursesSelected(per, "");
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
                            sql = "insert into CoursesSelected values(?,?)";
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
     * @param course Course Object that contains at least ECardNumber and CourseNumber.
     */
    public void deselectCourse(Course course) {
        try {
            String sql = "delete from CoursesSelected where courseNumber = ? and ECardNumber = ?";
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
     * @param person Person, namely the student.
     * @param semester Specify which semester to be retrieved.
     */
    public void getCoursesSelected(Person person, String semester) {
        String sql = "select * from Courses where exists (select * from CoursesSelected, Users where " +
                "Courses.courseNumber = CoursesSelected.courseNumber and CoursesSelected.ECardNumber " +
                "= Users.ECardNumber and Users.ECardNumber = ?) and Courses.courseSemester = ?";
        setStudentCoursesList(sql,person,semester);
    }

    /**
     * Get all the courses taken by designated student.
     * @param person Person, namely the student.
     */
    public void getCoursesSelected(Person person) {
        String sql = "select * from Courses where exists (select * from CoursesSelected, Users where " +
                "Courses.courseNumber = CoursesSelected.courseNumber and CoursesSelected.ECardNumber " +
                "= Users.ECardNumber and Users.ECardNumber = ?) ";
        setStudentCoursesList(sql,person,"");
    }

    /**
     * Get all the courses available to (not full) and not selected by this student this semester.
     * @param person Person object. Should contain ECardNumber.
     */
    public void getCoursesAvailable(Person person, String semester) {
        String sql = "select * from Courses where not exists (select * from CoursesSelected, Users where " +
                "Courses.courseNumber = CoursesSelected.courseNumber and CoursesSelected.ECardNumber " +
                "= Users.ECardNumber and Users.ECardNumber = ?) and Courses.courseSemester = ? " +
                "and Courses.enrolledStudents < Courses.maximumStudents";
        setStudentCoursesList(sql,person,semester);
    }

    /**
     * Get the grades of the student.
     * @param person Person object.
     */
    public void getGrades(Person person) {
        ArrayList<Course> cs = new ArrayList<Course>();
        String sql = "SELECT Courses.*, CoursesSelected.* FROM Courses INNER JOIN CoursesSelected ON " +
                "(CoursesSelected.courseNumber = Courses.courseNumber and CoursesSelected.EcardNumber = ? and " +
                "CoursesSelected.grade is not null)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,person.getECardNumber());
            ResultSet gradesRes = stmt.executeQuery();
            while (gradesRes.next()){
                Course c = new Course(gradesRes.getString("courseNumber"),
                        gradesRes.getString("courseName"), gradesRes.getString("courseSemester"),
                        gradesRes.getString("courseLecturer"),
                        gradesRes.getString("courseCredit"),
                        gradesRes.getString("courseType"),gradesRes.getInt("grade"));
                cs.add(c);
            }
            person.setCourses(cs);
            person.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            person.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Add a new course to the database.
     * @param course Course object. Should contain all the information, with the ECardNumber being the lecturer's.
     */
    public void addCourse(Course course) {
        try{
            String sql = "insert into Courses (courseNumber, courseName, courseSemester, lecturerECardNumber," +
                    "coursePlace, courseTime, maximumStudents, enrolledStudents, courseCredit, courseType," +
                    "courseLecturer) values (?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,course.getCourseNumber());
            stmt.setString(2,course.getCourseName());
            stmt.setString(3,course.getCourseSemester());
            stmt.setString(4,course.getECardNumber());
            stmt.setString(5,course.getCoursePlace());
            stmt.setString(6,course.getCourseTime());
            stmt.setInt(7,course.getMaximumStudents());
            stmt.setInt(8,course.getEnrolledStudents());
            stmt.setString(9,course.getCourseCredit());
            stmt.setString(10,course.getCourseType());
            stmt.setString(11,course.getCourseLecturer());
            stmt.executeUpdate();
            course.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            course.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Set grade for one course of one student.
     * @param course Course Object. Should contain grade, course number and ECard Number.
     */
    public void setGrade(Course course) {
        String sql = "UPDATE CoursesSelected SET grade = ? WHERE courseNumber = ? and ECardNumber = ?";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,course.getCourseGrade());
            stmt.setString(2,course.getCourseNumber());
            stmt.setString(3,course.getECardNumber());
            stmt.executeUpdate();
            course.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            course.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Get the enrolled students of a course.
     * @param person Person Object. Must contain ECardNumber (of the teacher) and course number(Please store it in the
     *               last element of the Person.courses list.)
     *               When this action is performed, a list of courses that contain all the information you need will be
     *               stored into the Person.courses list (the original courses list information will be overwritten).
     */
    public void getEnrolledStudents(Person person) {
        String sql = "SELECT Courses.*, CoursesSelected.* FROM Courses, CoursesSelected WHERE " +
                "Courses.courseNumber = CoursesSelected.courseNumber AND Courses.courseNumber = ?";
        int l = person.getCourses().size();
        String cN = person.getCourses().get(l-1).getCourseNumber();
        ArrayList<Course> cs = new ArrayList<Course>();

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,cN);
            ResultSet cRes = stmt.executeQuery();
            while (cRes.next()) {
                Course c = new Course(cRes.getString("courseNumber"),cRes.getString("courseName"),
                        cRes.getString("courseSemester"),cRes.getString("courseLecturer"),
                        cRes.getString("coursePlace"),cRes.getString("courseTime"),
                        cRes.getString("courseCredit"),cRes.getString("courseType"),
                        cRes.getInt("maximumStudents"),cRes.getInt("enrolledStudents"));
                c.setECardNumber(cRes.getString("ECardNumber"));
                cs.add(c);
            }
            person.setCourses(cs);
            person.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            person.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /**
     * Get the courses of the designated lecturer.
     * @param person Person object. Should contain ECardNumber of the lecturer. If the last item of Person.courses has
     *               courseSemester info that is not empty string, the semester info will be included in the sql.
     */
    public void getLecturerCourses(Person person) {
        ArrayList<Course> cs = new ArrayList<Course>();
        String sql;
        try{
            if (person.getCourses() == null){
                sql = "select * from Courses where lecturerECardNumber = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,person.getECardNumber());
            }
            else {
                String semester = person.getCourses().get(person.getCourses().size() - 1).getCourseSemester();
                sql = "select * from Courses where lecturerECardNumber = ? and courseSemester = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, person.getECardNumber());
                stmt.setString(2, semester);
            }

            ResultSet cRes = stmt.executeQuery();
            while (cRes.next()) {
                cs.add(new Course(cRes.getString("courseNumber"),cRes.getString("courseName"),
                        cRes.getString("courseSemester"), cRes.getString("courseLecturer"),
                        cRes.getString("coursePlace"),cRes.getString("courseTime"),
                        cRes.getString("courseCredit"),cRes.getString("courseType"),
                        cRes.getInt("maximumStudents"),cRes.getInt("enrolledStudents")));
            }
            person.setCourses(cs);
            person.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            person.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }

    }

    /**
     * Give me a list of courses and I'll set all the grades in it to the database.
     * @param person Person object. It's list of courses should not be empty.
     */
    public void gradesInput(Person person) {
        try {
            for (Course c : person.getCourses()) {
                if (!c.getECardNumber().isEmpty()) {
                    setGrade(c);
                }
            }
            person.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        }catch (NullPointerException e) {
            e.printStackTrace();
            person.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    /*
    The following is written by mbh.
    Database functions relating to bank and shop.
     */

    public Person PersonMessageSend(Person p) {                       //将用户基本信息发给服务端
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
                String avatar = res.getString("AvatarID");

                p.setName(Name);
                p.setStudentNumber(SN);
                p.setAuthorityLevel((short) Integer.parseInt(AL));
                p.setLendBooksNumber((short) Integer.parseInt(LBN));
                p.setECardBalance(Double.parseDouble(ECB));
                p.setSex(Sex);
                p.setAvatarID(avatar);
            }
            return p;
        }catch (SQLException E)
        {
            E.printStackTrace();
            p.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            return  p;
        }
    }

    public void deletePerson(Person p){                //删除一位用户的信息
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

    public void insertPerson(Person p){                     //添加一位用户信息
        try{
            PreparedStatement sql = conn.prepareStatement("insert into Users" +
                    "(ECardNumber,userName,PassWord,Sex,AuthorityNumber,LendBooksNumber,ECardBalance,StudentNumber,AvatarID)" +
                    "values(?,?,?,?,?,?,?,?,?)");
            sql.setString(1, p.getECardNumber());
            sql.setString(2, p.getName());
            sql.setString(3, p.getPassWord());
            sql.setString(4, p.getSex());
            sql.setString(5, Integer.toString(p.getAuthorityLevel().valueOf()));
            sql.setString(6, Integer.toString(p.getLendBooksNumber()));
            sql.setString(7, Double.toString(p.getECardBalance()));
            sql.setString(8, p.getStudentNumber());
            sql.setString(9,p.getAvatarID());
            sql.executeUpdate();

        }catch (SQLException E)
        {
            E.printStackTrace();
            p.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public  PersonManage  getPersonManage(PersonManage PM){         //获取所有用户的信息
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from Users");
            Person temp = new Person();

            while (res.next()){
                String ECardNumber = res.getString("ECardNumber");
                String userName = res.getString("userName");
                String PassWord = res.getString("PassWord");
                String Sex = res.getString("Sex");
                int AuthorityNumber = (short)Integer.parseInt("AuthorityNumber");
                short LendBooksNumber = (short)Integer.parseInt("LendBooksNumber");
                double ECardBalance = Double.parseDouble("ECardBalance");
                String Avatar = res.getString("AvatarID");

                temp.setECardNumber(ECardNumber);
                temp.setName(userName);
                temp.setPassWord(PassWord);
                temp.setAuthorityLevel(AuthorityNumber);
                temp.setSex(Sex);
                temp.setECardBalance(ECardBalance);
                temp.setLendBooksNumber(LendBooksNumber);
                temp.setAvatarID(Avatar);

                PM.addUser(temp);
            }
            return PM;
        }catch (SQLException e){
            e.printStackTrace();
            return PM;
        }
    }

    public ShopManage getShopMessage(ShopManage SM) {          //传输商店商品信息

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
                temp.setGoodsPrice(Double.parseDouble(GP));
                temp.setGoodsStock((short) Integer.parseInt(St));

                SM.addGoods(temp);
            }

            return SM;
        }catch (SQLException E)
        {
            E.printStackTrace();
            return  SM;
        }
    }

    public void deleteGoods(Goods g){     //删除某个商品信息
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

    public void insertGoods(Goods g) {      //添加某个新商品
        try{
            PreparedStatement sql = conn.prepareStatement("insert into Goods(GID,goodsName,Price,Stock)" +
                    "values(?,?,?,?)");
            sql.setString(1, g.getGoodsNumber());
            sql.setString(2, g.getGoodsName());
            sql.setString(3, Double.toString(g.getGoodsPrice()));
            sql.setString(4, Integer.toString(g.getGoodsStock()));
            sql.executeUpdate();
            g.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        }catch (SQLException E)
        {
            E.printStackTrace();
            g.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public BankCount getBankMessage(BankCount bankCountUsers) {              //传输银行客户信息
        try {
            BankBill temp=new BankBill();
            String sql = "select*from BankCount where ECardNumber=? and CountPassword=?";
            this.stmt = conn.prepareStatement(sql);
            stmt.setString(1, bankCountUsers.getECardNumber());
            stmt.setString(2,bankCountUsers.getBankPassword());
            ResultSet res = stmt.executeQuery();

            if(res.next()){
                String BB=res.getString("BankBalance");
                bankCountUsers.setBankBalance(Double.parseDouble(BB));

                sql="select*from BankCount FULL INNER JOIN BankBill ON " +    //取两表以一卡通为准的交集
                        "(BankCount.ECardNumber =BankBill.ECardNumber" +
                        " and where BankBill.ECardNumber=?)";
                stmt=conn.prepareStatement(sql);
                stmt.setString(1,bankCountUsers.getECardNumber());
                ResultSet Res=stmt.executeQuery();

                while(Res.next()){
                    boolean Type=res.getBoolean("Type");
                    String BA=res.getString("Amount");
                    String TransactionTime=res.getString("TransactionTime");
                    Date BD=res.getDate("TransactionTime");

                    temp.setBillType(Type? BankBill.BILL_TYPE.TYPE_EXPENDITURE: BankBill.BILL_TYPE.TYPE_INCOME);
                    temp.setBillDate(BD);
                    temp.setBillAmount(Double.parseDouble(BA));
                    bankCountUsers.addBill(temp);
                }

            }
            return bankCountUsers;

        }catch (SQLException E){
            E.printStackTrace();
            bankCountUsers.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            return  bankCountUsers;
        }
    }

    public BankBill ECardRecharge(BankBill bankBill)   //一卡通充值
    {
        try {
            String sql = "select*from Users where ECardNumber=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,bankBill.getECardNumber());
            ResultSet res=stmt.executeQuery();
            double ECB;
            double BB;

            if(res.next()) {
                ECB = res.getDouble("ECardBalance");
                ECB = ECB + bankBill.getBillAmount();

                sql = "select*from BankCount where ECardNumber=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,bankBill.getECardNumber());
                res = stmt.executeQuery();
                BB = res.getDouble("BankBalance") - bankBill.getBillAmount();

                sql = "UPDATE Users set ECardBalance=? where ECardNumber=?";
                stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, ECB);
                stmt.setString(2, bankBill.getECardNumber());

                sql = "UPDATE BankCount set BankBalance=? where ECardNumber=?";
                stmt = conn.prepareStatement(sql);
                stmt.setDouble(1,BB);
                stmt.setString(2,bankBill.getECardNumber());

                sql="insert into BankBill(ECardNumber,Type,Amount,TransactionTime) value (?,?,?,?)";
                stmt=conn.prepareStatement(sql);
                stmt.setString(1,bankBill.getECardNumber());
                stmt.setBoolean(2,true);
                stmt.setDouble(3,bankBill.getBillAmount());
                stmt.setDate(4, (java.sql.Date) bankBill.getBillDate());

                bankBill.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
                return bankBill;
            }else{
                bankBill.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
                return bankBill;
            }
        }catch (SQLException E)
        {
            E.printStackTrace();
            bankBill.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            return  bankBill;
        }
    }

    public BookManage getBookMessage(BookManage bookManage){            //获取所有图书信息
        try{
            Statement st=conn.createStatement();
            ResultSet res=st.executeQuery("select *from Books");
            Book temp=new Book();

            while(res.next()){
                String BID=res.getString("BID");
                String BN=res.getString("bookName");
                String Auth=res.getString("Author");
                String isLent=res.getString("isLent");
                Date lD=res.getDate("lendDate");
                short lday=res.getShort("lendDays");
                String ECN=res.getString("ECardNumber");

                temp.setBID(BID);
                temp.setName(BN);
                temp.setAuthor(Auth);
                temp.setLent(isLent=="在库"?false:true);
                temp.setLendDate(lD);
                temp.setLendDays(lday);
                temp.setECardNumber(ECN);

                bookManage.AddBook(temp);
            }
            return bookManage;
        }catch (SQLException E){
            E.printStackTrace();
            return bookManage;
        }
    }

    public void insertBook(Book book){                     //添加一本新书
        try{
            PreparedStatement sql = conn.prepareStatement("insert into Books(BID,bookName,Author,isLent,lendDate,lendDays)" +
                    "values(?,?,?,?,?,?)");
            sql.setString(1,book.getBID() );
            sql.setString(2, book.getName());
            sql.setString(3, book.getAuthor());
            sql.setBoolean(4, book.isLent());
            sql.setDate(5, (java.sql.Date) book.getLendDate());
            sql.setShort(6,book.getLendDays());
            sql.executeUpdate();
            book.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
        }catch (SQLException E)
        {
            E.printStackTrace();
            book.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public void deleteBook(Book book){                  //从书库中删除一本书
        try{
            String sql= "delete from Books where BID= ?";
            this.stmt=conn.prepareStatement(sql);
            stmt.setString(1,book.getBID());
            stmt.executeUpdate();

        }catch (SQLException E)
        {
            E.printStackTrace();
            book.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public void updateLendDate(Book book,int Type){       //修改书的状态
        try{
            switch (Type){
                case 0: {                                                         //续订
                    String sql = "UPDATE Books set lendDays=? where BID=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setShort(1, (short) 30);
                    stmt.setString(2,book.getBID());
                    book.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
                    break;
                }
                case 1:{                                                          //还书
                    String sql = "UPDATE Books set lendDays=?,isLent=?,ECardNumber=? where BID=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setShort(1, (short) 0);
                    stmt.setBoolean(2,false);
                    stmt.setString(3, "");
                    stmt.setString(4,book.getBID());
                    book.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
                    break;
                }
                case 2:{                                                         //借书
                    String sql = "UPDATE Books set lendDays=?,isLent=?,ECardNumber=? where BID=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setShort(1, (short) 30);
                    stmt.setBoolean(2,true);
                    stmt.setString(3, book.getECardNumber());
                    stmt.setString(4,book.getBID());
                    book.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
                    break;
                }
                default:
                    book.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            }
        }catch (SQLException E){
            E.printStackTrace();
            book.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public Person changeAvatar(Person p){     //修改头像
        try{
            String sql = "update Users set AvatarID = ? where ECardNumber=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,p.getAvatarID());
            stmt.setString(2,p.getECardNumber());
            stmt.executeUpdate();
            p.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
            return p;
        }catch (SQLException e){
            e.printStackTrace();
            p.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            return p;
        }
    }
}
