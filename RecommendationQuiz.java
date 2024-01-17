package Homepage;

/***********************************************************************************
 * Author: Fardin Abbassi
 * Date: December 29, 2023 
 * Last Modified: January 17, 2024
 * Last Modified by: Fardin Abbassi
 * Description: Creates a quiz that tracks a user's preferences throughout the program
 ***********************************************************************************/

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.*;

public class RecommendationQuiz {	
	
	/*Genre tracking*/
    private static String[] genreNames = {"Fantasy", "Horror", "Romance", "Adventure", "Mystery", "Historical", "Non-Fiction", "Drama", "Sci-Fi"};
    private static String genreResult;

	/*Region tracking*/
    private static String[] regionNames = {"Europe", "Latin America", "Middle East", "Africa", "South Asia", "East Asia", "South East Asia / Oceania", "Cental Asia"};
    private static String regionResult;
	
    /*Reading age range tracking*/
    private static String ageRangeResult;
    
    /*Story length tracking*/
    private static String[] storyLengths = {"Short Story", "Novella", "Novel", "Epic"};
    private static String storyLengthResult;

    /*Other global variables*/
    public static String[] preferences = new String[4];
	private static JFrame f = new JFrame("Recommendation Quiz");
    static boolean isThereAnAge = false;

    RecommendationQuiz(User userToTrack) {
        f.setSize(450, 630);
        f.getContentPane().setBackground(new Color(0, 242, 206));
                
        // Scrollable panel
        JScrollPane scrollPane = new JScrollPane(createContentPanel(userToTrack), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        f.getContentPane().add(scrollPane);

        f.setVisible(true);
    }

    /* Method Name: createContentPanel 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 31, 2023
	 * Modified Date: January 11, 2024
	 * Description: Creates a JPanel that contains all the question panels for the user to interact with, and updates a user's preference list.
	 * @Parameters: User userToTrack
	 * @Return Value: JPanel
	 * Data Type: ???? 
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */    
    private static JPanel createContentPanel(User userToTrack) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // left, top, right, bottom

        /*Title Panel*/
        JLabel title = new JLabel("Recommendation Quiz", SwingConstants.CENTER);
        title.setFont(new Font("Impact", Font.PLAIN, 30));
        contentPanel.add(title);
        JLabel description = new JLabel("Please take your time in answering a few questions.", SwingConstants.CENTER);
        description.setFont(new Font("Calibri", Font.PLAIN, 18));
        description.setForeground(new Color(0, 128, 64));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(description);        
        
        /*Question 1: Genre*/
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        JLabel question1 = new JLabel("Which genre best represents your taste in literature?");
        JCheckBox[] genres = new JCheckBox[genreNames.length];
        ButtonGroup genreGroup = new ButtonGroup();        
        JButton submitButton = new JButton("Confirm choice");
        JPanel genrePanel = questionPanel(question1, genres, genreGroup, submitButton, genreNames);
        
        contentPanel.add(genrePanel);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < genres.length; i++) {
                    if (genres[i].isSelected()) {
                        genreResult = genres[i].getText();
                        break;
                    }
                }

