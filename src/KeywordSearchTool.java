import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class KeywordSearchTool {
    public static void main(String[] args){
        FileHandler fileHandler = new FileHandler();
        SearchEngine searchEngine = new SearchEngine(false);
        ResultExporter exporter = new ResultExporter();

        try(BufferedReader reader = fileHandler.getFileReader("textFile.txt")){
            List<String> results = searchEngine.search(reader, "909Lorem");
            exporter.exportToCSV(results, "output.csv");
            System.out.println("Search completed. Results saved to output.csv");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
