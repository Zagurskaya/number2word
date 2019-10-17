package main.java.com.gmail.zagurskaya.number2word;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {

    public List<String> readNumbersFromFileToList(String fileName) {
        String initialFileName = getClass().getResource(fileName).getPath();
        try {
            return Files.lines(Paths.get(initialFileName), StandardCharsets.UTF_8).collect(Collectors.toList());
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading file [" + fileName + "]", e);
        }

    }

}

