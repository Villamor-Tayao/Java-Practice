package CRUDPractice.Models;

public class UserInformation {
	
	private int UserID;
	private String Fname;
	private String Lname;
	private int age;
	private String country;
	private String email;
	private String contact;
	
	// Getters
	public int getUserID() {
		return UserID;
	}
	
	public String getFname() {
		return Fname;
	}
	
	public String getLname() {
		return Lname;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getContact() {
		return contact;
	}
	
	// Setters
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
	public void setFname(String Fname) {
		this.Fname = Fname;
	}
	
	public void setLname(String Lname) {
		this.Lname = Lname;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
