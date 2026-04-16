package CRUDPractice.Requests;

import CRUDPractice.Models.UserInformation;
import CRUDPractice.DBConnect.SQLSetup;

import java.util.Scanner;
import java.sql.*;

public class DeleteRequest {
	
	private static Scanner scan = new Scanner(System.in);

	public static void DeleteDB(UserInformation user){
		
		while(true) {
			System.out.println("Enter the ID that you want to Delete?:");
			
			System.out.print("USER ID: ");
			String CheckUserID = scan.nextLine().trim();
			
			if(!CheckUserID(CheckUserID, user)) continue;
			
			System.out.print("CONFIRM (Y/N)? =>  ");
			char choice = Character.toUpperCase(scan.next().charAt(0));
			
			switch(choice) {
				case '\u0000':
					System.out.println("Please enter something!");
				case 'N':
					System.out.println("Goodbye!");
					break;
				case 'Y':
					if(!(user.getUserID() <= 0)) {
						System.out.println("Please enter a valid ID!");
						return;
					}
					
					String query = "DELETE FROM USERINFO WHERE USER_ID = ?";
					
					try(Connection connect = SQLSetup.getConnection();
						PreparedStatement result = connect.prepareStatement(query)){
						
						result.setInt(1, user.getUserID());
						
						result.executeUpdate();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					continue;
					
				default:
					System.out.println("Wrong Input!");
					continue;
			}
		}
	}
	
	private static boolean CheckUserID(String CheckUserID, UserInformation user) {
		
		if(CheckUserID.isEmpty()) {
			System.out.println();
			System.out.println("###############################");
			System.out.println("          IMPORTANT!");
			System.out.println("   Please enter your user ID");
			System.out.println("###############################");
			System.out.println();
			return false;
		}
		
		for(char c : CheckUserID.toCharArray()){
			if(!Character.isDigit(c)) {
				System.out.println();
				System.out.println("Please enter only digits for your user ID!");
				System.out.println();
				return false;
			}
		}
		
		try (Connection connect = SQLSetup.getConnection()){
			int convertStringID = Integer.parseInt(CheckUserID);
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
						return false;
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
					
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			System.out.println("Enter a valid User ID!");
			e.printStackTrace();
		}
		
		return true;
	}
}
