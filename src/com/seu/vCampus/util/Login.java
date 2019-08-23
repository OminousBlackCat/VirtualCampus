package com.seu.vCampus.util;

public class Login extends Message {

    private String passWord;
    private boolean isMatched;


    public Login(){
        this.Type = MESSAGE_TYPE.TYPE_LOGIN;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}

