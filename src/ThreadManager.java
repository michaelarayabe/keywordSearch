import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadManager {
    private final ExecutorService executor;

    public ThreadManager(int threadCount){
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public void processFile(String filePath, String keyword, boolean caseSensitive) {
        File file = new File(filePath);
        long fileSize = file.length();
        int chunkCount = 4; //number of thread
        long chunkSize = fileSize/chunkCount;

        List<Future<List<String>>> results = new ArrayList<>();

        for(int i = 0; i < chunkCount; i++){
            long start = i * chunkSize;
            long end = (i == chunkCount - 1) ? fileSize : start + fileSize;
            results.add(executor.submit(new FileChunkProcessor(filePath, start, end, keyword, caseSensitive)));
        }

        for(Future<List<String>> future : results){
            try {
                List<String> matchedLines = future.get();
                matchedLines.forEach(System.out::println);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
