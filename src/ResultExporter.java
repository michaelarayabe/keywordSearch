import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultExporter {
    public void exportToCSV(List<String> results, String outputPath) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))){
            for(String result : results){
                writer.write(result);
                writer.newLine();
            }
        }
    }
}
