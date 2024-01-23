/***********************************************************************************
 * Authors: Zainab Siddiqui, Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified: January 22, 2024
 * Last Modified by: Fardin Abbassi
 * Description: A user's home page in the application
 ***********************************************************************************/

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class StoryInteraction extends JFrame {
	
	//static Color initialColor = Color.yellow;
	static boolean hasBeenClickedT = false; //to check if the translator button has already been clicked, default at false
	private static JScrollPane scrollPane; //scroll bar to move up and down the story
   	private static JTextPane textPane;//where the text is

	//initialize buttons
    	JButton reportButton = new JButton("Report An Issue"); 
    	JButton buttonHigh = new JButton ("Highlight");
    	JButton translatorButton = new JButton ("Translator");
    	JButton buttonChoose = new JButton ("Choose Highlighter Color");
	private JButton annotateButton = new JButton("Add Annotation");
	
    	static Color highlightColor=Color.yellow;//default highlight color yellow
    	private static Point viewPosition;//to get the position of the scroll bar
    	private Annotations annotationFrame;//frame for the annotations
    //constructor
    public StoryInteraction(String bookName, String storyText, String storyOriginalText, String language) {
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        
        //when the window is closed, the point at which the scroll bar was is retrieved
        //this way functioning as a bookmark
        addWindowListener((WindowListener) new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	JViewport viewport = scrollPane.getViewport();

                // Get the current view position
                viewPosition = viewport.getViewPosition();

                // Print the scroll position
                System.out.println("Scroll Position: " + viewPosition.y);
                
              e.getWindow().dispose();
            }
        });
        setSize(700, 600);
        setLocationRelativeTo(Homepage.homepageFrame);
        setVisible(true);
        textPane = new JTextPane();
        textPane.setEditable(false); // Set editable to false
        setDefaultFont();  // Set default font for the JTextPane
        
        scrollPane = new JScrollPane(textPane);
        
        add(scrollPane, BorderLayout.CENTER);

        displayStory(bookName, storyText);

        scrollPosition ();//call method

	//add action listeners to all the buttons
        buttonHigh.addActionListener(new ActionListener() {//creates a new highlight method
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button works");
                Highlight.setTextPane (textPane);
                new Highlight (highlightColor);
            }
        });
        annotateButton.addActionListener(new ActionListener() {//creates a new frame and a new annotation methodf
            @Override
            public void actionPerformed(ActionEvent e) {
            	Annotations.setUsername (User.username);
            	Annotations.setStory(bookName);
            	annotationFrame = new Annotations( );
                showAnnotationFrame();
            }
        });
        
        translatorButton.addActionListener(new ActionListener() {//creates a new object translator 
            @Override
            public void actionPerformed(ActionEvent e) {
            	Translator.setPane (textPane);
            	Translator.setScrollBar(scrollPane);
            	Translator.setHasBeenClicked(hasBeenClickedT);
            	if (storyOriginalText!= null)//makes sure the translation exists in the database
            		new Translator(bookName, storyOriginalText, storyText);
            	else
            		JOptionPane.showMessageDialog(null, "Sorry!\n"+"We are working hard on finding the right translation", "Translation not found", JOptionPane.ERROR_MESSAGE);//error message if translation is not available
            	hasBeenClickedT = Translator.getHasBeenClicked ();//check if the button has already been clicked
            }
        });
        reportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reportStoryFrame(bookName);
			}//calls report
        });
        buttonChoose.addActionListener(new ActionListener() {//button for choosing highlight color
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				highlightColor=chooseColor();
			}
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonChoose);
        buttonPanel.add(buttonHigh);
        buttonPanel.add(annotateButton);
        buttonPanel.add(reportButton);
        if (language.equals("English"));//if the book only has english available, the translator button is not shown
        else
        	buttonPanel.add(translatorButton); //if not, it is
        add(buttonPanel, BorderLayout.SOUTH);
    }
	/* Method Name: setDefaultFont
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 10, 2024
	 * Modified Date: January 21, 2024
	 * Description: sets the default font for the body of the text
	 * @Parameters: n/a
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: n/a
	 * Throws/Exceptions:n/a
	 */  
    private void setDefaultFont() {
        Font defaultFont = new Font("Serif", Font.PLAIN, 14);
        textPane.setFont(defaultFont);
    }
    /* Method Name: scrollPosition
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 10, 2024
	 * Modified Date: January 21, 2024
	 * Description: gets the scroll position
	 * @Parameters: n/a
	 * @Return Value: n/a of the scrollPane
	 * Data Type: void
	 * Dependencies: n/a
	 * Throws/Exceptions:n/a
	 */  
    public static void scrollPosition () {
		SwingUtilities.invokeLater(() -> {
            // Set the scroll position to the top
			if (viewPosition == null)
				scrollPane.getVerticalScrollBar().setValue(0);
			else
				scrollPane.getVerticalScrollBar().setValue(viewPosition.y);
        });
	}
