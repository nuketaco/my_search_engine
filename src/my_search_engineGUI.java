import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class my_search_engineGUI extends JFrame {
    private JTextField queryField;
    private JTextArea resultArea;

    public my_search_engineGUI(File[] files) {
        // Set up the GUI components
        queryField = new JTextField(20);
        resultArea = new JTextArea(10, 30);
        JButton searchButton = new JButton("Search");

        // Create a SearchEngine instance
        search_engine searchEngine = new search_engine(files);

        // Add an action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getText();
                List<File> results = searchEngine.search(query);
                resultArea.setText("");
                for (File file : results) {
                    resultArea.append(file.getName() + "\n");
                }
            }
        });

        // Lay out the components using a simple layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Query:"));
        panel.add(queryField);
        panel.add(searchButton);
        panel.add(new JScrollPane(resultArea));

        // Set up the frame
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Specify the text files to search
        File[] files = {new File("file1.txt"), new File("file2.txt"), new File("file3.txt")};

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new my_search_engineGUI(files).setVisible(true);
            }
        });
    }
}