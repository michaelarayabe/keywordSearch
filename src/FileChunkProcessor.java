import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChunkProcessor implements Callable<List<String>> {
    private final String filePath;
    private final long start;
    private final long end;
    private final String keyword;
    private final boolean caseSensitive;

    public FileChunkProcessor(String filePath, long start, long end, String keyword, boolean caseSensitive) {
        this.filePath = filePath;
        this.start = start;
        this.end = end;
        this.keyword = keyword;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> results = new ArrayList<>();
        try(RandomAccessFile file = new RandomAccessFile(filePath, "r")){
            // Move the file pointer to the start of the chunk
            file.seek(start);

            // Read lines within the chunk range
            String line;
            long currentPosition = start;

            //prepare the regex pattern
            String regex = caseSensitive ? keyword : "(?i)" + keyword;
            Pattern pattern = Pattern.compile(regex);

            while(currentPosition < end && (line = file.readLine()) != null){
                currentPosition = file.getFilePointer(); //update current positon
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    results.add(line);
                }
            }
        }
        return results;
    }
}
