package com.seu.vCampus.util;

import com.sun.deploy.security.ValidationState;
import net.ucanaccess.commands.ICommand;

import java.io.*;

public class Message implements java.io.Serializable{

    protected MESSAGE_TYPE Type;
    protected String ECardNumber;

    public enum  MESSAGE_TYPE{
        TYPE_NULL,
        TYPE_SUCCESS,
        TYPE_FAIL,
        TYPE_LOGIN,
        TYPE_QUERY_PERSON,
        TYPE_SELECT_COURSE,
        TYPE_COURSE_STUDENTS_FULL,
        TYPE_DELETE_COURSE,
        TYPE_COURSE_CONFLICT,
        TYPE_COURSE_ALREADY_SELECTED,
        TYPE_QUERY_BANK_COUNT,
        TYPE_RECHARGE_ECARD,
        TYPE_QUERY_GOODS,
        TYPE_DELETE_GOODS,
        TYPE_ADD_GOODS,
        TYPE_GET_COURSES_AVAILABLE,
        TYPE_GET_COURSES_SELECTED
        ;
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
