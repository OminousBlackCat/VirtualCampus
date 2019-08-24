package connectTest;
/**
 * 数据库连接测试（已成功）
 * 完成人：毛泊涵（2019/8/23）
 */

import java.sql.*;

public class AccessConnection {
    private static final String dbURL = "jdbc:ucanaccess://" +
            "./././mySource/VirtualCampus.accdb";

    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            System.out.println("AccessDB connection fail");
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (con != null)
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public void ShowTable(Connection c) throws Exception {   //展示某些数据
        Statement st = c.createStatement();
        ResultSet res = st.executeQuery("select*from Users");
        while (res.next()) {
            System.out.print(res.getString("UID") + "\t");
            System.out.print(res.getString("userName") + "\t");
            System.out.print(res.getString("password") + "\t");
            System.out.println();
        }
    }

    public void insertScore(Connection c) throws Exception {   //添加新用户数据
        try {
            PreparedStatement sql = c.prepareStatement("insert into Users(UID,userName,password)values(?,?,?)");
            sql.setString(1, "");
            sql.setString(2, "");
            sql.setString(3, "");
            sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection c) throws Exception {  //删除最近添加的用户（可改成特定用户）
        Statement st = c.createStatement();
        st.executeUpdate("delete from Users where UID=(select max(UID)from Users)");
    }

    public void Search(Connection c) throws Exception {  //通过UID搜索用户
        Statement sql = c.createStatement();
        ResultSet res = sql.executeQuery("select*from Users where UID=09017211");
        if (res.isLast()) {
            while (res.next()) {
                String id = res.getString("UID");
                String name = res.getString("userName");
                System.out.print("学号：" + id);
                System.out.print("用户名：" + name);
            }
        } else
            System.out.println("未查到这名学生。");
    }

    public void indistinctSearch(Connection c) throws Exception {
        Statement sql = c.createStatement();
        ResultSet res = sql.executeQuery("select*from Users where UID like '09017%'");
        while (res.next()) {
            String id = res.getString("UID");
            String name = res.getString("userName");
            System.out.print("学号：" + id);
            System.out.print("用户名：" + name);
        }
    }
}
