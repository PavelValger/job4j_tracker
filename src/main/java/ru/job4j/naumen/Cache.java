package ru.job4j.naumen;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Класс кэширует и упорядочивает результаты запросов к серверу.
 *
 * @author PAVEL VALGER
 * @version 1.0
 */
public class Cache {
    /**
     * Указывает кодировку для файлов.
     */
    private static final Charset CODING = StandardCharsets.UTF_8;
    private static final int MIN_NUMBER_REQUESTS = 1;
    private static final int MAX_NUMBER_REQUESTS = 100000;

    /**
     * Хранилище запросов. Key - запрос, value - последовательность запросов.
     */
    private final Map<String, ArrayList<Integer>> storage = new LinkedHashMap<>();
    /**
     * Хранилище запросов.
     */
    private final List<String> requests = new LinkedList<>();
    /**
     * Хранилище кэша.
     */
    private final Map<String, ArrayList<Integer>> cache = new HashMap<>();
    /**
     * Указывает необходимость пересмотра кэша.
     */
    private boolean flag = true;
    /**
     * Счетчик кэша.
     */
    private int countCache;
    /**
     * Счетчик запросов.
     */
    private int countRequests;

    /**
     * Метод считывает исходные данные с файла, заполняет хранилища и проводит валидацию входных данных.
     * В случае ошибки данных бросает исключение.
     *
     * @param fileName имя считываемого файла.
     */
    private void readFile(String fileName) {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName, CODING))) {
            var strings = in.readLine().split(" ");
            countCache = Integer.parseInt(strings[0]);
            countRequests = Integer.parseInt(strings[1]);
            boolean validateCache = countCache < MIN_NUMBER_REQUESTS || countCache > MAX_NUMBER_REQUESTS;
            boolean validateRequest = countRequests < MIN_NUMBER_REQUESTS || countRequests > MAX_NUMBER_REQUESTS;
            if (validateCache || validateRequest) {
                throw new Exception();
            }
            int index = 1;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                requests.add(line);
                storage.putIfAbsent(line, new ArrayList<>());
                storage.get(line).add(index);
                index++;
            }
            if (index - 1 != countRequests) {
                throw new Exception();
            }
            for (var entry : storage.entrySet()) {
                if (entry.getValue().size() == 1) {
                    entry.getValue().remove(0);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Неверные аргументы");
        }
    }

    /**
     * Метод записывает результат работы программы в файл output.txt.
     *
     * @param rsl конечный результат.
     */
    private void writeFile(int rsl) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter("output.txt", CODING, false)))) {
            out.print(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод удаляет из кэша данные и заполняет новыми.
     *
     * @param key удаляемые данные.
     * @param req новые данные.
     */
    private void addToCache(String key, String req) {
        cache.remove(key);
        cache.put(req, storage.get(req));
        flag = true;
    }

    /**
     * Метод считывает исходные данные с файла (размер кэша, количество и последовательность запросов),
     * формирует и сортирует кэш, и записывает минимально-необходимое количество обращений к распределенной
     * системе в файл output.txt.
     *
     * @param fileName имя считываемого файла, исходные данные.
     */
    public void start(String fileName) {
        int counter = 0;
        readFile(fileName);
        var it = storage.entrySet().iterator();
        int index = 0;
        while (it.hasNext() && index < countCache) {
            var iterator = it.next();
            index++;
            cache.put(iterator.getKey(), iterator.getValue());
        }
        if (countCache >= countRequests) {
            writeFile(cache.size());
            return;
        }
        for (String req : requests) {
            if (cache.get(req) == null) {
                counter++;
                if (!storage.get(req).isEmpty()) {
                    int valFromReq = storage.get(req).get(0);
                    if (storage.get(req).size() > 1) {
                        valFromReq = storage.get(req).get(1);
                    }
                    var iterator = cache.entrySet().iterator();
                    var iteratorNext = iterator.next();
                    var key = iteratorNext.getKey();
                    if (iteratorNext.getValue().isEmpty()) {
                        addToCache(key, req);
                        continue;
                    }
                    int valFromCache = iteratorNext.getValue().get(0);
                    boolean valIsEmpty = false;
                    if (flag) {
                        while (iterator.hasNext()) {
                            iteratorNext = iterator.next();
                            valIsEmpty = iteratorNext.getValue().isEmpty();
                            if (valIsEmpty) {
                                key = iteratorNext.getKey();
                                addToCache(key, req);
                                break;
                            }
                            int number = iteratorNext.getValue().get(0);
                            if (valFromCache < number) {
                                valFromCache = number;
                                key = iteratorNext.getKey();
                            }
                        }
                        flag = valIsEmpty;
                        if (valFromReq < valFromCache && !valIsEmpty) {
                            addToCache(key, req);
                        }
                    }
                }
            }
            if (!storage.get(req).isEmpty()) {
                storage.get(req).remove(0);
            }
        }
        writeFile(counter + cache.size());
    }

    public static void main(String[] args) {
        new Cache().start("input.txt");
    }
}