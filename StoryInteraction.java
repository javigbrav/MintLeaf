package LogIn;
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
	static boolean hasBeenClickedT = false; //to check if the translator button has already been clicked
	private static JScrollPane scrollPane;
    private static JTextPane textPane;
    JButton reportButton = new JButton("Report An Issue");
    JButton buttonHigh = new JButton ("Highlight");
    JButton translatorButton = new JButton ("Translator");
    JButton buttonChoose = new JButton ("Choose Highlighter Color");
    static Color highlightColor=Color.yellow;//default highlight color yellow
    //int ScrollBarPosition = 0; //default value, at the top
    private static Point viewPosition;
    private JButton annotateButton = new JButton("Add Annotation");
    private Annotations annotationFrame;
    public LinkedList <Annotations> notes = new LinkedList <Annotations>();
    
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

        //textPane.setEditable(false);
        
        scrollPane = new JScrollPane(textPane);
        
        add(scrollPane, BorderLayout.CENTER);

        displayStory(bookName, storyText);

        scrollPosition ();
        
        buttonHigh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button works");
                Highlight.setTextPane (textPane);
                new Highlight (highlightColor);
            }
        });
        annotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Annotations.setUsername (User.username);
            	Annotations.setStory(bookName);
            	annotationFrame = new Annotations( );
                showAnnotationFrame();
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
        if (language.equals("English"));
        else
        	buttonPanel.add(translatorButton);
        add(buttonPanel, BorderLayout.SOUTH);


//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem openItem = new JMenuItem("Open");
        //openItem.addActionListener(e -> openAndDisplayFile("book1.txt"));  // Replace with the actual path
        //fileMenu.add(openItem);
        ///menuBar.add(fileMenu);

        //setJMenuBar(menuBar);
    }
    private void setDefaultFont() {
        // Set default font for the JTextPane (replace this with your desired default font)
        Font defaultFont = new Font("Serif", Font.PLAIN, 14);
        textPane.setFont(defaultFont);
    }
    
    public static void scrollPosition () {
		SwingUtilities.invokeLater(() -> {
            // Set the scroll position to the top
			if (viewPosition == null)
				scrollPane.getVerticalScrollBar().setValue(0);
			else
				scrollPane.getVerticalScrollBar().setValue(viewPosition.y);
        });
	}

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
//    private static void displayFileContent(String storyText) {
//        textPane.setText(""); // Clear existing text
//
//        // Split the story into lines
//        String[] lines = storyText.split("\n");
//
//        SimpleAttributeSet titleAttributes = new SimpleAttributeSet();
//        StyleConstants.setBold(titleAttributes, true);
//        StyleConstants.setFontFamily(titleAttributes, "Helvetica");
//        StyleConstants.setFontSize(titleAttributes, 16);
//
//        SimpleAttributeSet bodyAttributes = new SimpleAttributeSet();
//        StyleConstants.setBold(bodyAttributes, false);
//        StyleConstants.setFontFamily(bodyAttributes, "Serif");
//        StyleConstants.setFontSize(bodyAttributes, 14);
//
//        for (int i = 0; i < lines.length; i++) {
//            String line = lines[i];
//            SimpleAttributeSet attributes = (i == 0) ? titleAttributes : bodyAttributes;
//
//            textPane.setCharacterAttributes(attributes, true);
//            textPane.replaceSelection(line + "\n");
//        }
//    }
    
//    private void highlightSelectedText(Color highlightColor) {
//        int start = textPane.getSelectionStart();
//        int end = textPane.getSelectionEnd();
//
//        if (start != end) {
//            try {
//                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(highlightColor);
//                textPane.getHighlighter().addHighlight(start, end, painter);
//            } catch (BadLocationException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
    
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
    
    private void showAnnotationFrame() {
        if (textPane.getSelectedText() != null) {
            String selectedText = textPane.getSelectedText();
            annotationFrame.addAnnotation(selectedText);
            annotationFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No text selected for annotation", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Color chooseColor (){
		Color color=JColorChooser.showDialog(null,"Select a color",highlightColor);    
		highlightColor = color;
		return color;
    }
    public static int getScrollPosition () {
    	return viewPosition.y;
    }
    
    public int getBookmark (String storyName) {
    	try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "root");
	    	Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM annotations WHERE username like '" + Tales.username
					+ "' and story like '" + storyName + "'");
			while(rs.next()) {
				
			}
			return ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            StoryInteraction StoryInteraction = new StoryInteraction(bookName);
//            StoryInteraction.setVisible(true);
//        });
//    }
}
