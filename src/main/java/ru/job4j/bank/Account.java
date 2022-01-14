package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель банковского счета.
 * @author PAVEL VALGER.
 * @version 1.0
 */
public class Account {
    /**
     * Хранение данных по реквизитам счета.
     */
    private String requisite;
    /**
     * Хранение данных по балансу счета.
     */
    private double balance;

    /**
     * Конструктор принимает на вход реквизиты и баланс счета, и записывает их в поля класса.
     * @param requisite значение реквизитов счета.
     * @param balance значение баланса счета.
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод для получения значения реквизитов счета.
     * @return возвращает значение реквизитов счета.
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод для установки значения реквизитов счета.
     * @param requisite изменяет реквизиты счета.
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод для получения значения баланса счета.
     * @return возвращает значение баланса счета.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод для установки значения баланса счета.
     * @param balance изменяет баланс счета.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
