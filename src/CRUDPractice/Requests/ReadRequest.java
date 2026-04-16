package CRUDPractice.Requests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

import CRUDPractice.DBConnect.SQLSetup;
import CRUDPractice.Models.UserInformation;

public class ReadRequest {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void ReadDB(UserInformation user) {
		
		while(true) {
			System.out.print("ENTER USER ID => ");
			String userID = scan.nextLine().trim();
			
			if(!CheckInputs(userID)) continue;
			
			try (Connection connect = SQLSetup.getConnection()){
				int convertStringID = Integer.parseInt(userID);
				user.setUserID(convertStringID);

				String query = "SELECT * FROM USERINFO WHERE USER_ID = ?";
		
				try(PreparedStatement readData = connect.prepareStatement(query)){
					
					readData.setInt(1, user.getUserID());
				
					try (ResultSet result = readData.executeQuery()){
						
						if(!result.next()) {
							System.out.println();
							System.out.println("#########################");
							System.out.println("    NO RECORDS FOUND!");
							System.out.println("#########################");
							System.out.println();
							continue;
						}
						
						do {
							
							int USER_ID = result.getInt("USER_ID");
							String FNAME = result.getString("FIRST_NAME");
							String LNAME = result.getString("LAST_NAME");
							int AGE = result.getInt("AGE");
							String COUNTRY = result.getString("COUNTRY");
							String EMAIL = result.getString("EMAIL");
							String CONTACT_NO = result.getString("CONTACT_NO");
							
							System.out.println();
							System.out.println("------------------------------------------------");
							System.out.println("USER ID     : " + USER_ID);
							System.out.println("FIRST NAME  : " + FNAME);
							System.out.println("LAST NAME   : " + LNAME);
							System.out.println("AGE         : " + AGE);
							System.out.println("COUNTRY     : " + COUNTRY);
							System.out.println("EMAIL       : " + EMAIL);
							System.out.println("CONTACT NO. : " + CONTACT_NO);
							System.out.println("------------------------------------------------");
							System.out.println();
							
						} while (result.next());	
						
						break;
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			} catch (Exception e) {
				System.out.println("Enter a valid User ID!");
				e.printStackTrace();
				continue;
			}
			
		}
	}
	
	private static boolean CheckInputs(String userID) {
		
		if(userID.isEmpty()) {
			System.out.println();
			System.out.println("Please enter your User ID!");
			System.out.println();
			return false;
		}
		
		for(char c : userID.toCharArray()) {
			if(!Character.isDigit(c)) {
				System.out.println();
				System.out.println("Please enter only digits!");
				System.out.println();
				return false;
			}
		}
		
		return true;
	}
}
