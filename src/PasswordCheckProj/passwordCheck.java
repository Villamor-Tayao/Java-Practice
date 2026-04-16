package PasswordCheckProj;
import java.util.Scanner;

public class passwordCheck {
	
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		
		String chars = "`~!@#$%^&*()_+{}|:\"<>?[]\\;',./'";
		
		ValidatePassword(chars);
	}
	
	public static void ValidatePassword(String chars) {
		boolean hasChars = false;
		boolean hasDigits = false;
		boolean hasUpper = false;
		
		boolean running = true;
		
		while(running) {
			System.out.print("Enter your password: ");
			String password = scan.next().trim();
			
			if(password.trim().isEmpty()) {
				System.out.println("Enter something at least!");
				continue;
			}
			
			if(password.length() < 7) {
				System.out.println("Password too short!");
				continue;
			}
			
			//Process if the passwords have specials or numbers
			hasChars = CheckNumsSpecialUpper.ValidateSpecials(password, chars);
			hasDigits = CheckNumsSpecialUpper.ValidateNums(password);
			hasUpper = CheckNumsSpecialUpper.ValidateUpperLetter(password);
			
			if(!hasUpper) {
				System.out.println("Password does not have capital letters");
				continue;
			}
			
			if(!hasChars) {
				System.out.println("Password does not have special characters");
				continue;
			}
			
			if(!hasDigits) {
				System.out.println("Password does not have numbers");
				continue;
			}
			
			if(hasChars && hasDigits && hasUpper) {
				System.out.println("Password is valid!");
				break;
			}
		
		}
	}
}
	
