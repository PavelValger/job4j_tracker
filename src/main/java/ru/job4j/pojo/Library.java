package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book garry = new Book("Harry Potter", 400);
        Book game = new Book("Game of thrones", 450);
        Book hobbit = new Book("Hobbit", 350);
        Book code = new Book("Clean code", 200);
        Book[] shelf = new Book[4];
        shelf[0] = garry;
        shelf[1] = game;
        shelf[2] = hobbit;
        shelf[3] = code;
        for (Book sh : shelf) {
            System.out.println(sh.getName() + " - " + sh.getPages());
        }
        System.out.println("Swap places");
        Book temporary = shelf[0];
        shelf[0] = shelf[3];
        shelf[3] = temporary;
        for (Book sh : shelf) {
            System.out.println(sh.getName() + " - " + sh.getPages());
        }
        System.out.println("Book search");
        for (Book sh : shelf) {
            if (sh.getName().equals("Clean code")) {
                System.out.println(sh.getName() + " - " + sh.getPages());
            }
        }
    }
}
