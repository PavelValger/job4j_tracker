package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.util.Scanner;

public class Emulator {
    private static final int LOAD = 1;
    private static final int GET = 2;
    private static final String SELECT = "Выберите меню";
    private static final String EXIT = "Конец работы";
    private static final String NAME =
            "Введите относительный путь к файлу в директории, например - \\names.txt";
    private static final String MENU = """
                Введите 1 для загрузки содержимого файла в кэш.
                Введите 2 для получения содержимого файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите кэшируемую директорию");
        DirFileCache dirFileCache = new DirFileCache(scanner.nextLine());
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int action = Integer.parseInt(scanner.nextLine());
            if (LOAD == action) {
                System.out.println(NAME);
                dirFileCache.get(scanner.nextLine());
            } else if (GET == action) {
                System.out.println(NAME);
                System.out.println(dirFileCache.get(scanner.nextLine()));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
