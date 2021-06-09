package collection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPrint {
    public static void main(String[] args) throws IOException {
        final int assumedLineLength = 20;
        File file = new File(args[0]);
        List<String> fileList = new ArrayList<>((int)(file.length()/assumedLineLength) * 2);
        int lineCount = 0;

        try (
            BufferedReader reader = Files.newBufferedReader(file.toPath());
        ) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                fileList.add(line);
                lineCount++;
            }
        }
        int repeats = Integer.parseInt(args[1]);
        Random random = new Random();
        for (int i = 0; i < repeats; i++) {
            System.out.println(i + ": " + fileList.get(random.nextInt(lineCount - 1)));
        }
    }
}
