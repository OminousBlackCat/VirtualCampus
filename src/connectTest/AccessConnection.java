package connectTest;

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
    public  void ShowTable(Connection c)throws Exception {
		Statement st=c.createStatement();
		ResultSet res=st.executeQuery("select*from Users");
		while(res.next())
		{
			System.out.print(res.getString("UID") + "\t");
			System.out.print(res.getString("userName") + "\t");
			System.out.print(res.getString("password") + "\t");
			System.out.println();
		}
	}
}
