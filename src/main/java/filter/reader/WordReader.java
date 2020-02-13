package filter.reader;

import filter.utils.Encoder;
import filter.writer.WordWriter;

import java.io.*;
import java.nio.file.Files;
import java.util.*;


public class WordReader {
    private static final String DATA_FILE = "data.txt";
    private static final String VOCABULARY_FILE = "vocabulary.txt";
    private static final String EXTRA_CHARS = ",|!|\\(|\\)|\\.";
    private static final String CHARS_REPLACEMENT = " ";
    private static final String REGEX = "\\s+";
    private static final int WORDS_LIMIT = 20;

    private Set<String> setOfShortWords = new LinkedHashSet<>();
    private Map<String, Integer> mapOfMostUsedWords = new HashMap<>();

    private WordWriter writer = new WordWriter();

    private String readData(String path) {

        File file = new File(Objects.requireNonNull(WordReader.class.getClassLoader().getResource(path).getFile()));

        String data = null;

        try {
            data = Files.readString(file.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private List<String> getAllWords(String path) {

        List<String> words;

        String data = readData(path);
        data = data.replaceAll(EXTRA_CHARS, CHARS_REPLACEMENT);

        words = Arrays.asList(data.split(REGEX));
        return words;
    }

    public void countWords() {
        int num = 0;
        int swears = 0;
        int smallWord = 0;

        List<String> words = getAllWords(DATA_FILE);

        for (String word : words) {
            mapOfMostUsedWords.put(word, num);
            num++;

            if (isBadWord(word)) {
                swears++;
                writer.write(Encoder.encode(word));

            } else if (word.length() < 3) {
                smallWord++;
                setOfShortWords.add(word);
            }
        }

        System.out.printf("Total num of words: %d%n", num);

        System.out.printf("Total without short and swear words: %d%n", num - (smallWord + swears));

        System.out.printf("All short words: \n%s%n", setOfShortWords);
    }

    private boolean isBadWord(String word) {

        String some = getAllWords(VOCABULARY_FILE).stream()
                .filter(badWord -> Encoder.decode(badWord).equals(word))
                .findAny().orElse(null);
        return word.equals(some);
    }

    public void findMostRepeatedWords() {

        System.out.println("Most used words by descending order:");

        List<String> list = new ArrayList<>(mapOfMostUsedWords.keySet());

        for (int some = 0; some < WORDS_LIMIT; some++) {
            System.out.printf("%s / ", list.get(some));
        }
    }
}