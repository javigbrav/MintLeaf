/***********************************************************************************
 * Author: Victor Kosarev
 * Date: January 15, 2024
 * Last Modified: January 22, 2024
 * Last Modified by: Fardin Abbassi
 * Description: Creates profile page for the user
 ***********************************************************************************/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ProfilePage implements ActionListener{
	
	JFrame frame;
	private JTextField Username;
	
	private JLabel usernameLabel;
	private JLabel title = new JLabel();
	
	// showing all current preferences
	private JLabel genre = new JLabel("Preffered Genre : " + User.genre);
	private JLabel region = new JLabel("Preffered region : " + User.region);
	private JLabel age = new JLabel("Age : " + User.age);
	private JLabel length = new JLabel("Preffered Story Length : " + User.storyLength);
	
	// all buttons 
	private JButton changePreferences = new JButton("Change Preferences"); // used for changing preferences
	private JButton changeUsername = new JButton("Change Username"); // used for changing username
	private JButton confirmUsername = new JButton("Confirm Username"); // confirming username change
	private JButton suggestStory = new JButton("Suggest a Story"); // for suggesting a story
	
	// constructor
	ProfilePage() {
		// creating the frame
		createFrame();
		
		// creating welcome message for user
		title.setText("Welcome, " + User.username);
		title.setFont(new Font("Impact", Font.PLAIN, 30));
		title.setForeground(Color.black);
		title.setBounds(150, 20, 300, 40);
		
		// setting bounds of preferences
		genre.setBounds(150, 100, 200, 25);
		region.setBounds(150, 150, 200, 25);
		age.setBounds(150, 200, 200, 25);
		length.setBounds(150, 250, 300, 25);
		
		// setting up all buttons
		changePreferences.setFont(new Font("Impact", Font.PLAIN, 16));
		changePreferences.setBackground(new Color(115, 201, 61));
		changePreferences.setBounds(50, 300, 200, 25);
		changePreferences.addActionListener(this);
		
		suggestStory.setFont(new Font("Impact", Font.PLAIN, 16));
		suggestStory.setBackground(new Color(115, 201, 61));
		suggestStory.setBounds(250, 300, 200, 25);
		suggestStory.addActionListener(this);
		
		changeUsername.setFont(new Font("Impact", Font.PLAIN, 16));
		changeUsername.setBackground(new Color(115, 201, 61));
		changeUsername.setBounds(150, 350, 175, 25);
		changeUsername.addActionListener(this);
		
		//confirmUsername only appears when changeUsername is pressed
		confirmUsername.setFont(new Font("Impact", Font.PLAIN, 16));
		confirmUsername.setBackground(new Color(115, 201, 61));
		confirmUsername.setBounds(150, 460, 175, 25);
		confirmUsername.addActionListener(this);
		
		// setting up label and textfield for user changing user name. 
		// these will only appear when user clicks changeUsername
		usernameLabel = new JLabel("New Username : ");
		usernameLabel.setFont(new Font ("Impact", Font.PLAIN, 16));
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setBounds(100, 410, 175, 25);
		
		Username = new JTextField();
		Username.setFont(new Font ("Tahoma", Font.PLAIN, 14));
		Username.setBounds(225, 410, 175, 25);
		Username.setColumns(10);
		
		Username.setVisible(false);
		usernameLabel.setVisible(false);
		confirmUsername.setVisible(false);
		
		
		// adding all components to frame
		frame.getContentPane().add(title);
		frame.getContentPane().add(genre);
		frame.getContentPane().add(region);
		frame.getContentPane().add(age);
		frame.getContentPane().add(length);
		frame.getContentPane().add(changePreferences);
		frame.getContentPane().add(suggestStory);
		frame.getContentPane().add(changePreferences);
		frame.getContentPane().add(changeUsername);
		frame.getContentPane().add(Username);
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(confirmUsername);		
	}
	
	
	// creating frame
	public void createFrame(){
		frame = new JFrame("Profile");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(500, 550);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setForeground(new Color(255, 255, 255));
		frame.setBackground(new Color(220, 242, 206));
		frame.setVisible(true);
	}
	
	/* Method Name: suggestFrame 
	 * Author: Fardin Abbassi
	 * Creation Date: January 21, 2024
	 * Modified Date: January ??, 2024
	 * Description: Creates a frame that lets the user suggest a story, which when confirmed, inserts a submission report into the database
	 * @Parameters: n/a
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */   
	private void suggestFrame() {
		JFrame suggestion = new JFrame();
		
		JLabel desc = new JLabel("Please enter your suggestion here:");
		JTextArea suggestionTextArea = new JTextArea(5, 30);
		JButton cancelSuggestion = new JButton("Cancel suggestion");
		JButton submitSuggestion = new JButton("Submit suggestion");
		
		suggestionTextArea.getDocument().addDocumentListener(new DocumentListener() {
        	public void insertUpdate(DocumentEvent e) {
                Document doc = e.getDocument();
                int length = doc.getLength();
                if (length > 70) {
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
		suggestionTextArea.setMaximumSize(fixedSize);
		suggestionTextArea.setPreferredSize(fixedSize);
        suggestionTextArea.setLineWrap(true);
        suggestionTextArea.setWrapStyleWord(true);
        suggestionTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        suggestionTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        cancelSuggestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// just dispose of report frame to go back
				suggestion.dispose();
			}
        });
        submitSuggestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String suggestedTitle = suggestionTextArea.getText();
	            String formattedTitle = suggestedTitle.replace("'", "\\'"); // in case some stories have apostraphes in their title
	            
				String suggestionReport = User.username + " would like to add this book/story to the database.";
				Community.reportStory(Tales.username, formattedTitle, suggestionReport);
				suggestion.dispose();
			}
        });
        
		suggestion.add(desc, BorderLayout.NORTH);
		suggestion.add(suggestionTextArea);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(cancelSuggestion, BorderLayout.SOUTH);
		buttonPanel.add(submitSuggestion, BorderLayout.SOUTH);
		suggestion.add(buttonPanel);
		
		suggestion.setSize(400, 265);
		suggestion.setForeground(new Color(255, 255, 255));
		suggestion.setBackground(new Color(220, 242, 206));
		suggestion.setLocationRelativeTo(frame);
		suggestion.setLayout(new FlowLayout());
		suggestion.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// changing preferences
		if(e.getSource() == changePreferences) {
			new RecommendationQuiz(new User(User.username, User.password));
			frame.dispose();
		}
		// suggesting story
		if(e.getSource() == suggestStory) {suggestFrame();}
		
		//changing username
		if(e.getSource() == changeUsername) {
			Username.setVisible(true);
			usernameLabel.setVisible(true);
			confirmUsername.setVisible(true);
		}
		
		// confirm username change
		if(e.getSource() == confirmUsername) {
			User.changeUsername(Username.getText());
			Tales.username = Username.getText();
			Homepage.changeWelcomeMessage(Username.getText());
			frame.dispose();
		}
	}
}