package ru.job4j.io;

import java.io.*;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (FileWriter out = new FileWriter(file, true)) {
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
