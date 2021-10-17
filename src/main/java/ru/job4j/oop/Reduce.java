package ru.job4j.oop;

public class Reduce {
    private int[] array;

    public void to(int[] array) {
        this.array = array;
    }

    public void print() {
        for (int index : array) {
            System.out.println(index);
        }
    }

    public static void main(String[] args) {
        Reduce reduce = new Reduce();
        reduce.to(new int[]{1, 2, 3});
        reduce.print();
    }
}
