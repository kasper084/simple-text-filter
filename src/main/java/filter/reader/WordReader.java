package filter.reader;

import filter.utils.Encoder;
import filter.writer.WordWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordReader {
    private static final String DATA = "C:\\Users\\kasper084\\Desktop\\data.txt";
    private static final String VOCABULARY = "C:\\Users\\kasper084\\Desktop\\vocabulary.txt";
    private static final String CHARACTERS = ",|\\(|\\)|\\'|!";
    private static final String REPLACEMENT = " ";
    private static final String REGEX = "\\s+";

    private List<String> shortWords = new ArrayList<>();

    private WordWriter writer = new WordWriter();

    private String readData(String path) {
        String data = null;
        try {
            data = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<String> getAllWords(String path) {
        List<String> words;
        String data = readData(path);
        if (data.contains(CHARACTERS)) {
            data = data.replaceAll(CHARACTERS, REPLACEMENT);
        }
        words = Arrays.asList(data.split(REGEX));
        return words;
    }

    public void countWords() {
        int num = 0;
        int swears = 0;
        int smallWord = 0;
        List<String> words = getAllWords(DATA);
        for (String word : words) {
            num++;
            if (isWordBad(word)) {
                swears++;
                writer.write(word);
            } else if (isWordBad(word) || word.length() < 3) {
                smallWord++;
                shortWords.add(word);
            }
        }
        System.out.println("Total num of words: " + num);
        System.out.println("Total without short and swear words: " + (num - (smallWord + swears)));
        System.out.println("All short words: " + "\n" + shortWords);
    }

    public List<String> getSwearWordsFromFile() {
        return getAllWords(WordWriter.getPATH());
    }

    private boolean isWordBad(String word) {
        return getSwearWordsFromFile().contains(Encoder.encode(word));
    }
}