/* Method Name: displayStory
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 16, 2024
	 * Modified Date: January 21, 2024
	 * Description: displays story from a string
	 * @Parameters: String title: title of the story, String story: story to be displayed
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: n/a
	 * Throws/Exceptions: if there's an error while creating document
	 */  
    static void displayStory(String title, String story) { 
        StyledDocument styledDoc = textPane.getStyledDocument();

        // Title Style
        Style titleStyle = styledDoc.addStyle("TitleStyle", null); //format for the title
        StyleConstants.setBold(titleStyle, true);
        StyleConstants.setFontSize(titleStyle, 18);
        StyleConstants.setForeground(titleStyle, Color.BLUE);

        // Body Style
        Style bodyStyle = styledDoc.addStyle("BodyStyle", null); //format for the body
        StyleConstants.setFontSize(bodyStyle, 14);
        StyleConstants.setForeground(bodyStyle, Color.BLACK);

        try {
            styledDoc.insertString(styledDoc.getLength(), title + "\n\n", titleStyle); //inserts the string in the document
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        // Insert story with new lines every 10 words
        String[] words = story.split("\\s+");
        int wordCount = 0;
        for (String word : words) { //goes through all the words
            try {
                styledDoc.insertString(styledDoc.getLength(), word + " ", bodyStyle);
                wordCount++;

                // Insert new line after 10 words
                if (wordCount % 10 == 0) {
                    styledDoc.insertString(styledDoc.getLength(), "\n", bodyStyle);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
	 /* Method Name: reportStoryFrame
	 * Author: Fardin Abbasi
	 * Creation Date: January ??, 2024
	 * Modified Date: January ??, 2024
	 * Description: 
	 * @Parameters: n/a
	 * @Return Value: 
	 * Data Type:
	 * Dependencies: 
	 * Throws/Exceptions:n/a
	 */  
    /** NOT FINISHED YET **/
    private void reportStoryFrame(String storyName) {
    	setLocationRelativeTo(null);
    	
    	JFrame reportFrame = new JFrame();
		reportFrame.setLayout(new FlowLayout());
		JLabel desc = new JLabel("Please enter your report here:");
		JTextArea reportTextArea = new JTextArea(5, 30);
		JButton cancelReport = new JButton("Cancel report");
		JButton submitReport = new JButton("Submit report");
		
		reportTextArea.getDocument().addDocumentListener(new DocumentListener() {
        	public void insertUpdate(DocumentEvent e) {
                Document doc = e.getDocument();
                int length = doc.getLength();
                if (length > 200) {
                    SwingUtilities.invokeLater(() -> {
                        try {doc.remove(e.getOffset(), e.getLength());}
                        catch (BadLocationException ex) {ex.printStackTrace();}
                    });
                }
            }
        	// Technically a requirement, but not necessary for functionality
            public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {}
        });
		Dimension fixedSize = new Dimension(300, 150);
		reportTextArea.setMaximumSize(fixedSize);
		reportTextArea.setPreferredSize(fixedSize);
        reportTextArea.setLineWrap(true);
        reportTextArea.setWrapStyleWord(true);
        reportTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        reportTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        cancelReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// just dispose of report frame to go back
				reportFrame.dispose();
			}
        });
        submitReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// add report to table in database
				String report = reportTextArea.getText();
				/** ALMOST DONE: Need to find a way to add username into report **/
				// TALES.USERNAME COULD WORK HERE, NEED TO CHECK
				Community.reportStory(Tales.username, storyName, report);
			}
        });
        
        
        
		reportFrame.add(desc, BorderLayout.NORTH);
		reportFrame.add(reportTextArea);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(cancelReport, BorderLayout.SOUTH);
		buttonPanel.add(submitReport, BorderLayout.SOUTH);
		reportFrame.add(buttonPanel);
		
		reportFrame.setSize(400, 265);
		reportFrame.setVisible(true);
    }    
    /* Method Name: showAnnotationFrame
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 16, 2024
	 * Modified Date: January 21, 2024
	 * Description: shows the annotation frame
	 * @Parameters: n/a
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: n/a
	 * Throws/Exceptions:n/a
	 */  
    private void showAnnotationFrame() {
        if (textPane.getSelectedText() != null) {
            String selectedText = textPane.getSelectedText();
            annotationFrame.addAnnotation(selectedText);
            annotationFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No text selected for annotation", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /* Method Name: chooseColor
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 10, 2024
	 * Modified Date: January 21, 2024
	 * Description: allows the user to set a color for the highlights
	 * @Parameters: n/a
	 * @Return Value: chosen color
	 * Data Type: Color
	 * Dependencies: JColorChooser
	 * Throws/Exceptions:n/a
	 */  
    public static Color chooseColor (){
		Color color=JColorChooser.showDialog(null,"Select a color",highlightColor);    
		highlightColor = color;
		return color;
    }
//getter for the scroll position
    public static int getScrollPosition () {
    	return viewPosition.y;
    }
    
}
