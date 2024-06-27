package ru.job4j.tinkoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            in.readLine();
            int rsl = 0;
            int countSeven = 0;
            int countFive = 0;
            var numbers = in.readLine().split(" ");
            for (int i = 0; i < numbers.length; i++) {
                int numb = Integer.parseInt(numbers[i]);
                if (numb > 3 && countSeven < 7) {
                    countSeven++;
                    if (numb == 5) {
                        countFive++;
                    }
                } else {
                    countSeven = 0;
                    countFive = 0;
                }
                if (countSeven == 7) {
                    rsl = Math.max(rsl, countFive);
                    i = i - 6;
                    countSeven = 0;
                    countFive = 0;
                }
            }
            if (rsl == 0) {
                rsl = -1;
            }
            System.out.println(rsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
