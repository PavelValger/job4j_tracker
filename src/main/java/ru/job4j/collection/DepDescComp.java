package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String first, String second) {
        String[] arrayFirst = first.split("/");
        String[] arraySecond = second.split("/");
        int rsl = arraySecond[0].compareTo(arrayFirst[0]);
        if (rsl != 0) {
            return rsl;
        }
        return first.compareTo(second);
    }
}
