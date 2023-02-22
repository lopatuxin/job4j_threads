package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content(pr -> true);
    }

    public String getContentWithoutUnicode() {
        return content(pr -> pr < 0x80);
    }

    private String content(Predicate<Integer> pred) {
        StringBuilder builder = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))){
            int data;
            while ((data = in.read()) != -1) {
                if (pred.test(data)) {
                    builder.append((char) data);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
