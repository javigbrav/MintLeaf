import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/***********************************************************************************
 * Author: Zainab Siddiqui 
 * Date: December 20, 2023 
 * Last Modified: December 26, 2023
 * Last Modified by: Zainab Siddiqui
 * Description: Allow a user to login to their account
 ***********************************************************************************/
public class LoginPage implements ActionListener {
	
	static Color mintGreen = new Color(88, 153, 47);
	static Color mintRed = new Color(196, 30, 27);
    static JFrame loginFrame = new JFrame("Tales Around the World - Login");

    // set up screen
	private JLabel title = new JLabel(); // Tales Around the World

	JButton loginButton = new JButton("LOG IN");
	JButton signupButton = new JButton("SIGN UP");

	JTextField usernameField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	
	JLabel usernameLabel = new JLabel("username: ");
	JLabel userPasswordLabel = new JLabel("password: ");
	JLabel messageLabel = new JLabel();
	
	
	
	/*Constructor*/
	LoginPage(){
		
		// title
		title.setText("Tales Around the World");
		title.setFont(new Font("Impact", Font.PLAIN, 30));
		title.setForeground(Color.black);
		title.setBounds(50, 20, 300, 40);

		usernameLabel.setBounds(50, 100, 75, 25);
		userPasswordLabel.setBounds(50, 150, 75, 25);

		messageLabel.setBounds(125, 260, 250, 35);
		messageLabel.setFont(new Font(null, Font.BOLD, 15));

		usernameField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 150, 200, 25);

		// set up login button
		loginButton.setFont(new Font("Impact", Font.PLAIN, 16));
		loginButton.setForeground(new Color(0, 0, 0));
		loginButton.setBackground(new Color(115, 201, 61));
		loginButton.setBounds(125, 200, 100, 25);
		JLabel loginLabel = new JLabel("Existing user?");
		loginLabel.setForeground(Color.gray);
		loginLabel.setBounds(125, 240, 100, 25);
		loginButton.addActionListener(this);
	
		// set up sign in button
		signupButton.setFont(new Font("Impact", Font.PLAIN, 16));
		signupButton.setForeground(new Color(0, 0, 0));
		signupButton.setBackground(new Color(115, 201, 61));
		signupButton.setBounds(225, 200, 100, 25);
		JLabel signupLabel = new JLabel("First Time?");
		signupLabel.setForeground(Color.gray);
		signupLabel.setBounds(225, 240, 100, 25);
		signupButton.addActionListener(this);
		
		// add to frame
		loginFrame.getContentPane().add(title);
		loginFrame.getContentPane().add(usernameLabel);
		loginFrame.getContentPane().add(userPasswordLabel);
		loginFrame.getContentPane().add(messageLabel);
		loginFrame.getContentPane().add(usernameField);
		loginFrame.getContentPane().add(userPasswordField);
		loginFrame.getContentPane().add(loginButton);
		loginFrame.getContentPane().add(loginLabel);
		loginFrame.getContentPane().add(signupLabel);
		loginFrame.getContentPane().add(signupButton);
		
		// set frame
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(420, 420);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.getContentPane().setBackground(new Color(220, 242, 206));
		loginFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
		loginFrame.setBackground(new Color(220, 242, 206));
		loginFrame.setForeground(new Color(255, 255, 255));
		loginFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == loginButton) {
			String username = usernameField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(User.searchDataBase(username, password)) { // check if user and password are in the database
				messageLabel.setForeground(mintRed);
				messageLabel.setText("Welcome back!");
				loginFrame.dispose();
				Homepage homepage = new Homepage(username);
				homepage.homepageFrame.setVisible(true);
			} else {
				messageLabel.setForeground(mintRed);
				messageLabel.setText("Wrong username or password");
			}
		}
		if (e.getSource() == signupButton) {
			usernameField.setText("");
			userPasswordField.setText("");
			loginFrame.dispose();
			SignupPage signupPage = new SignupPage();
		}
	}
}
