package filter.printer;

import filter.reader.WordReader;

import java.util.List;

public class FilterPrinter {
    private static final int MIN_NUM = 1;

    public void printResults() {
        WordReader wordReader = new WordReader();
        wordReader.countWords();
        List<String> badWords = wordReader.getSwearWordsFromFile();
        if (badWords.size() <= MIN_NUM) System.out.println("Swear Words Not Found");
        else System.out.println(wordReader.getSwearWordsFromFile());
    }
}
