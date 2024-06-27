package yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Rocket {

    public static void main(String[] args) {
        List<String> finish = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.readLine();
            Map<Integer, List<Integer>> map = new TreeMap<>();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                var strings = line.split(" ");
                int id = Integer.parseInt(strings[3]);
                int counter = (Integer.parseInt(strings[0]) * 24
                        + Integer.parseInt(strings[1])) * 60 + Integer.parseInt(strings[2]);
                if (!strings[4].equals("B")) {
                    map.computeIfAbsent(id, k -> new ArrayList<>()).add(counter);
                }
            }
            int sum = 0;
            for (List<Integer> value : map.values()) {
                value.sort(Comparator.reverseOrder());
                for (int i = 0; i < value.size() - 1; i++) {
                    sum += value.get(i) - value.get(i + 1);
                    i++;
                }
                finish.add(String.format("%s ", sum));
                sum = 0;
            }
            finish.forEach(System.out::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
