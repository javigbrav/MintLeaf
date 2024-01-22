package Community;

/***********************************************************************************
 * Author: Fardin Abbassi
 * Date: December 20, 2023 
 * Last Modified: January 21, 2024
 * Last Modified by: Fardin Abbassi
 * Description: A class full of methods for user/system interactions
 ***********************************************************************************/

import java.sql.*;
import java.util.LinkedList;
import Homepage.*;
import StoryInteraction.Book;

public class Community{
	private double rating = 0;
  
	public Community(){
		this.rating = 0;
	}

	/**A TreeNode object represents a single book node in a binary(?) tree. **/
	private static class TreeNode {
		Book book; 			// The book.
		int priority;		// The book's relevance to the user.
		
		TreeNode left;    // Pointer to left subtree.
		TreeNode right;   // Pointer to right subtree.
		// 
		TreeNode(Book book, int priority) { // Constructor.
			this.book = book;
			this.priority = priority;
		}
	}  // end TreeNode class
	private static TreeNode root;
 
	/**MAIN IDEA: Make a binary tree based on questions from rec quiz, increase story priority based on matches to user preference**/
	/* Method Name: tailoredReccomendations 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 09, 2024
	 * Modified Date: January 21, 2024
	 * Description: Makes a binary tree based on the user's answers to questions from the recommendation quiz, 
	 * 				increase story priority based on matches to user preference, then sends the tailored list back.
	 * @Parameters: LinkedList<Book> booksToSort, User user
	 * @Return Value: LinkedList<Book>
	 * Data Type: ???
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */    
	public static LinkedList<Book> tailoredRecomendations(LinkedList<Book> booksToSort, User user) {
		// add each story in the linked list into a binary tree
  		for (Book book : booksToSort) {
  			// if root is null, make the root the only node in the tree
  			if(root == null) {
  				root = new TreeNode(book, book.getPriority());
  			}
		  
  			// set runner to root's value
  			TreeNode runner;
  			runner = root;
  			int preferenceToCheck = 0;
  			boolean finishedChecks = false;
  			
  			// increase priority based on how the story matches the user's preferences
  			while(!finishedChecks) {
  				switch(preferenceToCheck) {
  					case 0: // comparing to user's genre preference
  						if(book.genre == user.genre)
  							book.increasePriority();
  						break;
					  
  					case 1: // comparing to user's region preference
  						if(book.region == user.region)
  							book.increasePriority();
  						break;
					  
  					case 2: // comparing to user's age
  						/** For future reference, each story's age group should be something like "00-13" **/
  						String ages = book.ageGroup;
  						int age1 = 0;
  						int age2 = 0;
  						switch (ages) {
	  						case "Child":
	  							age1 = 0;
	  							age2 = 15;
	  						case "Young Adult":
	  							age1 = 15;
	  							age2 = 25;
	  						case "Adult":
	  							age1 = 25;
	  							age2 = 100;
  						}
  						if(age1 <= user.age && user.age > age2)
  							book.increasePriority();
  						break;
					  
  					case 3: // comparing to user's story length preference
  						if(book.storyLength == user.storyLength)
  							book.increasePriority();
  						break;
					  
  					case 4: // finish priority checks
  						finishedChecks = !finishedChecks;
  						break;
  				} // end switch case
  				preferenceToCheck++;
  			} // end while loop
		  
  			// add the story to the binary tree based on priority found above
  			while(true) {
  				// If the story is more relevant to the user than the runner, bring it to the left subtree of the runner
  				if(book.getPriority() >= runner.priority) {
  					if(runner.left == null) {
  						runner.left = new TreeNode(book, book.getPriority());
  						break;
  					}
  					// If the left node of the runner is not null, set runner to left node
  					else runner = runner.left;
  				} // end if
			  
  				// If the book is less relevant to the user than the runner, bring it to the right subtree of the runner
  				else {
  					if (runner.right == null) {
  						runner.right = new TreeNode(book, book.getPriority());
  						break;
  					}
  					// If the right node of the runner is not null, set runner to right node
  					else runner = runner.right;
  				} // end else
  			} // end while
  		} // end for loop
  		
  		// sorts the binary tree of books to accomodate the user
  		LinkedList<Book> recommendedBooks = new LinkedList<Book>();
  		recommendedList(root, recommendedBooks);
  		return recommendedBooks;
    }
	/* Method Name: recommendedList 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 12, 2024
	 * Modified Date: January 12, 2024
	 * Description: Goes inorder through a binary tree, and sorts books into the given linked list of stories
	 * @Parameters: TreeNode node, LinkedList<Book> stories
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */ 
	private static void recommendedList(TreeNode node, LinkedList<Book> stories) {
		if(node != null) {
			recommendedList(node.left, stories);
			stories.add(node.book);
			recommendedList(node.right, stories);			
		}
	}

  	
  	/**MAIN IDEA: Preface this by adding individual book pages, then add stars that act as buttons that lets a user do this.
  				  Depending on input rating, change user rating of the book to match. (Basic idea as of now)**/
	/** Might take this idea and move it into the star buttons themselves if/when they become available **/
  	public static void rateStory(double userRating, String title) {
		try {
	 		/** When doing this locally, swap "MintLeaf" with your local password**/
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
			Statement stat = con.createStatement();
			stat.execute("UPDATE stories SET UserRating = " + userRating + " WHERE Title = '" + title + "'");
			stat.close();
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Rating story");
		}
    }
    
  	/**MAIN IDEA: Based on the above method, take all user ratings for a given story and make an average rating for the given story.**/
    public double averageRating() {
        
        return rating;
    }

  	/**MAIN IDEA: Based on the above method, look through every story's ratings and create a top 10(?) list**/
    public void adjustRankings() {
        
    }

  	
    /**MAIN IDEA: 	Preface this by having a book page for each story.
    				Have a button that allows a user to add a report to a table in the database for reports. 
    				Reports should track which user created the report, which story the report is in, and the contents of the report.
    				(Basic idea as of now, could possibly just be a part of the story page's class instead)**/
	/* Method Name: reportStory 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 18, 2024
	 * Modified Date: January ??, 2024
	 * Description: Creates a report from the user to be uploaded into a reports table in the database.
	 * @Parameters: String user, String storyName, String report
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */ 
 	public static void reportStory(String user, String storyName, String report) {
		try {
	 		/** When doing this locally, swap "MintLeaf" with your local password**/
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
			Statement stat = con.createStatement();
			stat.execute("INSERT INTO REPORTS(User, StoryName, Report) VALUES('" + user + "', '" + storyName + "', '"+ report + "')");
			stat.close();
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Creating Report");
			System.err.print(e);
		}
    }

  	/**MAIN IDEA: 	Preface this by having a functioning profile page.
  	  				Have a button that allows the user to suggest a book/story for us to potentially add to the currently available stories.
  	  				Suggestions, like the reports above, should track user in addition to whichever story they want to track.
  	  				(Basic idea as of now, could possibly just be a part of the profile class instead)**/
/* 	public void suggestStory() {
        
    }

  	/**MAIN IDEA: ???**/
    /** Maybe change this up so that it provides a message out to the user if their request is accepted/denied? **/
/*	public boolean isApproved() {
    	
        boolean approved = false;
        return approved;
    }								*/

 	
 	
 	/** TEST AREA **/
 	public static void main(String[] args) {
 		Community.rateStory(4.5, "Ramayana");
 		Community.reportStory("test", "Aladdin", "Improper translation at line 8");
 	}
 	
 	
}
