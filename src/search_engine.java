import java.io.*;
import java.util.*;

public class search_engine {
    private File[] files;

    public search_engine(File[] files) 
    {
        this.files = files;
    }

    public List<File> search(String query) {
        Map<File, Integer> relevanceMap = new HashMap<>();
        for (File file : files) {
            int relevance = 0;
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    relevance += calculateRelevance(line, query);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (relevance > 0) {
                relevanceMap.put(file, relevance);
            }
        }

        List<File> results = new ArrayList<>(relevanceMap.keySet());
        results.sort((f1, f2) -> relevanceMap.get(f2) - relevanceMap.get(f1));
        return results;
    }

    private int calculateRelevance(String textSource, String query) 
    {
        int count = 0;
        int index = textSource.indexOf(query);
        while (index != -1) {
            count++;
            index = textSource.indexOf(query, index + 1);
        }
        return count;
    }
}