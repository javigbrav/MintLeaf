import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;
import Homepage.*;
import StoryInteraction.Story;

public class Community{
	private double rating = 0;
  
	public Community(){
		this.rating = 0;
	}

	/**A TreeNode object represents a single book node in a binary(?) tree. **/
	private static class TreeNode {
		String genre;      	// The book's genre.
		String region;    	// The book's region.
		String ageRange;     	// The book's age range.
		String length;     	// The book's length.
		int priority;			// The book's relevance to the user.
		
		TreeNode left;    // Pointer to left subtree.
		TreeNode right;   // Pointer to right subtree.
		TreeNode(String genre, String region, String ageRange, String length, int priority) { // Constructor.
			// Make a node containing the specified string.
			// Note that left and right pointers are initially null.
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
	 * Description: Makea a binary tree based on the user's answers to questions from the recommendation quiz, 
	 * 				increase story priority based on matches to user preference, ADD OTHER THINGS HERE.
	 * @Parameters: LinkedList<Story> booksToSort, User user
	 * @Return Value: void???
	 * Data Type: ???? 
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */    
	public void tailoredReccomendations(LinkedList<Story> booksToSort, User user) {
		// add each story in the linked list into a binary tree
  		for (Story story : booksToSort) {
  			// if root is null, make the root the only node in the tree
  			if(root == null) {
  				root = new TreeNode(story.genre, story.region, story.ageGroup, story.storyLength, story.getPriority());
  			}
		  
  			TreeNode runner;
  			runner = root;
  			int preferenceToCheck = 0;
  			boolean finishedChecks = false;
  			
  			while(!finishedChecks) {
  				switch(preferenceToCheck) {
  					case 0: // comparing to user's genre preference
  						if(story.genre == user.getPreferences()[0])
  							story.increasePriority();
  						break;
					  
  					case 1: // comparing to user's region preference
  						if(story.region == user.getPreferences()[1])
  							story.increasePriority();
  						break;
					  
  					case 2: // comparing to user's age range preference
  						if(story.ageGroup == user.getPreferences()[1])
  							story.increasePriority();
  						break;
					  
  					case 3: // comparing to user's story length preference
  						if(story.storyLength == user.getPreferences()[1])
  							story.increasePriority();
  						break;
					  
  					case 4: // finish priority checks
  						finishedChecks = !finishedChecks;
  						break;
  				} // end switch case
  				preferenceToCheck++;
  			} // end while loop
		  
  			while(true) {
  				// If the story is more relevant to the user than the runner, bring it to the left subtree of the runner
  				if(story.getPriority() >= runner.priority) {
  					if(runner.left == null) {
  						runner.left = new TreeNode(story.genre, story.region, story.ageGroup, story.storyLength, story.getPriority());
  						break;
  					}
  					// If the left node of the runner is not null, set runner to left node
  					else runner = runner.left;
  				} // end if
			  
  				// If the story is less relevant to the user than the runner, bring it to the right subtree of the runner
  				else {
  					if (runner.right == null) {
  						runner.right = new TreeNode(story.genre, story.region, story.ageGroup, story.storyLength, story.getPriority());
  						break;
  					}
  					// If the right node of the runner is not null, set runner to right node
  					else runner = runner.right;
  				} // end else
  			} // end while
  		} // end for loop
  		
  		/** NEXT STEPS: Need to find a way to make this list back into the library class**/
  		
    }

  	
  	/**MAIN IDEA: Preface this by adding individual story pages, then add a button that lets a user do this.
  				  Depending on user rating, change user rating of this story to match. (Basic idea as of now)**/
  	public void rateStory(double userRating) {
        
    }
    
  	/**MAIN IDEA: ???**/
    public double averageRating() {
        
        return rating;
    }

  	/**MAIN IDEA: ???**/
    public void adjustRankings() {
        
    }

  	/**MAIN IDEA: ???**/
    public void reportStory() {
        
    }

  	/**MAIN IDEA: ???**/
    public void suggestStory() {
        
    }

  	/**MAIN IDEA: ???**/
    public boolean isApproved() {
    	
        boolean approved = false;
        return approved;
    }
}

