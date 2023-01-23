package ru.job4j.ood.ocp;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFiles {
    public void read() {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
