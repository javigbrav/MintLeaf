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
import java.sql.*;
import java.util.LinkedList;

public class StoryInteraction extends JFrame {
	
	//static Color initialColor = Color.yellow;
	static boolean hasBeenClickedT = false; //to check if the translator button has already been clicked
	private static JScrollPane scrollPane;
    private static JTextPane textPane;
    
    // buttons
    JButton reportButton = new JButton("Report An Issue");
    JButton buttonHigh = new JButton ("Highlight");
    JButton translatorButton = new JButton ("Translator");
    JButton buttonChoose = new JButton ("Choose Highlighter Color");
    private JButton annotateButton = new JButton("Add Annotation");
    
	static Color highlightColor=Color.yellow;	// default highlight color yellow
	private static Point viewPosition;			// to get the position of the scroll bar
	private Annotations annotationFrame;		// frame for the annotations
    
    public StoryInteraction(String bookName, String storyText, String storyOriginalText, String language) {
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE  );
        
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

        scrollPosition ();
        
        buttonHigh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button works");
                Highlights.setTextPane (textPane);
                new Highlights (highlightColor);
            }
        });
        annotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Annotations.setUsername (User.username);
            	Annotations.setStory(bookName);
            	annotationFrame = new Annotations( );
                showAnnotationFrame(bookName);
            }
        });
        
        translatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Translator.setPane (textPane);
            	Translator.setScrollBar(scrollPane);
            	Translator.setHasBeenClicked(hasBeenClickedT);
            	if (storyOriginalText!= null)
            		new Translator(bookName, storyOriginalText, storyText);
            	
            	else
            		JOptionPane.showMessageDialog(null, "We are working hard on finding the right translation", "Sorry!", JOptionPane.ERROR_MESSAGE);
            	hasBeenClickedT = Translator.getHasBeenClicked ();
            }
        });
        reportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reportStoryFrame(bookName);
			}
        });
        buttonChoose.addActionListener(new ActionListener() {
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
        if (! language.equals("English"))	buttonPanel.add(translatorButton);
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
        // Set default font for the JTextPane (replace this with your desired default font)
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
        Style titleStyle = styledDoc.addStyle("TitleStyle", null);
        StyleConstants.setBold(titleStyle, true);
        StyleConstants.setFontSize(titleStyle, 18);
        StyleConstants.setForeground(titleStyle, Color.BLUE);

        // Body Style
        Style bodyStyle = styledDoc.addStyle("BodyStyle", null);
        StyleConstants.setFontSize(bodyStyle, 14);
        StyleConstants.setForeground(bodyStyle, Color.BLACK);

        // Insert title
        try {
            styledDoc.insertString(styledDoc.getLength(), title + "\n\n", titleStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        // Insert story with new lines every 10 words
        String[] words = story.split("\\s+");
        int wordCount = 0;
        for (String word : words) {
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
	 * Description: Creates a frame that collects a report, then sends it to the database's reports table
	 * @Parameters: String storyName
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: java.awt, javax.swing
	 * Throws/Exceptions: n/a
	 */  
    private void reportStoryFrame(String storyName) {    	
    	JFrame reportFrame = new JFrame();
    	reportFrame.setLocationRelativeTo(scrollPane);
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
				reportFrame.dispose();
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
    private void showAnnotationFrame(String storyName) {
        if (textPane.getSelectedText() != null) {
            String selectedText = textPane.getSelectedText();
            annotationFrame.addAnnotation(Tales.username, storyName, selectedText);
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
    // getter for the scroll position
    public static int getScrollPosition () {
    	return viewPosition.y;
    }
}