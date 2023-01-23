package ru.job4j.ood.ocp;

public class Dog {
    private String food;

    public void eat(String meal) {
        this.food = meal;
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat("bone");
        System.out.println(dog.food);
    }
}
