package com.sbt.javaschool.collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;

/**
 * Класс для чтения текстового файла.
 */
public class TextFileReader {
    /**
     * Чтение строк из файла {@code fileName} и добавление в коллекцию {@code lines}.
     *
     * @param fileName имя файла
     * @param lines    коллекция в которую добавляются строки текстового файла
     */
    public static void readLines(String fileName, Collection<String> lines) {
        File file = new File(fileName);
        if (!file.isFile()) return;

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty())
                    lines.add(line);
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
