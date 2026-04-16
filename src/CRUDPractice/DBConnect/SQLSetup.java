package CRUDPractice.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class SQLSetup {

	public static Scanner scan = new Scanner(System.in);
	private static Connection connect;
	
	public static Connection getConnection() {
		String URL = "jdbc:mysql://localhost:3306/UserInformationDB";
		String user = "root";
		String DBpassword = "";
		
		connect = null;
		
		try {
			return DriverManager.getConnection(URL, user, DBpassword);
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
		} finally {
			if(connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	
}