                if (genreResult != null) {
                    for (int j = 0; j < genres.length; j++) {
                        if (!genres[j].getText().equals(genreResult))
                            genres[j].setVisible(false);
                    }
                }
                if(preferences[0] == null) {
                	preferences[0] = genreResult;
                }
            }
        });

        /*Question 2: Region*/
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        JLabel question2 = new JLabel("Which region would you like to see some folk tales from?");
        JCheckBox[] regions = new JCheckBox[regionNames.length];
        ButtonGroup regionGroup = new ButtonGroup();
        JButton submitButton2 = new JButton("Confirm choice");
        JPanel regionPanel = questionPanel(question2, regions, regionGroup, submitButton2, regionNames);
        
        contentPanel.add(regionPanel);
        submitButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < regions.length; i++) {
                    if (regions[i].isSelected()) {
                        regionResult = regions[i].getText();
                        break;
                    }
                }

                if (regionResult != null) {
                    for (int j = 0; j < regions.length; j++) {
                        if (!regions[j].getText().equals(regionResult))
                            regions[j].setVisible(false);
                    }
                }
                if(preferences[1] == null) {
                	preferences[1] = regionResult;
                }            
            }
        });

        /*Question 3: Age Range*/
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        JLabel question3 = new JLabel("How old are you?");
        JTextField ageTextField = new JTextField(2);
        JButton submitButton3 = new JButton("Confirm choice");
        JLabel improperAge = new JLabel("Invalid Entry - Please enter a valid age");

        ageTextField.setText("");
        ageTextField.getDocument().addDocumentListener(new DocumentListener() {
        	public void insertUpdate(DocumentEvent e) {
                Document doc = e.getDocument();
                int length = doc.getLength();
                if (length > 2) {
                	isThereAnAge = true;
                    SwingUtilities.invokeLater(() -> {
                        try {
                            doc.remove(e.getOffset(), e.getLength());
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }
        	// Technically a requirement, but not necessary for functionality
            public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {}
        });
        Dimension fixedSize = new Dimension(submitButton3.getPreferredSize().width * 3, ageTextField.getPreferredSize().height);
        ageTextField.setMaximumSize(fixedSize);
        ageTextField.setPreferredSize(fixedSize); 
     
        submitButton3.setBackground(new Color(115, 201, 61));
        submitButton3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        submitButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String potentialAge = ageTextField.getText();
            	int age;
            	try {
            		// parse to find a number to use as an age
            		age = Integer.parseInt(potentialAge);
            		isThereAnAge = true;
            	}catch(NumberFormatException ex) {
            		// if there are non-numerical characters, make the user input numbers
            		improperAge.setVisible(true);
            		isThereAnAge = false;
            	} // end try/catch
            	
            	// if/when a correct age is input, lock the text field and set the string array index to the age
                if (isThereAnAge) {
                	ageRangeResult = potentialAge;
                    ageTextField.setEditable(false);
	                if(preferences[2] == null) {preferences[2] = ageRangeResult;}
	                improperAge.setVisible(false);
                } // end if            
            }
        });
        
        improperAge.setVisible(false);
        improperAge.setForeground(Color.red);
        
        JPanel ageRangePanel = new JPanel();
        ageRangePanel.setLayout(new BoxLayout(ageRangePanel, BoxLayout.Y_AXIS)); // vertically stack the components
        ageRangePanel.add(question3);
        ageRangePanel.add(ageTextField);
        ageRangePanel.add(submitButton3);
        ageRangePanel.add(improperAge);
        
        clearComponentBackground(ageRangePanel);
        contentPanel.add(ageRangePanel);
        ageTextField.setOpaque(true);
        submitButton3.setOpaque(true);
        
        /*Question 4: Story Length*/
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add 10 pixels of vertical space
        JLabel question4 = new JLabel("Which best represents the lengths of the stories you like to read?");
        JCheckBox[] lengths = new JCheckBox[storyLengths.length];
        ButtonGroup lengthGroup = new ButtonGroup();
        JButton submitButton4 = new JButton("Confirm choice");
        JPanel storyLengthPanel = questionPanel(question4, lengths, lengthGroup, submitButton4, storyLengths);

        contentPanel.add(storyLengthPanel);
        submitButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < lengths.length; i++) {
                    if (lengths[i].isSelected()) {
                        storyLengthResult = lengths[i].getText();
                        break;
                    }
                }

                if (storyLengthResult != null) {
                    for (int j = 0; j < lengths.length; j++) {
                        if (!lengths[j].getText().equals(storyLengthResult))
                        	lengths[j].setVisible(false);
                    }
                }

                if(preferences[3] == null) {
                	preferences[3] = storyLengthResult;
                }
            }
        });
        

        /* Finish Button to submit preferences*/
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // add 10 pixels of vertical space
        JButton finishedButton = new JButton("Finished?");
        contentPanel.add(finishedButton);
        finishedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
        		Tales.username = userToTrack.username;
        		Homepage.homepageFrame.setVisible(true);
        		userToTrack.updatePreferences(preferences);
        		if(!User.searchForExistingUser(userToTrack.username)) {
            		userToTrack.createConnection(userToTrack.username, userToTrack.password, userToTrack.genre, userToTrack.region, userToTrack.age, userToTrack.storyLength);	
        		}
        		clearComponents(genres);
        		clearComponents(regions);
        		clearComponents(ageTextField);
        		clearComponents(lengths);
            }
        });
        finishedButton.setFont(new Font("Impact", Font.PLAIN, 15));
        finishedButton.setBackground(new Color(98, 163, 57));
        
        clearComponentBackground(contentPanel);
        contentPanel.setBackground(new Color(220, 242, 206));
        contentPanel.setOpaque(true);
        finishedButton.setOpaque(true);
        return contentPanel;
    }
    
    /* Method Name: questionPanel 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 30, 2023
	 * Modified Date: January 05, 2024
	 * Description: Creates a JPanel that has a question, choices with checkboxes, and a button to confirm choice
	 * @Parameters: JLabel question, JCheckBox[] choices, ButtonGroup choiceGroup, JButton submitButton, String[] choiceNames
	 * @Return Value: JPanel
	 * Data Type: ????
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */
    private static JPanel questionPanel(JLabel question, JCheckBox[] choices, ButtonGroup choiceGroup, JButton submitButton, String[] choiceNames) {
    	JPanel questionPanel = new JPanel();
    	questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS)); // vertically stack the components
        questionPanel.add(question);
        for (int i = 0; i < choices.length; i++) {
            choices[i] = new JCheckBox(choiceNames[i]);
            choiceGroup.add(choices[i]);
            questionPanel.add(choices[i]);
            choices[i].setFont(new Font("Calibri", Font.PLAIN, 15));
        }
        questionPanel.add(submitButton);
        clearComponentBackground(questionPanel);
        submitButton.setBackground(new Color(115, 201, 61));
        submitButton.setOpaque(true);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	return questionPanel;
    }
    
    /* Method Name: clearComponentBackground 
	 * Author: Fardin Abbassi 
	 * Creation Date: January 05, 2024
	 * Modified Date: January 05, 2024
	 * Description: Makes the given JPanel's components clear
	 * @Parameters: JPanel panel
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */
    private static void clearComponentBackground(JPanel panel) {
    	for(int j = panel.getComponentCount() - 1; j > 0; j--) {
    		Component comp = panel.getComponents()[j];
    		if (comp instanceof JComponent) {
    			((JComponent) comp).setOpaque(false);
    		}
    	}
    }

    /**/
    private static void clearComponents(JCheckBox[] boxesToClear) {
    	for (int i = 0; i<boxesToClear.length; i++) {
    		boxesToClear[i].setVisible(true);
    		if(boxesToClear[i].isSelected()) {
    			boxesToClear[i].setSelected(false);
    		}
    	}
    }
    private static void clearComponents(JTextField textFieldToClear) {
    	textFieldToClear.setEditable(true);
    	isThereAnAge = false;
    }
}
