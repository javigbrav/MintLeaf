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
	public static String username;
	public static String password;
	public static String genre;
	public static String region;
	public static int age;
	public static String storyLength;
	
	// add new account
	void createConnection(String Username, String Password, String Genre, String Region, int Age,
			String StoryLength) {
		try {
			// trying to connect to database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			System.out.println("Database Connection Successful");

			//saving user log in and preference info to the databae
			Statement stat = con.createStatement();
			stat.execute("INSERT INTO USERNAMES(users, passwords) VALUES('" + Username + "', '" + Password + "', '"
					+ Genre + "', '" + Region + "', " + age + ", '" + StoryLength + "'");
			stat.close();

			// setting up this instance
			username = Username;
			password = Password;
			genre = Genre;
			region = Region;
			age = Age;
			storyLength = StoryLength;
		} 
		// error connecting to database
		catch (SQLException e) {
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL");
		}
	}
	
	// search database and log in
	static void searchDataBase(String Username, String Password) {
		try {
			// connecting to database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");

			//searching for log in details in database
			Statement stat2 = con.createStatement();
			ResultSet rs = stat2.executeQuery("SELECT * FROM USERNAMES WHERE users like '" + Username
					+ "' and passwords like '" + Password + 
			// retrieving preferences and adding to this instance
			while (rs.next()) {
				username = rs.getString("users");
				password = rs.getString("passwords");
				genre = rs.getString("genre");
				region = rs.getString("region");
				age = rs.getInt("age");
				storyLength = rs.getString("storyLength");
			}
		} 
		// error connecting to database
		catch (SQLException e) {
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL");
		}
	}

	// update string columns
	static void updateDataBase(String column, String New) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			// telling database to update a specific column and row
			// only for strings
			Statement stat = con.createStatement();
			stat.execute("UPDATE USERNAMES SET " + column + "= '" + New + "' WHERE users LIKE '" + username+"' AND passwords LIKE '"+password+"'");
			stat.close();
		} 
		// error connecting to database
		catch (SQLException e) {
			System.out.println("ERROR - NO CONNECTION");
		}
	}
	
	//update age
	static void updateDataBase(int Age) {
		try {
			// connecting to database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			Statement stat = con.createStatement();

			//updating age
			stat.execute("UPDATE USERNAMES SET age = " + Age + "WHERE users LIKE '" + username+"' AND passwords LIKE '" + password + "'");
			stat.close();
		} 
		// error connecting to database
		catch(SQLException e) {
			System.out.println("ERROR - NO CONNECTION");
		}
	}
//	List<Book> books = new ArrayList<book>();

	// method for testing purposes, so we can make whatever we want the user of this instance
	private static void fillUser(String Username, String Password, String Genre, String Region, int Age, String StoryLength) {
		username = Username;
		password = Password;
		genre = Genre;
		region = Region;
		age = Age;
		storyLength = StoryLength;
	}

	// changing client username
	private static void changeUsername(String Username) {
		username = Username;
		updateDataBase("users", Username);
	}

	// changing client password
	private static void changePassword(String Password) {
		password = Password;
		updateDataBase("passwords" , Password);
	}

	// methods from here on are used to update preferences
	private static void changeGenre(String Genre) {
		genre = Genre;
		updateDataBase("genre", Genre);
	}
	
	private static void changeRegion(String Region) {
		region = Region;
		updateDataBase("region", Region);
	}
	
	private static void changeAge(int Age) {
		age = Age;
		updateDataBase(age);
	}
	
	private static void changeStoryLength(String StoryLength) {
		storyLength = StoryLength;
		updateDataBase("storyLength", StoryLength);
	}

//	private static void addBookToLibrary(book Book) {
//		books.add(Book);
//	}

	// testing method to make sure all fields are what they should be 
	private static void printAll() {
		System.out.println(username + " " + password + " " + genre + " " + region + " " + age + " " + storyLength);
	}
}
