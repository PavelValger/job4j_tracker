package yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Code {
    private static final String ALPHABET = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static void addToMap(Map<Character, Character> map, char[] chars) {
        for (char ch : chars) {
            map.put(ch, ch);
        }
    }

    private static int sumDigits(String num) {
        int number = Integer.parseInt(num);
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> stringsList = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.readLine();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                Map<Character, Character> map = new HashMap<>();
                var strings = line.split(",");
                var charArr1 = strings[0].toCharArray();
                addToMap(map, charArr1);
                var charArr2 = strings[1].toCharArray();
                addToMap(map, charArr2);
                var charArr3 = strings[2].toCharArray();
                addToMap(map, charArr3);
                int countSymbol = map.size();
                int sumHappyDay = sumDigits(strings[3]) + sumDigits(strings[4]);
                int num = ALPHABET.indexOf(strings[0].toUpperCase(Locale.ROOT).charAt(0));
                int sum = countSymbol + sumHappyDay * 64 + num * 256;
                String rsl = String.format("%03X", sum);
                if (rsl.length() > 3) {
                    rsl = rsl.substring(rsl.length() - 3);
                }
                stringsList.add(String.format("%s ", rsl));
            }
            stringsList.forEach(System.out::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
