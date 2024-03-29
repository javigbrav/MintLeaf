package StoryInteraction;

import java.util.LinkedList;

public class Story{
	public String title;
	public String author;
	public String description;
	public String country;
	public float rating; // making sense with the table in the database
	
	public String genre;
	public String region;
	public String ageGroup; // change from UML: String instead of int
	public String storyLength;
	
	private int priority = 1;
	public void increasePriority() {priority++;}
	public int getPriority() {return priority;}
	
	
	public int votes;
	public LinkedList<Bookmark> bookmarkList;
	public LinkedList<Annotations> annotationList;
	
	public LinkedList<Bookmark> getBookmark(){
		// add stuff here
		return bookmarkList;
	}
	public void addBookmark(){
		// add stuff here
	}
	
	public LinkedList<Annotations> getAnnotation(){
		// add stuff here
		return annotationList;
	}
	public void addAnnotation(){
		// add stuff here		
	}
	
	public void scroll() {
		// add stuff here
	}
	
	public void translateTo(String language) {
		// add stuff here
	}
	
	public void addVotes(int votesToAdd) {
		// add stuff here
	}
	
	public void changeFont() {
		// add stuff here
	}

	public void darkMode() {
		// add stuff here
	}
}
