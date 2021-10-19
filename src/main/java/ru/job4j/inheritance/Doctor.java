package ru.job4j.inheritance;

public class Doctor extends Profession {
    private String profile;

    public Doctor(String name, String surname, String education,
                    String birthday, String profile) {
        super(name, surname, education, birthday);
        this.profile = profile;
    }

    public String history(String diseases) {
        return null;
    }
}
