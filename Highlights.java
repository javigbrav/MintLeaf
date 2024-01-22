

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Highlight {
	//static Color initialColor;
	static JTextPane textPane;
	Highlight (Color highlightColor) {
		highlightSelectedText (highlightColor);
	}
	public static void setTextPane(JTextPane textPane1) {
		textPane = textPane1;
	}
	public void highlightSelectedText(Color highlightColor) {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        if (start != end) {
            try {
                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(highlightColor);
                textPane.getHighlighter().addHighlight(start, end, painter);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
        
        
    }
	private static void saveHighlight (int start, int end) {
		
	}
}
