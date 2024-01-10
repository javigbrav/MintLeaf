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
	private static String genre;
	private static String region;
	private static int age;
	private static String storyLength;
	
	// add new account
	void createConnection(String Username, String Password, String Genre, String Region, int Age,
			String StoryLength) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			System.out.println("Database Connection Successful");
			Statement stat = con.createStatement();
			stat.execute("INSERT INTO USERNAMES(users, passwords) VALUES('" + Username + "', '" + Password + "', '"
					+ Genre + "', '" + Region + "', " + age + ", '" + StoryLength + "'");
			stat.close();

			username = Username;
			password = Password;
			genre = Genre;
			region = Region;
			age = Age;
			storyLength = StoryLength;

		} catch (SQLException e) {
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL");
		}
	}
	
	// search database and log in
	static void searchDataBase(String Username, String Password) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			Statement stat2 = con.createStatement();
			ResultSet rs = stat2.executeQuery("SELECT * FROM USERNAMES WHERE users like '" + Username
					+ "' and passwords like '" + Password + "'");
			while (rs.next()) {
				username = rs.getString("users");
				password = rs.getString("passwords");
				genre = rs.getString("genre");
				region = rs.getString("region");
				age = rs.getInt("age");
				storyLength = rs.getString("storyLength");
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR - CONNECTION UNSUCCESSFUL");
		}
	}

	// update string columns
	static void updateDataBase(String column, String New) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			Statement stat = con.createStatement();
			stat.execute("UPDATE USERNAMES SET " + column + "= '" + New + "' WHERE users LIKE '" + username+"' AND passwords LIKE '"+password+"'");
			stat.close();
		} catch (SQLException e) {
			System.out.println("ERROR - NO CONNECTION");
		}
	}
	
	//update age
	static void updateDataBase(int Age) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root",
					"Mlgnoscope106!");
			Statement stat = con.createStatement();
			stat.execute("UPDATE USERNAMES SET age = " + Age + "WHERE users LIKE '" + username+"' AND passwords LIKE '" + password + "'");
			stat.close();
		} catch(SQLException e) {
			System.out.println("ERROR - NO CONNECTION");
		}
	}
//	List<Book> books = new ArrayList<book>();

	User() {
		username = "";
		password = "";
		genre = "";
		region = "";
		age = 0;
		storyLength = "";
	}

	private static void fillUser(String Username, String Password, String Genre, String Region, int Age, String StoryLength) {
		username = Username;
		password = Password;
		genre = Genre;
		region = Region;
		age = Age;
		storyLength = StoryLength;
	}

	private static void changeUsername(String Username) {
		username = Username;
		updateDataBase("users", Username);
	}

	private static void changePassword(String Password) {
		password = Password;
		updateDataBase("passwords" , Password);
	}

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

	private static void printAll() {
		System.out.println(username + " " + password + " " + genre + " " + region + " " + age + " " + storyLength);
	}
}
