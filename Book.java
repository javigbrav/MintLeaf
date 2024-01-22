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
    public static String BookLength = null; // someone please tell me what this does
    private JButton book;
    public String title;
	public String author;
	public String description;
	public String country;
	public String language;
	public float rating; // making sense with the table in the database
	private double userRating = 0;
	private boolean chosenByUser = false;

	public String genre;
	public String region;
	public String ageGroup; // change from UML: String instead of int
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
    public void setTitle (String title) {this.title = title;}
    public void setAuthor (String author) {this.author = author;}
    public void setCountry (String country) {this.country = country;}
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
	public Book() {
		title = "";
		genre = "";
		ageGroup = "";
		rating = 0;
		region = "";
		storyLength = "";
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
		int result = JOptionPane.showOptionDialog(book,
			    "Languages avalaible: English and " + language+
			    "\nGenre: " + genre,
			    "Book: " + bookName,
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[0]); //default button title
		if (result == JOptionPane.YES_OPTION) {
			rateBook();
		}
        else if (result == JOptionPane.NO_OPTION)
            new StoryInteraction (bookName);
	}
	
//    public Book(String bookName) {
//    	
//        textPane = new JTextPane();
//        setDefaultFont();
//
//        book = new JButton(bookName);
//        book.setBackground(Homepage.mintGreen2);
//        book.setPreferredSize(new Dimension(25, 4));
//
//        openAndDisplayFile(bookName);
//
//        book.addActionListener(e -> {
//            try {
//                readFile(bookName);
//                Homepage.updateTextArea(textPane.getText());
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(book);
//        // Add buttonPanel to your frame or panel as needed
//    }

//  public void readFile(String fileName) {
//	 try {
//        BufferedReader reader = new BufferedReader(new FileReader(fileName));
//        StringBuilder content = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            content.append(line).append("\n");
//        }
//        reader.close();
//        textPane.setText(content.toString());
//        JOptionPane.showMessageDialog(null, new JScrollPane(textPane), "File Content", JOptionPane.PLAIN_MESSAGE);
//    } catch (FileNotFoundException e) {
//   	 e.printStackTrace();
//        JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
//    }
//	 catch (IOException e) {
//		 JOptionPane.showMessageDialog(null, "Error reading the file", e.toString(), JOptionPane.ERROR_MESSAGE);
//
//    }
//}
    public JButton getBookButton() {
        return book;
    }
    public JTextPane getTextPane() {
        return textPane;
    }
}
