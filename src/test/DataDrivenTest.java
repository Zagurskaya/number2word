package test;

import main.java.com.gmail.zagurskaya.number2word.Converter;
import main.java.com.gmail.zagurskaya.number2word.Reader;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DataDrivenTest {
    private Converter converter = new Converter();

    @Test
    public void testNumberToWords() {
        Reader reader = new Reader();
        List<String> numbersList = reader.readNumbersFromFileToList("/number.txt");
        List<String> wordsList = reader.readNumbersFromFileToList("/numberToWords.txt");

        for (int i = 0; i < Math.min(numbersList.size(), wordsList.size()); i++) {
            String result = converter.numberToWords(numbersList.get(i));
            assertEquals(wordsList.get(i).trim(), result);
        }
    }
}
