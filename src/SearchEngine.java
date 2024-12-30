import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {
    private boolean caseSensitive;

    public SearchEngine(boolean caseSensitive){
        this.caseSensitive = caseSensitive;
    }

    public List<String> search(BufferedReader reader, String keyword) throws IOException {
        List<String> results = new ArrayList<>();
        String line;
        String regex = caseSensitive ? keyword : "(?i)" + keyword;
        Pattern pattern = Pattern.compile(regex);

        while((line = reader.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){
                results.add(line);
            }
        }
        return results;
    }
}
