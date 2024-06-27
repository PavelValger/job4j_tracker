package yandex;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Calculator {
    private static final Charset CODING = StandardCharsets.UTF_8;

    private int read() {
        int rsl = 0;
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            var first = in.readLine();
            var two = in.readLine();
            for (int i = 0; i < two.length(); ++i) {
                char ch = two.charAt(i);
                if (first.indexOf(ch) >= 0) {
                    rsl++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void record(int number) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter("output.txt", CODING, false)))) {
            out.println(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.record(calculator.read());
    }
}
