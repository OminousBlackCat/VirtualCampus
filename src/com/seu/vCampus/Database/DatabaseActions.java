package com.seu.vCampus.Database;
import com.seu.vCampus.util.*;

import java.sql.*;

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


    public Person PersonMessageSend(Connection conn,Person p)throws SQLException { //将用户基本信息发给服务端

        String sql= "select*from Users where ECardNumber=?";
        this.stmt = conn.prepareStatement(sql);
        stmt.setString(1, p.getECardNumber());
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            String Name = res.getNString("userName");
            String SN = res.getString("StudentNumber");
            String AL = res.getString("AuthorityNumber");
            String LBN = res.getString("LendBooksNumber");
            String ECB = res.getString("ECardBalance");

            p.setName(Name);
            p.setStudentNumber(SN);
            p.setAuthorityLevel((short) Integer.parseInt(AL));
            p.setLendBooksNumber((short) Integer.parseInt(LBN));
            p.setECardBalance((short) Integer.parseInt(ECB));
        }
        return p;
    }

    public Goods GoodsMessageSend(Connection conn, Goods g)throws SQLException { return g; }            //传输商店所有商品信息

    public ShopManage ShopMessageSend(Connection conn, ShopManage SM)throws SQLException { return SM; } //传输商店管理信息

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
