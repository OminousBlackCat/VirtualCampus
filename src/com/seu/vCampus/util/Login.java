package com.seu.vCampus.util;

public class Login extends Message {

    private String passWord;
    private int AuthorityLevel;


    public Login(){
        this.Type = MESSAGE_TYPE.TYPE_LOGIN;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAuthorityLevel() {
        return AuthorityLevel;
    }

    public void setAuthorityLevel(int authorityLevel) {
        AuthorityLevel = authorityLevel;
    }
}


