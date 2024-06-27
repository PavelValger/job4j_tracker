package yandex;

import java.io.*;

public class ConsoleReader {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] strings = line.split(" ");
            int x1 = Integer.parseInt(strings[0]);
            int x2 = Integer.parseInt(strings[1]);
            System.out.println(x1 + x2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class WooHoo {
        public static void main(String[] args) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String J = reader.readLine();
            String S = reader.readLine();

            int result = 0;
            for (int i = 0; i < S.length(); ++i) {
                char ch = S.charAt(i);
                if (J.indexOf(ch) >= 0) {
                    ++result;
                }
            }
            System.out.println(result);
        }
    }
}
