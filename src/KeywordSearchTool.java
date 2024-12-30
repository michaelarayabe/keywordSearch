import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class KeywordSearchTool {
    public static void main(String[] args){
        String filePath = "textFile.txt";
        String keyword = "909Lorem";
        boolean caseSensetive = false;

        ThreadManager threadManager = new ThreadManager(4);

        threadManager.processFile(filePath, keyword, caseSensetive);
    }
}
