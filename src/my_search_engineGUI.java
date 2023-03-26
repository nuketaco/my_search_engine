import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public class my_search_engineGUI extends JFrame 
{
    private JLabel searchLabel;
    private JTextField searchField;
    private JLabel filesLabel;
    private JCheckBox file1CheckBox;
    private JCheckBox file2CheckBox;
    private JCheckBox file3CheckBox;
    private JButton searchButton;
    private JTextArea resultsArea;

    public my_search_engineGUI() 
    {
        setTitle("My Search Engine");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0, 255, 255)); // set cyan background color


        searchLabel = new JLabel("Search term:");
        searchField = new JTextField(20);
        filesLabel = new JLabel("Select files to search:");
        file1CheckBox = new JCheckBox("File 1");
        file2CheckBox = new JCheckBox("File 2");
        file3CheckBox = new JCheckBox("File 3");
        searchButton = new JButton("Search");
        resultsArea = new JTextArea(10, 40);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(searchField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(filesLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(file1CheckBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(file2CheckBox, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(file3CheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        panel.add(searchButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(new JScrollPane(resultsArea), gbc);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        new my_search_engineGUI();
    }
}