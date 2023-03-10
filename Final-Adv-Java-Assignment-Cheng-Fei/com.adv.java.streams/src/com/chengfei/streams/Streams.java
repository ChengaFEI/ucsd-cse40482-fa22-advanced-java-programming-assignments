package com.chengfei.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Streams {

    public static void streams(String[] args) {
        if (args.length < 1) {
            System.out.println("Argument required");
        }
        // Load the file.
        String pathStr = args[0];
        String article = loadFromPath(pathStr);

        int i = 0;
        long numPattern;
        long startTime, endTime, streamLapse, pStreamLapse, lapseDiff;
        String regex = "^0{8}[0-9a-fA-F]{8}$";
        // Loop until the ParallelStream out-performs the Stream.
        while (true) {
            i++;
            System.out.printf("Try %d\n", i);
            System.out.printf("String size: %d\n", article.length());
            List<String> words = Arrays.asList(article.split("[^0-9a-zA-Z]+"));

            // Try to use stream.
            startTime = System.currentTimeMillis();
            numPattern = words.stream()
                    .filter(w -> Pattern.matches(regex, w))
                    .count();
            endTime = System.currentTimeMillis();
            streamLapse = endTime - startTime;
            System.out.printf("Milliseconds using stream: %d\n", streamLapse);

            // Try to use parallelStream.
            startTime = System.currentTimeMillis();
            numPattern = words.parallelStream()
                    .filter(w -> Pattern.matches(regex, w))
                    .count();
            endTime = System.currentTimeMillis();
            pStreamLapse = endTime - startTime;
            System.out.printf("Milliseconds using parallelStream: %d\n", pStreamLapse);

            lapseDiff = streamLapse - pStreamLapse;
            if (lapseDiff <= 0) {
                System.out.printf("Results: stream was %d milliseconds faster than parallelStream\n", -lapseDiff);
                System.out.println("Doubling String size and trying again");
                article += article;
            }
            else {
                System.out.printf("Results: parallelStream was %d milliseconds faster than stream.\n", lapseDiff);
                break;
            }
        }
    }

    /********************************************
     * Load the file by the relative path string.
     * @param pathStr: relative path of the file.
     * @return all contents in the string format.
     * @require the file exists in the path.
     ********************************************/
    private static String loadFromPath(String pathStr) {
        Path path = Paths.get(pathStr);
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(path);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something goes wrong with loading file!");
        }
        assert bytes != null;
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
