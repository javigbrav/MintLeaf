/***********************************************************************************
 * Author: Javiera Garrido Bravo 
 * Date: January 15, 2023 
 * Last Modified: January 22, 2024
 * Last Modified by: Javiera Garrido
 * Description: Translates story into original language
 ***********************************************************************************/

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Translator {
	private static JTextPane pane;
	private static JScrollPane scrollPane;
	private static boolean hasBeenClicked;
	
	//to save if the button has been clicked
	public static void setHasBeenClicked (boolean hasBeenClicked1) {
		hasBeenClicked = hasBeenClicked1;
	}
	//to return once the button has been clicked in here
	public static boolean getHasBeenClicked () {
		return hasBeenClicked;
	}
	//sets the Text Pane
	public static void setPane (JTextPane textPane){
		pane = textPane;
	}
	//Sets the scroll pane
	public static void setScrollBar(JScrollPane scroll){
		scrollPane = scroll;
	}
	public static void scrollPosition () {
		SwingUtilities.invokeLater(() -> {
            // Set the scroll position to the top
            scrollPane.getVerticalScrollBar().setValue(0);
        });
	}
	
	Translator (String bookName, String storyOriginalText, String storyText){	
		pane.setText("");
		if ( ! hasBeenClicked ) {
			StoryInteraction.displayStory(bookName, storyOriginalText);		
			scrollPosition();
        } else {
        	StoryInteraction.displayStory(bookName, storyText);
        	scrollPosition();
        }
        hasBeenClicked = !hasBeenClicked;	
	}
}