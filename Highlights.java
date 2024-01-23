/***********************************************************************************
 * Author: Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified: January 17, 2024
 * Last Modified by: Javiera Garrido
 * Description: Class that represents a book and allows to interact with it
 ***********************************************************************************/

import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Highlights {
	//static Color initialColor;
	static JTextPane textPane;
	
	Highlights(Color highlightColor) {
		highlightSelectedText (highlightColor);
	}
	
	//setter for text pane
	public static void setTextPane(JTextPane textPane1) {textPane = textPane1;}
	
	/* Method Name: highlightSelectedText
	 * Author: Javiera Garrido Bravo
	 * Creation Date: December 20, 2023
	 * Modified Date: January 17, 2024
	 * Description: highlights the text selected with the desired color
	 * @Parameters: Color highlightColor: color in which the text will be highlighted
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: n/a
	 * Throws/Exceptions: BadLocationException
	 */  
	public void highlightSelectedText(Color highlightColor) {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        if (start != end) {
            try {
                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(highlightColor);
                textPane.getHighlighter().addHighlight(start, end, painter);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } // end try/catch
        } // end if  
    }
}