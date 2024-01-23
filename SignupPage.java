/***********************************************************************************
 * Author: Zainab Siddiqui 
 * Date: December 26, 2023 
 * Last Modified: January 18, 2024
 * Last Modified by: Fardin Abbassi
 * Description: Allow a user to create an account
 ***********************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignupPage implements ActionListener {
	
	/*Globals*/
	JFrame signupFrame = new JFrame("Tales Around the World - Sign Up");
	private JLabel title = new JLabel();
	private JTextField usernameField;
	private JPasswordField userPasswordField;
	public JButton signupButton = new JButton("SIGN UP");
	public JButton goBackButton = new JButton("GO BACK");
	private JLabel warningLabel = new JLabel("Username already exists");
	User newUser;
	
	/*Constructor*/
	SignupPage(){

		// set frame
		signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signupFrame.setSize(420, 420);
		signupFrame.getContentPane().setBackground(new Color(220, 242, 206));
		signupFrame.getContentPane().setLayout(null);
		title.setBounds(20, 20, 100, 38);
		
		
		// title
		title.setText("Sign up");
		title.setFont(new Font("Impact", Font.PLAIN, 30));
		title.setForeground(Color.black);
		signupFrame.getContentPane().add(title);
		
		// username
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(new Color(66, 66, 66));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameLabel.setBounds(56, 121, 88, 29);
		signupFrame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setBounds(154, 125, 159, 20);
		signupFrame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		// password
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(new Color(66, 66, 66));
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordLabel.setBounds(56, 167, 84, 29);
		signupFrame.getContentPane().add(passwordLabel);
		
		userPasswordField = new JPasswordField();
		userPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userPasswordField.setColumns(10);
		userPasswordField.setBounds(154, 171, 159, 20);
		signupFrame.getContentPane().add(userPasswordField);
		
		// welcome directions
		JLabel welcomeLabel = new JLabel("Welcome! Create an account to continue.");
		welcomeLabel.setForeground(new Color(0, 128, 64));
		welcomeLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		welcomeLabel.setBounds(56, 74, 315, 34);
		signupFrame.getContentPane().add(welcomeLabel);

		JLabel directionsLabel = new JLabel("Once you're done signing up, feel free to set up your");
		directionsLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		directionsLabel.setBounds(40, 225, 321, 23);
		directionsLabel.setForeground(new Color(75, 75, 75));
		JLabel directionsLabel2 = new JLabel("preferences by taking a survey (or not, if you'd like)");
		directionsLabel2.setFont(new Font("Calibri", Font.PLAIN, 15));
		directionsLabel2.setBounds(40, 240, 321, 23);
		directionsLabel2.setForeground(new Color(75, 75, 75));

		// sign up button
		signupButton.setBackground(new Color(115, 201, 61));
		signupButton.setForeground(new Color(0, 0, 0));
		signupButton.setFont(new Font("Impact", Font.PLAIN, 15));
		signupButton.setBounds(110, 280, 100, 30);
		signupFrame.getContentPane().add(signupButton);
		signupButton.addActionListener(this);
		
		// go back button
		goBackButton.setBackground(new Color(115, 201, 61));
		goBackButton.setForeground(new Color(0, 0, 0));
		goBackButton.setFont(new Font("Impact", Font.PLAIN, 15));
		goBackButton.setBounds(210, 280, 100, 30);
		signupFrame.getContentPane().add(goBackButton);
		goBackButton.addActionListener(this);
		
		// warning label
		warningLabel.setForeground(LoginPage.mintRed);
		warningLabel.setFont(new Font(null, Font.PLAIN, 14));
		warningLabel.setText("Username already exists");
		warningLabel.setBounds(154, 147, 159, 23);
		signupFrame.getContentPane().add(warningLabel);
		warningLabel.setVisible(false);
		
		// set up frame
		signupFrame.getContentPane().add(directionsLabel);
		signupFrame.getContentPane().add(directionsLabel2);
		signupFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
		signupFrame.setBackground(new Color(220, 242, 206));
		signupFrame.setForeground(new Color(255, 255, 255));
		signupFrame.setVisible(true);	
	} // end SignupPage

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signupButton) {
			String username = usernameField.getText();
			String userPassword = String.valueOf(userPasswordField.getPassword());
			
			System.out.println(warningLabel.isVisible());

			if (User.searchForExistingUser(username)) {
				usernameField.setText("");
				userPasswordField.setText("");
				warningLabel.setVisible(true);
			}
			else {
				newUser = new User(username, userPassword);
				Homepage h = new Homepage(username);
				h.homepageFrame.setVisible(false);
				RecommendationQuiz rc = new RecommendationQuiz(newUser);
				signupFrame.dispose();
			}
		}
		if(e.getSource() == goBackButton) {
			signupFrame.dispose();
			new LoginPage();
		}
	}
} // end SignupPage