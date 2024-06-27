package yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Matrix {

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            var num = in.readLine().split(" ");
            int[][] mtrx = new int[Integer.parseInt(num[1]) + 1][Integer.parseInt(num[0]) + 1];
            int count = 0;
            for (int i = 0; i < mtrx.length; i++) {
                for (int j = 0; j < mtrx[i].length; j++) {
                    mtrx[i][j] = count++;
                }
            }
            for (int i = 0; i < mtrx.length; i++) {
                for (int j = 0; j < mtrx[i].length; j++) {
                    System.out.print(mtrx[i][j] + " ");
                }
                System.out.println();
            }
            in.readLine();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
