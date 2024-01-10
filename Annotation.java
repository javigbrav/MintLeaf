package StoryInteraction;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextAnnotationApp extends JFrame {
    private JTextArea textArea;
    private JTextField annotationField;

    public TextAnnotationApp() {
        super("Text Annotation App");
        initComponents();
    }

    private void initComponents() {
        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        annotationField = new JTextField(20);
        JButton addAnnotationButton = new JButton("Add Annotation");
        JButton connectToHomepageButton = new JButton("Connect to Homepage");

        addAnnotationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnnotation();
            }
        });

        connectToHomepageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToHomepage();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Annotation:"));
        controlPanel.add(annotationField);
        controlPanel.add(addAnnotationButton);
        controlPanel.add(connectToHomepageButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addAnnotation() {
        String annotation = annotationField.getText();
        textArea.append("\n[Annotation]: " + annotation + "\n");
        annotationField.setText(""); // Clear the annotation field
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextAnnotationApp();
            }
        });
    }
}
