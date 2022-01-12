package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int limit = Math.min(left.length(), right.length());
        for (int index = 0; index < limit; index++) {
            if (left.charAt(index) != right.charAt(index)) {
                rsl = Integer.compare(left.charAt(index), right.charAt(index));
                break;
            }
        }
        return rsl == 0 ? left.length() - right.length() : rsl;
    }
}
