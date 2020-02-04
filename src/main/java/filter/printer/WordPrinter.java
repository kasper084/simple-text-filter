package filter.printer;

import filter.reader.ReaderInThread;
import filter.reader.WordReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordPrinter {

    public void printResults() {
        WordReader wordReader = new WordReader();
        wordReader.countWords();
        wordReader.findMostRepeatedWords();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new ReaderInThread());
        service.shutdown();
    }
}