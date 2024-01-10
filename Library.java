package Homepage;

import java.util.*;
import StoryInteraction.*;

public class Library{
	private LinkedList<User> users;
	private LinkedList<Story> stories;
	
	// constructor
	public Library(){
		users = null;
		stories = null;
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
	
	void organizeBookshelf() {
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
}
