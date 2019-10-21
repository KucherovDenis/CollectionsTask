package com.sbt.javaschool.collections;

import java.util.*;

/**
 * Класс для обработки текстового файла.
 * Реализует интерфейс {@code Iterable<String>} для вывода строк в обратном порядке.
 */
public class FileHandler implements Iterable<String> {

    private String fileName;

    /**
     * Метод возвращает список строк считанных из файла.
     *
     * @return список строк.
     */
    public List<String> getLines() {
        return lines;
    }

    private List<String> lines;

    /**
     * Создает объект для обработки текстового файла.
     *
     * @param fileName имя файла
     */
    public FileHandler(String fileName) {
        this.fileName = fileName;
        lines = new ArrayList<>();
    }

    /**
     * Метод инициализации. Выполяет чтение текстового файла построчно и сохраняет его в памяти.
     */
    public void initialize() {
        TextFileReader.readLines(fileName, lines);
    }

    /**
     * Разбиение строк на слова. В качестве разделителя используется пробел.
     *
     * @param words коллекция в которую добавляются отдельные слова.
     */
    private void getWords(Collection<String> words) {
        for (String line : lines)
            words.addAll(Arrays.asList(line.split("\\s+")));
    }

    /**
     * Метод подсчитывает количество различных слов в файле.
     *
     * @return количество слов
     */
    public int countDifferentWords() {
        Set<String> words = new HashSet<>();
        getWords(words);
        return words.size();
    }

    /**
     * Метод возвращает список различных слов файла,
     * отсортированных по возрастанию их длины
     * (компаратор сначала по длине слова, потом по тексту).
     *
     * @return отсортированный список
     */
    public SortedSet<String> sortWords() {
        SortedSet<String> result = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) return o1.compareToIgnoreCase(o2);
                else return o1.length() > o2.length() ? 1 : -1;
            }
        });
        getWords(result);
        return result;
    }

    /**
     * Класс подсчитывает число вхождений слова в тексте.
     * Чтобы не было постоянной упаковки, распаковки {@code Integer}.
     */
    private class Counter {
        int count = 0;

        public void inc() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * Метод подсчитывает сколько раз каждое слово встречается в файле.
     *
     * @return возвращает интерфейс {@code Map<String, Integer>}, где
     * {@code key} - слово, {@code value} - сколько раз слово встречается
     */
    public Map<String, Integer> countWords() {
        List<String> words = new ArrayList<>();
        getWords(words);

        Map<String, Counter> counterWords = new HashMap<>();
        for (String word : words) {
            Counter counter = counterWords.get(word);
            if (counter == null) {
                counter = new Counter();
                counterWords.put(word, counter);
            }
            counter.inc();
        }

        Map<String, Integer> resultMap = new HashMap<>();
        for (Map.Entry<String, Counter> entry : counterWords.entrySet())
            resultMap.put(entry.getKey(), entry.getValue().getCount());

        return resultMap;
    }

    /**
     * Метод делает копирование списка строк.
     *
     * @return писок строк
     */
    private List<String> copy() {
        List<String> result = new ArrayList<>();
        result.addAll(lines);
        return result;
    }


    /**
     * Метод возвращает список строк файла в обратном порядке.
     *
     * @return список строк
     */
    public List<String> reverse() {
        List<String> result = copy();
        Collections.reverse(result);
        return result;
    }

    /**
     * Метод возвращает строку по заданному индексу.
     * @param index номер строки
     * @return возвращает строку, если строки по задданному индексу нет вернет {@code null}
     */
    public String getLine(int index) {
        if (index < 0 || index >= lines.size()) {
            return null;
        } else return lines.get(index);
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseLines();
    }

    /**
     * Класс реализующий обход списка в обратном порядке.
     */
    private class ReverseLines implements Iterator<String> {

        private int count;

        public ReverseLines() {
            count = lines.size();
        }

        @Override
        public boolean hasNext() {
            return count != 0;
        }

        @Override
        public String next() {
            String line = lines.get(count - 1);
            count--;
            return line;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
