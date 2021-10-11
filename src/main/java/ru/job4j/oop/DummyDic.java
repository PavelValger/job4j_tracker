package ru.job4j.oop;

public class DummyDic {
    public String engToRus() {
        return "apple";
    }

    public static void main(String[] args) {
        DummyDic translation = new DummyDic();
        System.out.println("яблоко " + translation.engToRus());
    }
}
