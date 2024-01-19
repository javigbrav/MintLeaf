package LogIn;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Book extends JFrame{
    private JTextPane textPane;
    private static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 14);
	public static String BookLength = null;
    private JButton book;
    public String title;
	public String author;
	public String description;
	public String country;
	public String language;
	public float rating; // making sense with the table in the database
	
	public String genre;
	public String region;
	public String ageGroup; // change from UML: String instead of int
	public String storyLength;
	private static int priority = 1;
	
	Object[] options = {"Rate",
    "Open"};
	
	public int votes;
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
    
	Book (String bookName){
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
                //String fileName = "book" + bookNumber; 
				// will probably need to add private JTextArea textArea;
				int result = JOptionPane.showOptionDialog(book,
					    "Languages avalaible: English and " + language+
					    "\nGenre: " + genre,
					    "Book: " + bookName,
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,     //do not use a custom Icon
					    options,  //the titles of buttons
					    options[0]); //default button title
				if (result == JOptionPane.YES_OPTION);
	                //rate ();
	            else if (result == JOptionPane.NO_OPTION)
	                new StoryInteraction (bookName);
			}
        });
	}
	static void increasePriority() {priority++;}
	public int getPriority() {return priority;}
	
	public void showThis() {
		
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
