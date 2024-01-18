import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Community{
	private double rating = 0;
  
	public Community(){
		this.rating = 0;
	}

	/**A TreeNode object represents a single book node in a binary(?) tree. **/
	private static class TreeNode {
		Story book; 			// The book.
		String genre;      		// The book's genre.
		String region;    		// The book's region.
		String ageRange;     	// The book's age range.
		String length;     		// The book's length.
		int priority;			// The book's relevance to the user.
		
		TreeNode left;    // Pointer to left subtree.
		TreeNode right;   // Pointer to right subtree.
		TreeNode(Story book, String genre, String region, String ageRange, String length, int priority) { // Constructor.
			// Make a node containing the specified string.
			// Note that left and right pointers are initially null.
			this.book = book;
			this.genre = genre;
			this.region = region;
			this.ageRange = ageRange;
			this.length = length;
			this.priority = priority;
		}
	}  // end TreeNode class
	private static TreeNode root;
 
	/**MAIN IDEA: Make a binary tree based on questions from rec quiz, increase story priority based on matches to user preference**/
	/* Method Name: tailoredReccomendations 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 09, 2024
	 * Modified Date: January ??, 2024
	 * Description: Makes a binary tree based on the user's answers to questions from the recommendation quiz, 
	 * 				increase story priority based on matches to user preference, ADD OTHER THINGS HERE.
	 * @Parameters: LinkedList<Story> booksToSort, User user
	 * @Return Value: LinkedList<Story>
	 * Data Type: ???
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */    
	public LinkedList<Story> tailoredRecomendations(LinkedList<Story> booksToSort, User user) {
		// add each story in the linked list into a binary tree
  		for (Story story : booksToSort) {
  			// if root is null, make the root the only node in the tree
  			if(root == null) {
  				root = new TreeNode(story, story.genre, story.region, story.ageGroup, story.storyLength, story.getPriority());
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
  						if(story.genre == user.genre)
  							story.increasePriority();
  						break;
					  
  					case 1: // comparing to user's region preference
  						if(story.region == user.region)
  							story.increasePriority();
  						break;
					  
  					case 2: // comparing to user's age
  						/** For future reference, each story's age group should be something like "00-13" **/
  						int age1 = Integer.parseInt(story.ageGroup.substring(0,1));
  						int age2 = Integer.parseInt(story.ageGroup.substring(2));
  						if(age1 < user.age && user.age > age2)
  							story.increasePriority();
  						break;
					  
  					case 3: // comparing to user's story length preference
  						if(story.storyLength == user.storyLength)
  							story.increasePriority();
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
  				if(story.getPriority() >= runner.priority) {
  					if(runner.left == null) {
  						runner.left = new TreeNode(story, story.genre, story.region, story.ageGroup, story.storyLength, story.getPriority());
  						break;
  					}
  					// If the left node of the runner is not null, set runner to left node
  					else runner = runner.left;
  				} // end if
			  
  				// If the story is less relevant to the user than the runner, bring it to the right subtree of the runner
  				else {
  					if (runner.right == null) {
  						runner.right = new TreeNode(story, story.genre, story.region, story.ageGroup, story.storyLength, story.getPriority());
  						break;
  					}
  					// If the right node of the runner is not null, set runner to right node
  					else runner = runner.right;
  				} // end else
  			} // end while
  		} // end for loop
  		
  		// sorts the binary tree of books to accomodate the user
  		LinkedList<Story> recommendedBooks = new LinkedList<Story>();
  		recommendedList(root, recommendedBooks);
  		return recommendedBooks;
    }
	/* Method Name: recommendedList 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 12, 2024
	 * Modified Date: January 12, 2024
	 * Description: Goes inorder through a binary tree, and sorts books into the given linked list of stories
	 * @Parameters: TreeNode node, LinkedList<Story> stories
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */ 
	private void recommendedList(TreeNode node, LinkedList<Story> stories) {
		if(node != null) {
			recommendedList(node.left, stories);
			stories.add(node.book);
			recommendedList(node.right, stories);			
		}
	}

  	
  	/**MAIN IDEA: Preface this by adding individual story pages, then add stars that act as buttons that lets a user do this.
  				  Depending on input rating, change user rating of the story to match. (Basic idea as of now)**/
	/** Might take this idea and move it into the star buttons themselves if/when they become available **/
  	public void rateStory(double userRating) {
         
    }
    
  	/**MAIN IDEA: Based on the above method, take all user ratings for a given story and make an average rating for the given story.**/
    public double averageRating() {
        
        return rating;
    }

  	/**MAIN IDEA: Based on the above method, look through every story's ratings and create a top 10(?) list**/
    public void adjustRankings() {
        
    }

  	
    /**MAIN IDEA: 	Preface this by having a story page for each story.
    				Have a button that allows a user to add a report to a table in the database for reports. 
    				Reports should track which user created the report, which story the report is in, and the contents of the report.
    				(Basic idea as of now, could possibly just be a part of the story page's class instead)**/
 	public void reportStory(String user, String storyName, String report) {
        
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
}

