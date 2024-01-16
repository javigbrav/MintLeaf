package StoryInteraction;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Annotations extends JFrame {
    private JTextArea textArea;
    private JButton button;
    
    
    public Annotations() {
        super("Annotation");
        initComponents();
    }

    private void initComponents() {
        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        button = new JButton ("Save Annotation");
        
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        
        add (button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String content = textArea.getText();
            	System.out.println("Text: " + content);
                //System.out.println("button works");
                
                
            }
        });
        
    }
    
    public void addAnnotation(String selectedText) {// i need to save it to text files. What i'' do is that i'll take the index and save it in the textfile and that way it can be connected
        textArea.append("\n[Annotation]: \"" + selectedText + "\"\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Annotations();
        });
    }
}
