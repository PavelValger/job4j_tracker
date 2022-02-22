package ru.job4j.bank;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankServiceTest {

    @Test
    public void whenAddUser() {
        var user = new User("3434", "Petr Arsentev");
        var bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434"), is(Optional.of(user)));
    }

    @Test
    public void whenRemoveUser() {
        var user = new User("3434", "Petr Arsentev");
        var bank = new BankService();
        bank.addUser(user);
        bank.removeUser(user);
        assertThat(bank.findByPassport("3434"), is(Optional.empty()));
    }

    @Test
    public void whenEnterInvalidPassport() {
        var user = new User("3434", "Petr Arsentev");
        var bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.findByRequisite("34", "5546"), is(Optional.empty()));
    }

    @Test
    public void whenAdd2Account() {
        var user = new User("3434", "Petr Arsentev");
        var bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("7777", 70D));
        assertThat(bank.findByRequisite("3434", "5546").get().getBalance(), is(150D));
        assertThat(bank.findByRequisite("3434", "7777").get().getBalance(), is(70D));
    }

    @Test
    public void whenTransferMoney() {
        var user = new User("3434", "Petr Arsentev");
        var bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "113").get().getBalance(), is(200D));
        assertThat(bank.findByRequisite(user.getPassport(), "5546").get().getBalance(), is(0D));
    }

    @Test
    public void whenTransferMoneyThenTransferFalse() {
        var user = new User("3434", "Petr Arsentev");
        var bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        assertFalse(bank.transferMoney(user.getPassport(), "5546",
                user.getPassport(), "113", 500D));
        assertFalse(bank.transferMoney(user.getPassport(), "55",
                user.getPassport(), "113", 10D));
        assertFalse(bank.transferMoney(user.getPassport(), "5546",
                user.getPassport(), "114", 20D));
    }
}