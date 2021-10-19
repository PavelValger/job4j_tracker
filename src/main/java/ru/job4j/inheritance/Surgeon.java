package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    public Surgeon(String name, String surname, String education, String birthday, String profile) {
        super(name, surname, education, birthday, profile);
    }

    public void liver() {
    }
}
