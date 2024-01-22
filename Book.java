package StoryInteraction;

/***********************************************************************************
 * Author: Javiera Garrido Bravo
 * Date: December ??, 2023 
 * Last Modified: January 21, 2024
 * Last Modified by: Fardin Abbassi
 * Description: Class that represents a book
 ***********************************************************************************/

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import Homepage.*;
import Community.*;

public class Book extends JFrame{
    private JTextPane textPane;
    private static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 14);
	public static String BookLength = null;
    private JButton book;
    public String title;
	public String author;
	public String description;
	public String region;
	public String language;
	public String StoryOriginalText;

	public float rating; // making sense with the table in the database
	public int saved;
	private double userRating;
	private String storyText;
	private String storyOriginalText;
    private String storiesCol;
    private boolean chosenByUser = false;
    
	public String genre;
	public String age; // change from UML: String instead of int
	public String storyLength;
	private static int priority = 1;
	
	Object[] options = {"Rate", "Open"};
	
	public int votes; // again, someone please tell me what this does
//    Book (String bookName){
//		book = new JButton (bookName);
//		book.setBackground(Homepage.mintGreen2);
//        book.setPreferredSize(new Dimension(25, 40));
//        
//	}
	
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
    
	public void increasePriority() {priority++;}
	public int getPriority() {return priority;}
	
	public void bookChosen() {chosenByUser = true;}
	public boolean isChosen() {return chosenByUser;}
    
	public Book (String bookName){
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize (250, 175);
//		setLocationRelativeTo(book);
		textPane = new JTextPane();
		book = new JButton (bookName);
		book.setBackground(Homepage.mintGreen2);
        book.setPreferredSize(new Dimension(25, 4)); 
        book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Handle reading from the corresponding text file
                // String fileName = "book" + bookNumber; 
				// will probably need to add private JTextArea textArea;
				showThis(bookName);
			}
        });
	}
	//		title = "";
//		genre = "";
//		age = "";
//		rating = 0;
//		region = "";
//		storyLength = "";
		System.out.println(title);
		textPane = new JTextPane();
		book = new JButton (title);
		book.setBackground(Homepage.mintGreen2);
        book.setPreferredSize(new Dimension(25, 4)); 
        book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Handle reading from the corresponding text file
                //String fileName = "book" + bookNumber; 
				// will probably need to add private JTextArea textArea;
				showThis(title);
			}
        });
	}
	/* Method Name: showThis 
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January ??, 2024
	 * Modified Date: January ??, 2024
	 * Description: (ADD DESC HERE)
	 * @Parameters: n/a
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */   
	public void showThis(String bookName) {
		int result = 0;
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
				    bookName,
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,     //do not use a custom Icon
				    options,  //the bookNames of buttons
				    options[0]); //default button bookName
		}
		if (result == JOptionPane.YES_OPTION) {
			rateBook();
			System.out.println("works");}
        else if (result == JOptionPane.NO_OPTION)
            new StoryInteraction (bookName, storyText, storyOriginalText, language);
	}
	
	
	/* Method Name: rateBook 
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January ??, 2024
	 * Modified Date: January ??, 2024
	 * Description: (ADD DESC HERE)
	 * @Parameters: n/a
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */    
	private void rateBook() {
		// sets up components
		JFrame rateFrame = new JFrame();
		rateFrame.setLayout(new FlowLayout());
		JLabel desc = new JLabel("Rate this story out of 5:");
		JCheckBox[] ratings = new JCheckBox[5];
        ButtonGroup ratingGroup = new ButtonGroup();
        JButton submitButton = new JButton("Confirm rating");
        
        // adds everything to frame
        rateFrame.add(desc);
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

	

    public JButton getBookButton() {
        return book;
    }
    public JTextPane getTextPane() {
        return textPane;
    }
}
