package connectTest;

import java.sql.*;

public class ConnectTest {
	public static void main(String[] args) throws Exception{
		AccessConnection student=new AccessConnection();
		Connection conn=student.getConn();
		System.out.println("表中现有数据：");
		student.ShowTable(conn);
	}
}
