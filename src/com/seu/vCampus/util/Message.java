package com.seu.vCampus.util;

public class Message implements java.io.Serializable{

    protected MESSAGE_TYPE Type;
    protected String ECardNumber;

    public enum  MESSAGE_TYPE{
        TYPE_NULL,
        TYPE_SUCCESS,
        TYPE_FAIL,
        TYPE_LOGIN,
        TYPE_QUERY_PERSON,
        TYPE_QUERY_PERSON_MANAGE,
        TYPE_SELECT_COURSE,
        TYPE_COURSE_STUDENTS_FULL,
        TYPE_DESELECT_COURSE,
        TYPE_COURSE_CONFLICT,
        TYPE_COURSE_ALREADY_SELECTED,
        TYPE_GET_COURSES_AVAILABLE,
        TYPE_GET_COURSES_SELECTED,
        TYPE_GET_WITHOUT_GRADES,
        TYPE_GET_GRADES,
        TYPE_GET_ENROLLED_STUDENTS,
        TYPE_GET_LECTURER_COURSES,
        TYPE_SET_GRADE,
        TYPE_GRADES_INPUT,
        TYPE_ADD_COURSE,
        TYPE_QUERY_EXAMINABLE_COURSES,
        TYPE_INPUT_EXAMS,
        TYPE_GET_EXAM_INFO,
        TYPE_GET_STUDENT_EXAMS_INFO,
        TYPE_QUERY_BANK_COUNT,
        TYPE_RECHARGE_ECARD,
        TYPE_QUERY_GOODS,
        TYPE_DELETE_GOODS,
        TYPE_ADD_GOODS,
        TYPE_ADD_BOOK,
        TYPE_DELETE_BOOK,
        TYPE_QUERY_BOOKS,
        TYPE_CHANGE_AVATAR,
        TYPE_RENEWAL_BOOK,
        TYPE_RETURN_BOOK,
        TYPE_LEND_BOOK,
        TYPE_UPDATE_USER,
        TYPE_QUERY_NEWS
    }

    public void setECardNumber(String ECardNumber) {
        this.ECardNumber = ECardNumber;
    }

    public String getECardNumber() {
        return ECardNumber;
    }

    public MESSAGE_TYPE getType() {
        return Type;
    }

    public void setType(MESSAGE_TYPE type) {
        Type = type;
    }

}
