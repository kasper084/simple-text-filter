package filter;

import filter.reader.Reader;

public class App
{
    public static void main( String[] args ) {
        Reader reader = new Reader();
        reader.countWords();
    }
}