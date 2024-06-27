package ru.job4j.kontur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String string = in.readLine();
            if (string.length() < 2) {
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(string.charAt(0));
            for (int i = 1; i < string.length(); i++) {
                if (string.charAt(i) == string.charAt(i - 1)) {
                    stringBuilder.append(string.charAt(i));
                    continue;
                }
                Integer count = map.putIfAbsent(stringBuilder.length(), 1);
                if (count != null) {
                    count++;
                    map.replace(stringBuilder.length(), count);
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(string.charAt(i));
            }
            Integer val = map.get(stringBuilder.length());
            if (val != null) {
                val++;
                map.replace(stringBuilder.length(), val);
            } else {
                map.put(stringBuilder.length(), 1);
            }
            Integer value = 0;
            for (var entry : map.entrySet()) {
                Integer key = entry.getKey();
                if (key > 1) {
                    value += entry.getValue();
                }
            }
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
