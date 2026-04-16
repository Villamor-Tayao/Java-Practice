package CRUDPractice.Requests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Scanner;

import CRUDPractice.Models.UserInformation;
import CRUDPractice.DBConnect.SQLSetup;

public class UpdateRequest {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void UpdateDB(UserInformation user) {
		
		while(true) {
			ArrayList<String> setQuery = new ArrayList<String>();
			
			System.out.println("IMPORTANT!");
			System.out.print("Enter ID number: ");
			String CheckUserID = scan.nextLine();
			
			if(!CheckUserID(CheckUserID, user)) continue;
			
			System.out.println("Enter the data you want to update:");
			System.out.println("Leave the data blank if you don't want to update it!");
			
			System.out.print("First Name: ");
			user.setFname(scan.nextLine().toUpperCase().trim());
			
			System.out.print("Last Name: ");
			user.setLname(scan.nextLine().toUpperCase().trim());
			
			System.out.print("Age: ");
			String CheckAge = scan.nextLine();
			
			if(!CheckAge(CheckAge)) continue;
			
			System.out.print("Country: ");
			user.setCountry(scan.nextLine().toUpperCase().trim());
			
			System.out.print("Email: ");
			user.setEmail(scan.nextLine().trim());
			
			System.out.print("Contact No.: ");
			user.setContact(scan.nextLine().trim());
			
			if(!CheckContact(user)) continue;
			
			try {
				int ValidatedUserID = Integer.parseInt(CheckUserID);
				user.setUserID(ValidatedUserID);
				
				String query = "UPDATE USERINFO SET " + String.join(", ", setQuery) + " WHERE USER_ID = ?";
				
				BeginAddSetQuerySetup(setQuery, user, CheckAge);
				
				try (Connection connect = SQLSetup.getConnection();
					PreparedStatement QueryStmnt = connect.prepareStatement(query)){
					
					BeginParametersSetup(setQuery, user, QueryStmnt);
					
					QueryStmnt.executeUpdate();
					break;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} catch (Exception e){
				e.printStackTrace();
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
	
	private static boolean CheckAge(String CheckAge) {
		
		if(CheckAge.isEmpty()) {
			return true; //to skip it in case there is nothing to change here
		}
		
		for(char c : CheckAge.toCharArray()){
			if(!Character.isDigit(c)) {
				System.out.println();
				System.out.println("Please enter only digits for your user ID!");
				System.out.println();
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean CheckContact(UserInformation user) {
		
		for(char c : user.getContact().toCharArray()){
			if(!Character.isDigit(c)) {
				System.out.println();
				System.out.println("Please enter only digits for your user ID!");
				System.out.println();
				return false;
			}
		}
		
		return true;
	}
	
	private static void BeginAddSetQuerySetup(ArrayList<String> setQuery, UserInformation user, String CheckAge) {
		
		try {
			if(!user.getFname().isEmpty()) {
				setQuery.add("FIRST_NAME = ?");
			}
			
			if(!user.getLname().isEmpty()) {
				setQuery.add("LAST_NAME = ?");
			}
			
			if(!CheckAge.isEmpty()) {
				int ValidatedAge = Integer.parseInt(CheckAge);
				user.setAge(ValidatedAge);
				
				setQuery.add("AGE = ?");
			}
			
			if(!user.getCountry().isEmpty()) {
				setQuery.add("COUNTRY = ?");
			}
			
			if(!user.getEmail().isEmpty()) {
				setQuery.add("EMAIL = ?");
			}
			
			if(!user.getContact().isEmpty()) {
				setQuery.add("CONTACT_NO = ?");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void BeginParametersSetup(ArrayList<String> setQuery, UserInformation user, PreparedStatement QueryStmnt) {
		
		int index = 1;
		
		try {
			if(!user.getFname().isEmpty()) {
				QueryStmnt.setString(index++, user.getFname());
			}
			
			if(!user.getLname().isEmpty()) {
				QueryStmnt.setString(index++, user.getLname());
			}
			
			if(!(user.getAge() <= 0)) {
				QueryStmnt.setInt(index++, user.getAge());
			}
			
			if(!user.getCountry().isEmpty()) {
				QueryStmnt.setString(index++, user.getCountry());
			}
			
			if(!user.getEmail().isEmpty()) {
				QueryStmnt.setString(index++, user.getEmail());
			}
			
			if(!user.getContact().isEmpty()) {
				QueryStmnt.setString(index++, user.getContact());
			}
			
			QueryStmnt.setInt(index, user.getUserID());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

