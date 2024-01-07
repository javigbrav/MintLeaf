package Homepage;
import java.util.HashMap;
/***********************************************************************************
 * Author: Zainab Siddiqui 
 * Date: December 20, 2023 
 * Last Modified:
 * Last Modified by: 
 * Description: Store a user's login information and story preferences
 ***********************************************************************************/
public class User {
	
	/*Globals*/
	HashMap<String, String> logininfo = new HashMap<String, String>(); // temp storage of username and password
	public String[] preferences = new String[10]; // change to how many items there are in here
	
	/*Constructor*/
	User(String username, String userPassword){
		LoginPage.logininfo.put(username, userPassword);
		logininfo.put("Mint_Leaf", "logic4"); // need to add new user with this info to database so we can demo someone's account
	}
	
	// temporary -- need to combine with MySQL
	public HashMap<String, String> getLoginInfo() {
		return logininfo;
	}
	
	public void updatePreferences(String[] preferences){
		this.preferences = preferences;
	}
} // end User
