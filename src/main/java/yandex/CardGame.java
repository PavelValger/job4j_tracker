package yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardGame {

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.readLine();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                var numbers = line.split(" ");
                long first = Long.parseLong(numbers[0]);
                long second = Long.parseLong(numbers[1]);
                long third = Long.parseLong(numbers[2]);
                if (first % 2 != 0 && second % 2 != 0 && third % 2 != 0) {
                    System.out.println("No");
                    continue;
                }
                if (first % 2 == 0 && second % 2 == 0 && third % 2 == 0) {
                    System.out.println("No");
                    continue;
                }
                System.out.println("Yes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//2
//1 1 1
//1 1 2
//1 2 2
//2 2 2