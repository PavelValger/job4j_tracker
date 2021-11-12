package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int z) {
        return Math.abs(z - x);
    }

    public int divide(int n) {
        return n / x;
    }

    public int sumAllOperation(int s) {
        return sum(s) + multiply(s) + minus(s) + divide(s);
    }

    public static void main(String[] args) {
        System.out.println("Результат сложения: " + sum(5));
        Calculator calculator = new Calculator();
        System.out.println("Результат умножения: " + calculator.multiply(5));
        System.out.println("Результат вычитания: " + minus(5));
        System.out.println("Результат деления: " + calculator.divide(5));
        System.out.println("Результат сложения всех операций: "
                + calculator.sumAllOperation(5));
    }
}
