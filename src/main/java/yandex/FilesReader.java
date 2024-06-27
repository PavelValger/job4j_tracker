package yandex;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FilesReader {
    private static final Charset CODING = StandardCharsets.UTF_8;

    private int read() {
        int rsl = 0;
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {

            in.lines().forEach(System.out::println);

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }

            String[] split = in.readLine().split(" ");
            int x1 = Integer.parseInt(split[0]);
            int x2 = Integer.parseInt(split[1]);

            for (String s : split) {
                rsl += Integer.parseInt(s);
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

        try (BufferedWriter out = new BufferedWriter(new FileWriter("output.txt", false))) {
            out.write(number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        FilesReader filesReader = new FilesReader();
        filesReader.record(filesReader.read());
    }

    private int read2() {
        int rsl = 0;
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            String[] split = in.readLine().split(" ");
            rsl = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void record2(int number) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter("output.txt", CODING, false)))) {
            out.println(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
