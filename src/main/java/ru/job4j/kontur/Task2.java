package ru.job4j.kontur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            var friendsAndRolls = in.readLine().split(" ");
            long rolls = Long.parseLong(friendsAndRolls[1]);
            var appetites = in.readLine().split(" ");
            long pleased = 0;
            for (var appetite : appetites) {
                pleased += Long.parseLong(appetite);
            }
            var maxAppetites = in.readLine().split(" ");
            long maxPleased = 0;
            for (var maxAppetite : maxAppetites) {
                maxPleased += Long.parseLong(maxAppetite);
            }
            if (pleased <= rolls && maxPleased >= rolls) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
