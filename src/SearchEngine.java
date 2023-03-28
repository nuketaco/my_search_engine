import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JTextArea;

public class SearchEngine 
{
    public void search(File[] files, String[] searchTerms, String searchMode, JTextArea textArea) {
        for (File file : files) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                boolean found = false;
                if (searchMode.equals("All")) {
                    found = true;
                    for (String searchTerm : searchTerms) {
                        if (content.indexOf(searchTerm) < 0) {
                            found = false;
                            break;
                        }
                    }
                } else {
                    for (String searchTerm : searchTerms) {
                        if (content.indexOf(searchTerm) >= 0) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    textArea.append(file.getName() + " contains " + (searchMode.equals("All") ? "all" : "at least one of") + " the search terms.\n");
                } else {
                    textArea.append(file.getName() + " does not contain " + (searchMode.equals("All") ? "all" : "any of") + " the search terms.\n");
                }
            } catch (IOException e) {
                textArea.append("Error reading file: " + file.getName() + "\n");
            }
        }
    }
}