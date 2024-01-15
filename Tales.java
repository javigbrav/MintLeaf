import java.awt.*;
import java.util.*;
import javax.swing.*;
/***********************************************************************************
 * Author: Zainab Siddiqui 
 * Date: December 20, 2023 
 * Last Modified: January 14, 2024
 * Last Modified by: Fardin Abbassi
 * Description: the main/driver for the application
 ***********************************************************************************/
public class Tales {
	
	/*Globals*/
	static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int screenW = rect.width; //sets the screen width
    public static int screenH = rect.height; //sets the screen height
    
    private static LinkedList<User> users;
    static String username; 
    private String password;
    
    /*Constructor*/
    public Tales(LinkedList<User> users) {
    	this.users = users;
		LoginPage loginpage = new LoginPage();
	}
	
	public static void main(String[] args) {
		new Tales(users);
	} // end main
} // end Tales
