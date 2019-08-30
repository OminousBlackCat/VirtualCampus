package com.seu.vCampus.Database;
import com.seu.vCampus.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jamie , mbh
 * @className DatabaseActions
 * @createdTime 8/27/2019 10:21 AM
 * @description All the functions need to manipulate the database and interact with the server.
 */

public class DatabaseActions {
    private PreparedStatement stmt = null;

    public void validatePassword(Connection conn, Login login) throws SQLException { //匹配用户名与密码
        String sql= "select*from Users where ECardNumber=?";
        this.stmt = conn.prepareStatement(sql);
        stmt.setString(1,login.getECardNumber());
        ResultSet res=stmt.executeQuery();

        if(res.next()){
            String PW = res.getString("PassWord");
            String AL = res.getString("AuthorityNumber");
            if(PW.equals(login.getPassWord())){
                login.setAuthorityLevel((short) Integer.parseInt(AL));
                login.setType(Message.MESSAGE_TYPE.TYPE_SUCCESS);
            }
            else login.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
        else
        {
            login.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }
    public Person messageSend(Connection conn,Person p)throws SQLException{
        String sql= "select*from Users where ECardNumber=?";
        this.stmt = conn.prepareStatement(sql);
        stmt.setString(1,p.getECardNumber());
        ResultSet res=stmt.executeQuery();

        if(res.next()){
            String Name=res.getNString("userName");
            String SN=res.getString("StudentNumber");
            String AL=res.getString("AuthorityNumber");
            String LBN=res.getString("LendBooksNumber");
            String ECB=res.getString("ECardBalance");

            p.setName(Name);
            p.setStudentNumber(SN);
            p.setAuthorityLevel((short)Integer.parseInt(AL));
            p.setLendBooksNumber((short) Integer.parseInt(LBN));
            p.setECardBalance((short) Integer.parseInt(ECB));
            p.setMatched(true);
        }
        else p.setMatched(false);

        return p;
    }

}
