package com.seu.vCampus.Server;

/**
 * @ServerThread.java
 * @服务端线程
 * @完成日期：2019_8_24
 * @作者：wxy
 */



import com.seu.vCampus.util.*;
import com.seu.vCampus.Database.DatabaseConnection;
import com.seu.vCampus.Database.DatabaseActions;

import java.net.Socket;
import java.io.*;


public class ServerThread  extends Thread{
    private Socket socket;

    /**
     * 输入的流
     **/
    private InputStream is;
    private BufferedInputStream bis ;
    private ObjectInputStream ois;

    /**
     * 输出的流
     **/
    private ObjectOutputStream oos;
    private OutputStream os;

    /**
     * 输入的message对象与输出的message对象
     **/
    private Message msg;
    private DatabaseActions act = new DatabaseActions(DatabaseConnection.getConn());

    public ServerThread(Socket s){
        this.socket = s;
        is = null;
        bis = null;
        ois = null;

        os = null;
        oos = null;

        msg = new Message();
    }


    @Override
    public void run(){
        try {

            is = socket.getInputStream();          //获得socket的输入流
            os = socket.getOutputStream();         //获得socket的输出流

            while (true) {
                bis = new BufferedInputStream(is);     //构建缓冲输入流
                ois = new ObjectInputStream(bis);      //反序列化获得对象
                oos = new ObjectOutputStream(os);      //
                msg = (Message) ois.readObject();   //获得Message对象
                System.out.println(msg.getECardNumber());
                switch (msg.getType()) {
                    case TYPE_LOGIN:
                        act.validatePassword((Login) msg);
                        break;
                    case TYPE_QUERY_PERSON:
                        System.out.println("是获取基本信息mes，一卡通号是：" + msg.getECardNumber());
                        act.PersonMessageSend((Person) msg);
                        break;
                    case TYPE_DESELECT_COURSE:
                        System.out.println("是删除课程mes，一卡通号是：" + msg.getECardNumber());
                        act.deselectCourse((Course) msg);
                        break;
                    case TYPE_SELECT_COURSE:
                        act.selectCourse((Course) msg);
                        break;
                    case TYPE_GET_COURSES_AVAILABLE: //Message must be a person object with the last
                        // element of courses list containing semester info.
                    {
                        if(((Person) msg).getCourses().isEmpty()) {
                            act.getCoursesAvailable((Person) msg);
                        }
                        else{
                            int l = ((Person) msg).getCourses().size();
                            String semester = ((Person) msg).getCourses().get(l - 1).getCourseSemester();
                            act.getCoursesAvailable((Person) msg, semester);
                        }
                        break;
                    }
                    case TYPE_GET_COURSES_SELECTED: {
                        if(((Person) msg).getCourses().isEmpty()) {
                            act.getCoursesSelected((Person) msg);
                        }
                        else {
                            int l = ((Person) msg).getCourses().size();
                            String semester = ((Person) msg).getCourses().get(l - 1).getCourseSemester();
                            act.getCoursesSelected((Person) msg, semester);
                        }
                        break;
                    }
                    case TYPE_GET_WITHOUT_GRADES:
                        act.getCoursesSelectedWithoutGrades((Person) msg);
                    case TYPE_GET_GRADES:
                        act.getGrades((Person) msg);
                        break;
                    case TYPE_SET_GRADE:
                        act.setGrade((Course) msg);
                        break;
                    case TYPE_GRADES_INPUT:
                        act.gradesInput((Person) msg);
                        break;
                    case TYPE_GET_LECTURER_COURSES:
                        act.getLecturerCourses((Person) msg);
                        break;
                    case TYPE_GET_ENROLLED_STUDENTS:
                        act.getEnrolledStudents((Person) msg);
                        break;
                    case TYPE_ADD_COURSE:
                        act.addCourse((Course) msg);
                        break;
                    case TYPE_QUERY_EXAMINABLE_COURSES:
                        act.getCoursesForExam((Person) msg);
                        break;
                    case TYPE_INPUT_EXAMS:
                        act.examsInput((Person) msg);
                        break;
                    case TYPE_QUERY_GOODS:
                        act.getShopMessage((ShopManage) msg);
                        break;
                    case TYPE_ADD_GOODS:
                        act.insertGoods((Goods) msg);
                        break;
                    case TYPE_DELETE_GOODS:
                        act.deleteGoods((Goods) msg);
                        break;
                    case TYPE_QUERY_PERSON_MANAGE:
                        act.getPersonManage((PersonManage) msg);
                        break;
                    case TYPE_RECHARGE_ECARD:
                        act.ECardRecharge((BankBill) msg);
                        break;
                    case TYPE_QUERY_BANK_COUNT:
                        act.getBankMessage((BankCount) msg);
                        break;
                    case TYPE_QUERY_BOOKS:
                        act.getBookMessage((BookManage) msg);
                        break;
                    case TYPE_ADD_BOOK:
                        act.insertBook((Book) msg);
                        break;
                    case TYPE_DELETE_BOOK:
                        act.deleteBook((Book) msg);
                        break;
                    case TYPE_CHANGE_AVATAR:
                        act.changeAvatar((Person) msg);
                        break;
                    case TYPE_LEND_BOOK:
                        act.updateLendDate((Book)msg,2);
                        break;
                    case TYPE_RENEWAL_BOOK:
                        act.updateLendDate((Book)msg,0);
                        break;
                    case TYPE_RETURN_BOOK:
                        act.updateLendDate((Book)msg,1);
                        break;
                }

                System.out.println(msg.getType());
                oos.writeObject(msg);

            }
        }


        catch (IOException | ClassNotFoundException ioe){
            ioe.printStackTrace();
        }
    }

}

