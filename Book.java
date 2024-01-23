package StoryInteraction;

/***********************************************************************************
 * Author: Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified: January 22, 2024
 * Last Modified by: Victor Kosarev
 * Description: Class that represents a book and allows to interact with it
 ***********************************************************************************/

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import Homepage.*;
import Community.*;

public class Book extends JFrame{
   	private JTextPane textPane;
    	private static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 14); //default font for the body of the story
	public static String BookLength = null; //length of the book
    	private JButton book;//button of the book that is made for the homepage
    	public String title;//title of the book
	public String author;//author of the book
	public String region;//regien from where the book is from
	public String language;//language available for the book apart from english
	public float rating; // rating of the book
	public int saved;// is the book saved
	private double userRating; //rating of the user for the book
	private String storyText;//story in english
	private String storyOriginalText;//story in its original language
	private String storiesCol;//column where the story is
    	private boolean chosenByUser = false; //to save the story to the library
	public String genre; //genre of the book
	public String age; // change from UML: String instead of int
	public String storyLength; //length of the story
	private static int priority = 1; //nitial priority of the book for the user
	public Icon icon;
	
	
	Object[] options = {"Rate", "Open"};
	
	//setters and getters for variables
    	public void setStoryOriginalText (String storyOriginalText) {this.storyOriginalText = storyOriginalText;}
	public void setStoryText (String storyText) {this.storyText = storyText;}
	public void setGenre (String genre) {this.genre = genre;}
	public void setAge (String age) {this.age = age;}
	public void setLength (String storyLength) {this.storyLength = storyLength;}
    	public void setTitle (String title) {this.title = title;}
    	public void setAuthor (String author) {this.author = author;}
    	public void setRegion (String region) {this.region = region;}
    	public void setLanguage (String language) {this.language = language;}
    	public void setRating (float rating) {this.rating = rating;}
    
	public void increasePriority() {priority++;} //increases priority of the book for the user
	public int getPriority() {return priority;}//getter for the int priority
	
	public void bookChosen() {chosenByUser = true;} //if the user saves the book this method is used
	public boolean isChosen() {return chosenByUser;}//getter for the boolean chosenByUser
    
	public Book (String bookName){
		bookName = bookName.replaceAll(" ", "");
		bookName = bookName.replaceAll("\\p{Punct}", "").toUpperCase();
		
		Icon icon = new ImageIcon(bookName + ".JPEG");
		
		textPane = new JTextPane();
		book = new JButton (icon);
		book.setBackground(Homepage.mintGreen2);
        	book.setPreferredSize(new Dimension(25, 4)); 
        	book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showThis(bookName);
			}
        	});
	}
		
		textPane = new JTextPane();
		icon = new ImageIcon(title.toUpperCase().replaceAll(" ", "").replaceAll("\\p{Punct}", "") + ".JPEG");
		book = new JButton (icon);
		book.setBackground(Homepage.mintGreen2);
        	book.setPreferredSize(new Dimension(25, 4)); 
       		book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showThis(title);
			}
        	});
	}
	/* Method Name: showThis 
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 20, 2024
	 * Modified Date: January 20, 2024
	 * Description: shows a pop up that tell a general description of the book that's being read
	 * @Parameters: String bookName
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */   
	public void showThis(String bookName) {
		int result = 0; //initialize variable before
		// so  it doesn't say English and English
		if (language.equals("English")) {
			result = JOptionPane.showOptionDialog(book,
				    "Languages avalaible: English"+
				    "\nGenre: " + genre +
				    //"\nAuthor " + author +
				    "\nRegion: "+ region +
				    "\nRating " + rating,
				    bookName,
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,     //do not use a custom Icon
				    options,  //the bookNames of buttons
				    options[0]); //default button bookName
		}
		else {
			result = JOptionPane.showOptionDialog(book,
				    "Languages avalaible: English and " + language+
				    "\nGenre: " + genre +
				    //"\nAuthor " + author +
				    "\nRegion: "+ region +
				    "\nRating " + rating,
				    bookName, //title
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null, 
				    options,  //the bookNames of buttons
				    options[0]); //default button bookName
		}
		if (result == JOptionPane.YES_OPTION)
			rateBook(); // if the user decided to rate the story, calls this method
        	else if (result == JOptionPane.NO_OPTION)
            		new StoryInteraction (bookName, storyText, storyOriginalText, language); //create a new story interaction if the user decides to open the book
	}
	
	
	/* Method Name: rateBook 
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 19, 2024
	 * Modified Date: January 21, 2024
	 * Description: user can rate the book that is being read
	 * @Parameters: n/a
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: Community.rateStories()
	 * Throws/Exceptions: n/a
	 */    
	private void rateBook() {
		// sets up components
		JFrame rateFrame = new JFrame(); //create a new frame to rate
		rateFrame.setLayout(new FlowLayout());
		JLabel desc = new JLabel("Rate this story out of 5:");
		JCheckBox[] ratings = new JCheckBox[5];
	        ButtonGroup ratingGroup = new ButtonGroup();
	        JButton submitButton = new JButton("Confirm rating");
	        
	        // adds everything to frame
	        rateFrame.add(desc);
		//creates 5 check boxes and adds numbers from 1 to 5 to them
	        for (int i = 0; i < 5; i++) {
	        	ratings[i] = new JCheckBox(String.valueOf(i+1));
	        	ratingGroup.add(ratings[i]);
	            rateFrame.add(ratings[i]);
	            ratings[i].setFont(new Font("Calibri", Font.PLAIN, 15));
	        }
	        rateFrame.add(submitButton);
	        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        rateFrame.setVisible(true);
	        rateFrame.setPreferredSize(new Dimension(25, 4)); 
	        rateFrame.setSize(500,75);
	        rateFrame.setLocationRelativeTo(book);
	        
	        // submit button listener
	        submitButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	// looks through each checkbox to find user rating
	                for (int i = 0; i < ratings.length; i++) {
	                    if (ratings[i].isSelected()) {
	                        userRating = Double.parseDouble(ratings[i].getText());
	                        break;
	                    }
	                }
	
	                // if user adds a rating, dispose frame, add user rating to database
	                if (userRating != 0) {
	                    rateFrame.dispose();
	                    Community.rateStory(userRating, title);;
	                }
	            }
	        });
	}	
	//getter for the button to add in the button grid in the homepage
    	public JButton getBookButton() {
       		return book;
    }
}
