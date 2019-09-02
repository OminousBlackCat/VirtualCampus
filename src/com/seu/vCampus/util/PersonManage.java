package com.seu.vCampus.util;

import java.util.List;

public class PersonManage extends Message{

    private List<Person> AllUsers;
    public PersonManage(){
        this.Type = MESSAGE_TYPE.TYPE_NULL;
        this.ECardNumber = null;
    }
    public List<Person> getAllUsers(){return AllUsers;}
    public void addUser(Person add){
        AllUsers.add(add);
    }
}
