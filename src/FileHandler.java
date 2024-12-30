import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public BufferedReader getFileReader(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }
}