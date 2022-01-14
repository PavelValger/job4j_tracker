package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банка.
 * @author PAVEL VALGER.
 * @version 1.0
 */
public class User {
    /**
     * Хранение паспортных данных пользователя.
     */
    private String passport;
    /**
     * Хранение имени пользователя.
     */
    private String username;

    /**
     * Конструктор принимает на вход паспортные данные и имя пользователя,
     * и записывает их в поля класса.
     * @param passport значение паспортных данных пользователя.
     * @param username значение имени пользователя.
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод для получения значения паспортных данных пользователя.
     * @return возвращает значение паспортных данных пользователя.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Метод для установки значения паспортных данных пользователя.
     * @param passport изменяет паспортные данные пользователя.
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод для получения значения имени пользователя.
     * @return возвращает значение имени пользователя.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод для установки имени пользователя.
     * @param username изменяет имя пользователя.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
