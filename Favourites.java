package library;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import mains.Homepage;
import mains.Tales;

/***********************************************************************************
 * Author: Zainab Siddiqui
 * Date: January 14, 2023 
 * Last Modified: January 22, 2024
 * Last Modified by: Zainab Siddiqui
 * Description: Display user's favourited stories
 ***********************************************************************************/

public class Favourites {
    /*Globals*/
	public static ArrayList<String> favList = new ArrayList<>();

    /*Constructor*/
	public Favourites() {		
		findFavourites();	
	}
	
	/* Method Name: findFavourites
	 * Author: Zainab Siddiqui
	 * Creation Date: January 23 2023
	 * Modified Date: January 24 2023
	 * Description: finds the favourited book titles from the database
	 * @Parameters: none
	 * @Return Value: none
	 * Data Type: void
	 * Dependencies: MySQL
	 * Throws/Exception: none
	 */ 
	public static void findFavourites(){
		
	     String title = ""; // title of favourited story
	     try {
	            Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
	            Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery("SELECT Title FROM stories WHERE Favourite = 1"); // sort to put favourites first
                while (rs.next()) {
                   	title = rs.getString("Title");
                   		favList.add(title); // add to list of titles
                }
                showFavourites(favList);
	     } catch (SQLException e) {
	            e.printStackTrace();
	        }
	} // end findFavourites()
	
	/* Method Name: findFavouritesSaved
	 * Author: Zainab Siddiqui
	 * Creation Date: January 23 2023
	 * Modified Date: January 24 2023
	 * Description: finds the favourited book titles from the database in saved books
	 * @Parameters: none
	 * @Return Value: none
	 * Data Type: void
	 * Dependencies: MySQL
	 * Throws/Exception: none
	 */ 
	public static void findFavouritesSaved(){
		
	     String title = ""; // title of favourited story
	     try {
	            Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
	            Statement stat = con.createStatement();

                ResultSet rs = stat.executeQuery("SELECT Title FROM stories WHERE Favourite = 1 AND SAVED = 1"); // sort to put favourites first
                StringBuilder titlesBuilder = new StringBuilder(); // store title
                while (rs.next()) {
                   	title = rs.getString("Title");
					favList.add(title); // add to list of titles
                }
                showFavourites(favList);
	     } catch (SQLException e) {
	            e.printStackTrace();
	        }
	} // end findFavourites()
	
	/* Method Name: showFavourites
	 * Author: Zainab Siddiqui
	 * Creation Date: January 23 2023
	 * Modified Date: January 24 2023
	 * Description: Displays the titles of stories that matched the selected filters in a message dialog
	 				*Future: add to a search page where the user can directly go to story
	 * @Parameters: ArrayList<String> titlesList
	 * @Return Value: none
	 * Data Type: n/a
	 * Dependencies: none
	 * Throws/Exceptions: none
	 */  
	public static void showFavourites(ArrayList<String> favouritesList) {
		StringBuilder message = new StringBuilder("* MY FAVOURITES * \n");
		for (String favourite : favouritesList) // iterate through the built titles list
	        message.append("- ").append(favourite).append("\n"); // display the story titles in the message dialog
	    JOptionPane.showMessageDialog(Homepage.homepageFrame, message.toString(), "* MY FAVOURITES *  ", JOptionPane.INFORMATION_MESSAGE);
	} // end showFavourites
	
    public static void main(String[] args) {
    	new Favourites();
    }
} // end Favourites

