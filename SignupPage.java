import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/***********************************************************************************
 * Author: Zainab Siddiqui 
 * Date: December 26, 2023 
 * Last Modified:
 * Last Modified by: 
 * Description: Allow a user to create an account
 ***********************************************************************************/
public class SignupPage implements ActionListener {
	
	/*Globals*/
	JFrame signupFrame = new JFrame("Tales Around the World - Sign Up");
	private JLabel title = new JLabel();
	private JTextField usernameField;
	private JPasswordField userPasswordField;
	public JButton signupButton = new JButton("SIGN UP");
	private JLabel warningLabel = new JLabel();
	User newUser;
	/*Constructor*/
	SignupPage(){
		System.out.println(LoginPage.logininfo.entrySet());

		// set frame
		signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signupFrame.setSize(420, 420);
		signupFrame.getContentPane().setBackground(new Color(220, 242, 206));
		signupFrame.getContentPane().setLayout(null);
		title.setBounds(20, 20, 91, 38);
		
		// title
		title.setText("Sign up");
		title.setFont(new Font("Impact", Font.PLAIN, 30));
		title.setForeground(Color.black);
		signupFrame.getContentPane().add(title);
		
		// username
		JLabel usernameLabel = new JLabel("username:");
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
		JLabel passwordLabel = new JLabel("password:");
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
		JLabel welcomeLabel = new JLabel("Welcome! Create an account to continue");
		welcomeLabel.setForeground(new Color(0, 128, 64));
		welcomeLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		welcomeLabel.setBounds(56, 74, 315, 34);
		signupFrame.getContentPane().add(welcomeLabel);
		
		// recommendations quiz directions
		JButton quizButton = new JButton("Recommendations Quiz!");
		quizButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quizButton.setBackground(new Color(115, 201, 61));
		quizButton.setForeground(new Color(0, 0, 0));
		quizButton.setBounds(100, 254, 213, 23);
		signupFrame.getContentPane().add(quizButton);
		
		JLabel directionsLabel = new JLabel("Personalize your homepage with this");
		directionsLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		directionsLabel.setBounds(90, 220, 251, 23);
		
		// sign up button
		signupButton.setBackground(new Color(115, 201, 61));
		signupButton.setForeground(new Color(0, 0, 0));
		signupButton.setFont(new Font("Impact", Font.PLAIN, 15));
		signupButton.setBounds(161, 316, 89, 23);
		signupFrame.getContentPane().add(signupButton);
		signupButton.addActionListener(this);
		
		// set up frame
		signupFrame.getContentPane().add(directionsLabel);
		signupFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
		signupFrame.setBackground(new Color(220, 242, 206));
		signupFrame.setForeground(new Color(255, 255, 255));
		signupFrame.setVisible(true);	
	} // end SignupPage

	/**NEED TO REPLACE HASH MAP WITH MYSQL DATABASE -- that will make sure user info is saved**/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signupButton) {
			System.out.println("test");
			String username = usernameField.getText();
			String userPassword = String.valueOf(userPasswordField.getPassword());

			if (LoginPage.logininfo.containsKey(username)) {
				usernameField.setText("");
				userPasswordField.setText("");
				// warning label
				warningLabel.setForeground(LoginPage.mintRed);
				warningLabel.setFont(new Font(null, Font.PLAIN, 14));
				warningLabel.setText("Username already exists");
				warningLabel.setBounds(154, 147, 159, 23);
				signupFrame.getContentPane().add(warningLabel);
			}
			LoginPage.logininfo.put(username, userPassword); // add new user
			signupFrame.dispose();
			LoginPage.loginFrame.setVisible(true);
		}
		// add ^ for recommendation quiz button
	}
} // end SignupPage