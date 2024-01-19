/***********************************************************************************
 * Author: Fardin Abbassi
 * Date: January 10, 2024 
 * Last Modified: January 19, 2024
 * Last Modified by: Fardin Abbassi
 * Description: A library that stores stories found in the database. (might need to elaborate)
 ***********************************************************************************/

package Homepage;

import java.sql.*;
import java.util.*;

import Community.Community;
import StoryInteraction.*;

public class Library{
	private static LinkedList<User> users = new LinkedList<User>();
	private static LinkedList<Story> stories = new LinkedList<Story>();
	private Connection con;
	
	// constructor
	public Library(){
		try {
			/** When doing this locally, swap "MintLeaf" with your local password**/
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
			System.out.println("Connection Successful");
			
			Statement stat = con.createStatement();
			// CONTINUE FROM HERE
			ResultSet rs1 = stat.executeQuery("SELECT * FROM stories");
			while(rs1.next()) {
				Story storyToAdd = new Story();
				storyToAdd.title = rs1.getString("Title");
				storyToAdd.genre = rs1.getString("Genre");
				storyToAdd.ageGroup = rs1.getString("Age");
				storyToAdd.rating = rs1.getFloat("Rating");
				storyToAdd.region = rs1.getString("Region");
				storyToAdd.storyLength = rs1.getString("Length");
				stories.add(storyToAdd);
			}
			
			ResultSet rs2 = stat.executeQuery("SELECT * FROM usernames");
			while(rs2.next()) {
				String username = rs2.getString("users");
				String userPassword = rs2.getString("passwords");
				User userToAdd = new User(username, userPassword);
				System.out.println(username);
				users.add(userToAdd);
			}
			
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Connecting to stories");
			System.err.println(e);
		}
		users.add(null);
	}
	
	List<Story> searchStory() {
		// add stuff
		return stories;
	}
	
	List<Story> searchByFilter(){
		// add stuff
		return stories;
	}
	
	void saveToBookshelf() {
		// add stuff		
	}
	
	void removeFromBookshelf() {
		// add stuff
	}
	
	/** CURRENT IDEA: Go through each user  **/
	/* Method Name: organizeBookshelf 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 18 2024
	 * Modified Date: January 19, 2024
	 * Description: Checks through each user to see which user to tailor to, sorts stories based on relevance to user
	 * @Parameters: String usernameToSort
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */ 
	static void organizeBookshelf(String usernameToSort) {
		for (User user: users) {
			if(user.username == usernameToSort) {
				user.searchDataBase(user.username, user.password);
				Community.tailoredRecomendations(stories, user);
				break;
			}
		}
		// add stuff
	}
	
	String[] displayTopFive() {
		// add stuff
		return null;
	}
	
	void renameBookshelf() {
		// add stuff		
	}

	public LinkedList<Story> getStories() {
		return stories;
	}	
	
	// test area
	public static void main (String[] args) {
		Library l = new Library();
		for (Story story: l.stories) {
			System.out.println(story.title);
		}
		System.out.println();
		for (User user: l.users) {
			System.out.println(user.username);
		}
		System.out.println();
		User checking = users.peek();
		System.out.println(checking.username);
	}
}
