package CRUDPractice;

import CRUDPractice.Models.UserInformation;
import CRUDPractice.DBConnect.SQLSetup;
import CRUDPractice.Requests.CreateRequest;
import CRUDPractice.Requests.ReadRequest;
import CRUDPractice.Requests.UpdateRequest;
import CRUDPractice.Requests.DeleteRequest;

import java.util.Scanner;
import java.sql.*;

public class Main {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		UserInformation user = new UserInformation();
		
		System.out.print("What would you like to do?");
		System.out.println("CREATE, UPDATE, READ or DELETE a Record:");
		
		System.out.print("=> ");
		String choice = scan.next().toUpperCase().trim();
		
		try(Connection connect = SQLSetup.getConnection()){
			switch(choice) {
				case "CREATE":
					CreateRequest.InsertDB(user);
					break;
				case "READ":
					ReadRequest.ReadDB(user);
					break;
				case "UPDATE":
					UpdateRequest.UpdateDB(user);
					break;
				case "DELETE":
					DeleteRequest.DeleteDB(user);
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
