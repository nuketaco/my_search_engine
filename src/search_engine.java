import java.util.*;

public class search_engine 
{
    private List<String> textSources;

    public search_engine(List<String> textSources) 
    {
        this.textSources = textSources;
    }

    public List<SearchResult> search(String query) 
    {
        List<SearchResult> results = new ArrayList<>();

        for (String textSource : textSources) 
        {
            int relevance = calculateRelevance(textSource, query);
            if (relevance > 0)
            {
                results.add(new SearchResult(textSource, relevance));
            }
        }

        Collections.sort(results);
        return results;
    }

    public static int calculateRelevance(String textSource, String query) 
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

class SearchResult implements Comparable<SearchResult> 
{
    private String textSource;
    private int relevance;

    public SearchResult(String textSource, int relevance) 
    {
        this.textSource = textSource;
        this.relevance = relevance;
    }

    public String getTextSource() 
    {
        return textSource;
    }

    public int getRelevance() {

        return relevance;
    }

    @Override
    public int compareTo(SearchResult other) 
    {
        return other.relevance - this.relevance;
    }
}