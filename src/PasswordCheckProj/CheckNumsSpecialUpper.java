package PasswordCheckProj;

	public class CheckNumsSpecialUpper{
		
		public static boolean ValidateSpecials(String password, String chars) {
			
			for(char specialChar : chars.toCharArray()) {
				if(password.indexOf(specialChar) != -1) {
					return true;
				}
			}	
			 return false;
		}
		
		public static boolean ValidateNums(String password) {
			
			for(char passNums : password.toCharArray()) {
				if(Character.isDigit(passNums)) {
					return true;
				}
			}
			
			return false;
		}
		
		public static boolean ValidateUpperLetter(String password) {
			
			for(char passUpper : password.toCharArray()) {
				if(password.indexOf(passUpper) != -1) {
					return true;
				}
			}
			
			return false;
	}
}
