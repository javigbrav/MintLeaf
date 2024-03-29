/***********************************************************************************
 * Author: Zainab Siddiqui 
 * Date: December 20, 2023 
 * Last Modified: January 15, 2024
 * Last Modified by: Zainab Siddiqui
 * Description: the main/driver for the application
 ***********************************************************************************/

import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.*;
import javax.swing.*;
import java.sql.*;

public class Tales {
	
	/*Globals*/
	static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int screenW = rect.width; //sets the screen width
    public static int screenH = rect.height; //sets the screen height
    
	/* Database/File stuff that differs from user to user */
    public static String conPath = "jdbc:mysql://localhost:3306/mintleafdb"; 
	public static String dbUsername = "root"; 
	public static String dbPassword = "MintLeaf";
    public static String mintIcon = "G:\\The Programmer's Delight\\Eclipse Projects\\Photos For MintLeaf\\mintleaf_icon.png\"";
   
    private static LinkedList<User> users;
    public static String username; public String password;
    Connection con;
    
    /*Constructor*/
    public Tales(LinkedList<User> users) {
    	createConnection();
    	this.users = users;
		User usernameAndPassword = new User(username, password);
		LoginPage loginpage = new LoginPage();
	}

    /*Create connection to MySQL*/
    void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // connecting MySQL driver to program
			con = DriverManager.getConnection(conPath, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Tales.class.getName()).log(Level.SEVERE, null, e);
		} catch (SQLException e) {
			Logger.getLogger(Tales.class.getName()).log(Level.SEVERE, null, e);
		}	
	} // end createConnection

    public static void setFrame(JFrame frame) {
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(Tales.screenW, Tales.screenH);
    	frame.getContentPane().setLayout(null);
    	frame.getContentPane().setBackground(new Color(220, 242, 206));
    	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
    	frame.setBackground(new Color(220, 242, 206));
    	frame.setForeground(Color.black);
    }
    
    public static void main(String[] args) {
		new Tales(users);
	} // end main
} // end Tales