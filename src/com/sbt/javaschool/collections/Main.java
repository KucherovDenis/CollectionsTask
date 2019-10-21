package com.sbt.javaschool.collections;

import java.util.*;


public class Main {
    private static final String TASK_HEADER = "Задание №%d";
    private static final String TASK_SEPARATOR = "----------------------------------";
    private static int taskCounter = 1;

    private static FileHandler fileHandler;

    private interface Task {
        void call();
    }

    private static Task[] tasks = new Task[]{
            new Task() {
                @Override
                public void call() {
                    printCountDifferentWords(fileHandler);
                    taskCounter++;
                }
            },

            new Task() {
                @Override
                public void call() {
                    printSortedWords(fileHandler);
                    taskCounter++;
                }
            },


            new Task() {
                @Override
                public void call() {
                    printCountWords(fileHandler);
                    taskCounter++;
                }
            },

            new Task() {
                @Override
                public void call() {
                    printReverseFile(fileHandler);
                    taskCounter++;
                }
            },

            new Task() {
                @Override
                public void call() {
                    printReverseUseIterator(fileHandler);
                    taskCounter++;
                }
            },

            new Task() {
                @Override
                public void call() {
                    printSelectedLine(fileHandler);
                    taskCounter++;
                }
            },
    };

    public static void main(String[] args) {
        String fileName;
        if (args.length == 1) fileName = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя файла: ");
            fileName = in.nextLine();
            in.close();
        }

        fileHandler = new FileHandler(fileName);
        fileHandler.initialize();

        for (Task task : tasks)
            task.call();
    }

    /**
     * Задание 1: Подсчитайте количество различных слов в файле.
     */
    private static void printCountDifferentWords(FileHandler fileHandler) {
        System.out.println(String.format(TASK_HEADER, taskCounter));
        System.out.println("Количество различных слов в файле: " + fileHandler.countDifferentWords());
        System.out.println(TASK_SEPARATOR);
    }

    /**
     * Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).
     */
    private static void printSortedWords(FileHandler fileHandler) {
        System.out.println(String.format(TASK_HEADER, taskCounter));
        System.out.println("Отсортированный список:");
        SortedSet<String> sortedWords = fileHandler.sortWords();
        for (String word : sortedWords)
            System.out.println(word);
        System.out.println(TASK_SEPARATOR);
    }

    /**
     * Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
     */
    private static void printCountWords(FileHandler fileHandler) {
        System.out.println(String.format(TASK_HEADER, taskCounter));
        System.out.println("Подсчет слов:");
        Map<String, Integer> words = fileHandler.countWords();
        for (String word : words.keySet()) {
            System.out.println(word + ":" + words.get(word));
        }
        System.out.println(TASK_SEPARATOR);
    }

    /**
     * Задание 4: Выведите на экран все строки файла в обратном порядке.
     */
    private static void printReverseFile(FileHandler fileHandler) {
        System.out.println(String.format(TASK_HEADER, taskCounter));
        System.out.println("Файл в обратном порядке:");
        for (String line : fileHandler.reverse())
            System.out.println(line);
        System.out.println(TASK_SEPARATOR);
    }

    /**
     * Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
     */
    private static void printReverseUseIterator(FileHandler fileHandler) {
        System.out.println(String.format(TASK_HEADER, taskCounter));
        System.out.println("Исходный файл:");
        for (String line : fileHandler.getLines())
            System.out.println(line);

        System.out.println("Файл в обратном порядке используя итератор:");
        for (String line : fileHandler)
            System.out.println(line);
        System.out.println(TASK_SEPARATOR);
    }

    /**
     * Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
     */
    private static void printSelectedLine(FileHandler fileHandler) {
        System.out.println(String.format(TASK_HEADER, taskCounter));
        int lineNum = 0;
        System.out.println("Исходный файл:");
        List<String> lines = fileHandler.getLines();
        for (String line : lines)
            System.out.println((lineNum++) + " " + line);

        System.out.println("Введите номер произвольной строки:");
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            lineNum = in.nextInt();
            if (lineNum < 0 || lineNum >= lines.size()) {
                System.out.println("Такой строки нет.");
            } else {
                System.out.println(lines.get(lineNum));
            }
            System.out.println("Введите номер произвольной строки:");
        }

        System.out.println(TASK_SEPARATOR);
    }
}
