import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	public static String username;
	public static String password;
	public static String genre;
	public static String region;
	public static int age;
	public static String storyLength;
	
	private static Connection con;
	
	// Constructor to create connection and stuff
	User(String username, String password){
			try {
				this.username = username;
				this.password = password;
				/** When doing this locally, swap "MintLeaf" with your local password**/
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
				System.out.println("Connection Successful");
			} catch (SQLException e) {
				System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Creating User");
			}
	
	}
	
	// add new account
	void createConnection(String Username, String Password, String Genre, String Region, int Age,
			String StoryLength) {
		try {
			// trying to connect to database
			/** Again, when doing this locally, swap "MintLeaf" with your local password**/
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
			System.out.println("Database Connection Successful");

			//saving user log in and preference info to the databae
			Statement stat = con.createStatement();
			stat.execute("INSERT INTO USERNAMES(users, passwords, genre, region, age, storylength) VALUES('" + Username + "', '" + Password + "', '"
					+ Genre + "', '" + Region + "', " + Age + ", '" + StoryLength + "')");
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
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Creating User");
		}
	}
	
	// search database and log in
	static boolean searchDataBase(String Username, String Password) {
		try {
			/** Once again, when doing this locally, swap "MintLeaf" with your local password**/
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"MintLeaf");
			System.out.println("Database Connection Successful");
			Statement stat2 = con.createStatement();
			ResultSet rs = stat2.executeQuery("SELECT * FROM USERNAMES WHERE users like '" + Username
					+ "' and passwords like '" + Password + "'");
			if (rs.next()) {
				do {
					username = rs.getString("users");
					password = rs.getString("passwords");
					genre = rs.getString("genre");
					region = rs.getString("region");
					age = rs.getInt("age");
					storyLength = rs.getString("storyLength");
				} while (rs.next());
				return true;
			} else {
				System.out.println("user not found");
			}
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Searching database");
		}
		return false;
	}

	// search for existing user
	static boolean searchForExistingUser(String username) {
		/** Once more again, when doing this locally, swap "MintLeaf" with your local password**/
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
			System.out.println("Database Connection Successful");
			Statement stat3 = con.createStatement();
			ResultSet rs = stat3.executeQuery("SELECT * FROM USERNAMES WHERE users like '" + username + "'");
			if(rs.next())	return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL; Finding existing user");
			System.err.print(e);
		}
		return false;
	}
	
	// update string columns
	static void updateDataBase(String column, String New) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			// telling database to update a specific column and row
			// only for strings
			Statement stat = con.createStatement();
			stat.execute("UPDATE USERNAMES SET " + column + "= '" + New + "' WHERE users LIKE '" + username+"' AND passwords LIKE '"+password+"'");
			stat.close();
		} 
		// error connecting to database
		catch (SQLException e) {
			System.err.println("ERROR - NO CONNECTION");
		}
	}
	
	//update age
	static void updateDataBase(int Age) {
		try {
			// connecting to database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
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
	public static void changeUsername(String Username) {
		updateDataBase("users", Username);
		username = Username;
	}

	// changing client password
	public static void changePassword(String Password) {
		updateDataBase("passwords" , Password);
		password = Password;
	}

	// methods from here on are used to update preferences
	public static void changeGenre(String Genre) {
		updateDataBase("genre", Genre);
		genre = Genre;
	}

	// changing user region
	public static void changeRegion(String Region) {
		updateDataBase("region", Region);
		region = Region;
	}

	//changing user age
	public static void changeAge(int Age) {
		updateDataBase(age);
		age = Age;
	}

	//changing users preffered story length
	public static void changeStoryLength(String StoryLength) {
		updateDataBase("storyLength", StoryLength);
		storyLength = StoryLength;
	}
	// To be used after the rec quiz
	public static void updatePreferences(String[] preferences) {
		genre = preferences[0];
		region = preferences[1];
		age = Integer.parseInt(preferences[2]);
		storyLength = preferences[3];
		updateDataBase(age);
		updateDataBase("genre", genre);
		updateDataBase("region", region);
		updateDataBase("storyLength", storyLength);
	}
	
//	private static void addBookToLibrary(book Book) {
//		books.add(Book);
//	}

	// testing method to make sure all fields are what they should be 
	private static void printAll() {
		System.out.println(username + " " + password + " " + genre + " " + region + " " + age + " " + storyLength);
	}
}
