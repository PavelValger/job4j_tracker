package ru.job4j.kontur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        List<String> stringsList = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            in.readLine();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }
            stringsList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
