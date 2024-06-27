package ru.job4j.tinkoff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int rsl = 0;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
               int number = Integer.parseInt(line);
               rsl += number;
            }
            System.out.println(rsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
