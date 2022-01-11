package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        List<Character> one = new ArrayList<>();
        List<Character> two = new ArrayList<>();
        for (int index = 0; index < left.length(); index++) {
            one.add(left.charAt(index));
        }
        for (int index = 0; index < right.length(); index++) {
            two.add(right.charAt(index));
        }
        int limit = Math.min(one.size(), two.size());
        for (int index = 0; index < limit; index++) {
            if (one.get(index) != two.get(index)) {
                rsl = Character.compare(one.get(index), two.get(index));
                break;
            }
        }
        if (rsl == 0) {
            rsl = one.size() - two.size();
        }
        return rsl;
    }
}
