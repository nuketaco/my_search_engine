//date 05/04/2023
//author: filip kruk
//program name: mysearchengineGUI
//program description: this program creates a GUI for the search engine
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



public class my_search_engineGUI extends JFrame 
{
    private static final String[] f_names = {"games.csv", "IMDB Top 250 Movies.csv", "languages.csv", "mobiles.csv", "Top 50 US Tech Companies 2022 - 2023.csv"};
    private JTextField searchTermField;
    private JTextArea resultsArea;

    public my_search_engineGUI() 
    {
        super("my search engine");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        // Set the background color and font for the text field and text area
        Color turquoise = new Color(64, 224, 208);
        Font consolas14 = new Font("Consolas", Font.BOLD, 14);

        searchTermField = new JTextField();
        searchTermField.setBackground(turquoise);
        searchTermField.setFont(consolas14);

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setBackground(turquoise);
        resultsArea.setFont(consolas14);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() 
        {
            // Create a message box to display the search term
            public void actionPerformed(ActionEvent e)
            {
                
                String message = "You are searching for the word: " + searchTermField.getText();
                JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = pane.createDialog(null, "Search Message");
                
                // Set the size of the message box
                dialog.setSize(500, 200);
                dialog.setVisible(true);     
                searchFiles();
            }
        });
        searchButton.setBackground(Color.GREEN); // Set the background color of the  search button to green
        searchButton.setFont(consolas14);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                searchTermField.setText("");
                resultsArea.setText("");
                
            }
        });
        cancelButton.setBackground(Color.orange); // set the background color of the cancelbutton to orange
        cancelButton.setFont(consolas14);

        JButton endButton = new JButton("End");
        endButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the program?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            
                if (choice == JOptionPane.YES_OPTION) 
                {
                    dispose(); // close the JFrame
                }
            }
        });
        endButton.setBackground(Color.red); // set the background color of the endbutton to red
        endButton.setFont(consolas14);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());// create a new panel with FlowLayout
        buttonPanel.add(searchButton); // add the search button to the panel
        buttonPanel.add(cancelButton);//add the cancel button
        buttonPanel.add(endButton);//add the end button
        
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Enter the word to be searched :"), BorderLayout.WEST);
        searchPanel.add(searchTermField, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.EAST);

        // Set the background color of the main panel to turquoise
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(turquoise);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);
        getContentPane().add(mainPanel);

        // Set the background color of the JFrame to turquoise
        getContentPane().setBackground(turquoise);
    }

    private void searchFiles() 
    {
        String[] searchword = searchTermField.getText().trim().toLowerCase().split("\\s+");
        Map<String, Integer> results = new HashMap<>();
        
        for (String filename : f_names) 
        {
            int wordCount = 0;
            
            try 
            {
                File file1 = new File(filename);
                Scanner scanner = new Scanner(file1);
                
                while (scanner.hasNextLine()) 
                {
                    String line = scanner.nextLine().toLowerCase();
                    boolean WordsFound = true;
                    
                    for (String term : searchword) 
                    {
                        if (!line.contains(term)) 
                        {
                            WordsFound = false;
                            break;
                        }
                    }
                    
                    if (WordsFound) 
                    {
                        wordCount++;
                    }
                }
                scanner.close();
            } 
            
            catch (FileNotFoundException ex) 
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "File not found: " + filename, "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if (wordCount > 0) 
            {
                results.put(filename, wordCount);
            }
        }
    
        if (results.isEmpty()) 
        {
            resultsArea.setText("No matches found.");
        } 
        
        else 
        {
            StringBuilder sb = new StringBuilder();
            sb.append("these Matches have been found in the following files:\n");
            results.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry -> sb.append(String.format("%s (%d matches)\n", entry.getKey(), entry.getValue())));
            resultsArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new my_search_engineGUI().setVisible(true);
            }
        });
    }
}