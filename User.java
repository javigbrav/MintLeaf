package mintLeaf;

import java.util.List;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement; 

class User {
	private static String username;
	private static String password;
	Boolean isPremium;

	//creating account
	void createAccount(String Username, String Password){
		try {
			Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb","root", "Mlgnoscope106!");
			System.out.println("Database Connection Successful");
			Statement stat = con.createStatement();
			stat.execute("INSERT INTO USERNAMES(users, passwords) VALUES('" + Username+"', '" + Password + "'");
			stat.close();
			
		} catch(SQLException e) {
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL");
		}
	}

	//looking for account
	static void searchDataBase(String Username, String Password) {
		try {
			Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb","root", "Mlgnoscope106!");
			Statement stat2 = con.createStatement();
			ResultSet rs = stat2.executeQuery("SELECT * FROM USERNAMES WHERE users like '"+Username+"' and passwords like '"+Password+"'");
			while(rs.next()) {
				username = rs.getString("users");
				String password = rs.getString("passwords");
			}
		} catch(SQLException e) {
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL");
		}
	}
	
	List <Book> books = new ArrayList<book>();
	User(){
		username = "";
		password = "";		
		isPremium = false;
	}
	
	User(String Username, String Password, boolean IsPremium){
		username = Username;
		password = Password;
		isPremium = IsPremium;
	}
	
	private void changeUsername(String Username) {
		username = Username;
	}
	
	private void changePassword(String Password) {
		password = Password;
	}
	
	private void upgradeUser(String Username, String Password) {
		isPremium = true;
	}
	
	private void addBookToLibrary(book Book) {
		books.add(Book);
	}
	
	public static void main(String[] args) {
		searchDataBase("VICTOR", "MyPassword");
	}
	
}
