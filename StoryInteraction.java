import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class StoryInteraction extends JFrame {
	
	//static Color initialColor = Color.yellow;
	static boolean hasBeenClickedT = false; //to check if the translator button has already been clicked
	private JScrollPane scrollPane;
    private static JTextPane textPane;
    private static String bookName = "book1.txt";
    JButton buttonHigh = new JButton ("Highlight");
    JButton translatorButton = new JButton ("Translator");
    JButton buttonChoose = new JButton ("Choose Highlighter Color");
    static Color highlightColor=Color.yellow;//default highlight color yellow
    
    private JButton annotateButton = new JButton("Add Annotation");
    private Annotations annotationFrame;
    public StoryInteraction(String bookName) {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        textPane = new JTextPane();
        setDefaultFont();  // Set default font for the JTextPane

        //textPane.setEditable(false);
        
        scrollPane = new JScrollPane(textPane);
        
        add(scrollPane, BorderLayout.CENTER);

        openAndDisplayFile(bookName);

        SwingUtilities.invokeLater(() -> {
            // Set the scroll position to the top
            scrollPane.getVerticalScrollBar().setValue(0);
        });
        
        buttonHigh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button works");
                Highlight.setTextPane (textPane);
                new Highlight (highlightColor);
            }
        });
        annotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	annotationFrame = new Annotations();
                showAnnotationFrame();
            }
        });
        
        translatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Translator.setPane (textPane);
            	Translator.setScrollBar(scrollPane);
            	Translator.setHasBeenClicked(hasBeenClickedT);
            	new Translator(bookName);
            	hasBeenClickedT = Translator.getHasBeenClicked ();
            }
        });
        
        buttonChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				highlightColor=chooseColor();
			}
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonChoose);
        buttonPanel.add(buttonHigh);
        buttonPanel.add(annotateButton);
        buttonPanel.add(translatorButton);
        add(buttonPanel, BorderLayout.SOUTH);


//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem openItem = new JMenuItem("Open");
        //openItem.addActionListener(e -> openAndDisplayFile("book1.txt"));  // Replace with the actual path
        //fileMenu.add(openItem);
        ///menuBar.add(fileMenu);

        //setJMenuBar(menuBar);
    }

    private void setDefaultFont() {
        // Set default font for the JTextPane (replace this with your desired default font)
        Font defaultFont = new Font("Serif", Font.PLAIN, 14);
        textPane.setFont(defaultFont);
    }

    static void openAndDisplayFile(String filePath) {
        try {
            String[] fileLines = readFile(filePath);
            displayFileContent(fileLines);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String[] readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().toArray(String[]::new);
        }
    }

    private static void displayFileContent(String[] fileLines) {
        textPane.setText(""); // Clear existing text

        for (int i = 0; i < fileLines.length; i++) {
            String line = fileLines[i];
            SimpleAttributeSet attributes = new SimpleAttributeSet();

            // Set different font for the first line (replace this with your desired first-line font)
            if (i == 0) {
                StyleConstants.setBold(attributes, true);
                StyleConstants.setFontFamily(attributes, "SansSerif");
                StyleConstants.setFontSize(attributes, 16);
            } else {
                StyleConstants.setBold(attributes, false);
                StyleConstants.setFontFamily(attributes, "Serif");
                StyleConstants.setFontSize(attributes, 14);
            }

            textPane.setCharacterAttributes(attributes, true);
            textPane.replaceSelection(line + "\n");
        }
    }
//    private void highlightSelectedText(Color highlightColor) {
//        int start = textPane.getSelectionStart();
//        int end = textPane.getSelectionEnd();
//
//        if (start != end) {
//            try {
//                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(highlightColor);
//                textPane.getHighlighter().addHighlight(start, end, painter);
//            } catch (BadLocationException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
    private void showAnnotationFrame() {
        if (textPane.getSelectedText() != null) {
            String selectedText = textPane.getSelectedText();
            annotationFrame.addAnnotation(selectedText);
            annotationFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No text selected for annotation", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Color chooseColor (){
		Color color=JColorChooser.showDialog(null,"Select a color",highlightColor);    
		highlightColor = color;
		return color;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StoryInteraction StoryInteraction = new StoryInteraction(bookName);
            StoryInteraction.setVisible(true);
        });
    }
}
