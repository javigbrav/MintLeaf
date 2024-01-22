package LogIn;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
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
        hasBeenClicked = ! hasBeenClicked;
		
	}

}
