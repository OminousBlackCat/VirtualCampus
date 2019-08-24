package com.seu.vCampus.util;

import java.io.*;

public class Message implements java.io.Serializable{

    protected MESSAGE_TYPE Type;
    protected String ECardNumber;

    public enum  MESSAGE_TYPE{
        TYPE_LOGIN,
        TYPE_PERSON,
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
