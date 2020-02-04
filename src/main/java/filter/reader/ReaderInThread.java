package filter.reader;

import filter.writer.WordWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReaderInThread implements Runnable {
    private static final String CHARACTERS = ",";
    private static final String REPLACEMENT = "";
    private static final String REGEX = "\\s+";
    private static final int MIN_NUM = 1;

    @Override
    public void run() {
        List<String> words = Collections.emptyList();
        try {
            String data = Files.readString(Paths.get(WordWriter.getPATH()));
            data = data.replaceAll(CHARACTERS, REPLACEMENT);
            words = Arrays.asList(data.split(REGEX));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (words.size() <= MIN_NUM) System.out.println("No Swear Words Found");
        else System.out.println(words);
    }
}