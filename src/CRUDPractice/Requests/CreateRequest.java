package CRUDPractice.Requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import CRUDPractice.Models.UserInformation;
import CRUDPractice.DBConnect.SQLSetup;

public class CreateRequest {
	
	public static Scanner scan = new Scanner(System.in);
	
	
	public static void InsertDB(UserInformation user) {
		
		
		while(true) {
			System.out.print("Enter First Name: ");
			user.setFname(scan.nextLine().toUpperCase().trim());
			
			if(!CheckFName(user)) continue;
			
			System.out.print("Enter Last Name: ");
			user.setLname(scan.nextLine().toUpperCase().trim());
			
			if(!CheckLName(user)) continue;
			
			System.out.print("Enter Age: ");
			String age = scan.nextLine().trim();
			
			if(!CheckAge(age)) continue;
			
			System.out.print("Enter Email: ");
			user.setEmail(scan.nextLine().trim());
			
			if(!CheckEmail(user)) continue;
			
			System.out.print("Enter Country Name: ");
			user.setCountry(scan.nextLine().toUpperCase().trim());
			
			if(!CheckCountry(user)) continue;
			
			System.out.print("Enter Contact Number: ");
			user.setContact(scan.nextLine().trim());
			
			if(!CheckContact(user)) continue;
			
			user.setAge(Integer.parseInt(age));
			
			String query = "INSERT INTO USERINFO VALUES (FLOOR(100000000 + RAND() * 900000000), ?, ?, ?, ?, ?, ?)";
			
			try(Connection connect = SQLSetup.getConnection();
				PreparedStatement result = connect.prepareStatement(query)){
				
	//			result.setInt(1, Math.random() * 1000000000); //USER_ID -- DO NOT CHANGE
				result.setString(1, user.getFname()); //FIRST_NAME
				result.setString(2, user.getLname()); //LAST_NAME
				result.setInt(3, user.getAge()); //AGE
				result.setString(4, user.getEmail()); //EMAIL
				result.setString(5, user.getCountry()); //COUNTRY
				result.setString(6, user.getContact()); //CONTACT_NO
				
				result.executeUpdate();
				System.out.println("Account creation success!");
				break;
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		
		}
	}
	
	private static boolean CheckFName(UserInformation user) {
		if(user.getFname().isEmpty()) {
			System.out.println("Please enter your first name!");
			System.out.println("#################################");
			System.out.println();
			System.out.println("TRY AGAIN");
			return false;
		}
		
		return true;
		
	}
	
	private static boolean CheckLName(UserInformation user) {
		if(user.getLname().isEmpty()) {
			System.out.println("Please enter your last name ");
			System.out.println("#################################");
			System.out.println();
			System.out.println("TRY AGAIN");
			return false;
		}
		
		return true;
	}
	
	private static boolean CheckAge(String age) {
		if(age.isEmpty()) {
			System.out.println("Please enter your age ");
			System.out.println("#################################");
			System.out.println();
			System.out.println("TRY AGAIN");
			return false;
		}
		
		for(char c : age.toCharArray()) {
			if(!Character.isDigit(c)) {
				System.out.println("Enter digits only!");
				System.out.println("#################################");
				System.out.println();
				System.out.println("TRY AGAIN");
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean CheckCountry(UserInformation user) {
		if(user.getCountry().isEmpty()) {
			System.out.println("Please enter your country ");
			System.out.println("#################################");
			System.out.println();
			System.out.println("TRY AGAIN");
			return false;
		}
		
		return true;
	}
	
	private static boolean CheckEmail(UserInformation user) {
		if(user.getEmail().isEmpty()) {
			System.out.println("Please enter your country ");
			System.out.println("#################################");
			System.out.println();
			System.out.println("TRY AGAIN");
			return false;
		}
		
		return true;
	}
	
	private static boolean CheckContact(UserInformation user) {
		if(user.getContact().isEmpty()) {
			System.out.println("Please enter your contact number ");
			System.out.println("#################################");
			System.out.println();
			return false;
		}
		
		for(char c : user.getContact().toCharArray()) {
			if(!Character.isDigit(c)) {
				System.out.println("Enter digits only!");
				System.out.println("#################################");
				System.out.println();
				System.out.println("TRY AGAIN");
				return false;
			}
		}
		
		return true;
	}
	
}
