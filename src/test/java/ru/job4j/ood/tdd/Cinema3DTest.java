package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class Cinema3DTest {
    /**
     * Тест сравнивает купленный билет через кинотеатр с созданным билетом
     */
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    /**
     * Тест проверяет добавление фильма в список кинотеатра
     */
    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    /**
     * Тест выдает исключение при покупке недопустимого места по row
     */
    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест выдаёт null в случае ненайденного сеанса
     */
    @Test
    public void whenSessionNotAddedThenNull() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(ses -> false);
        assertThat(sessions).isNull();
    }

    /**
     * Тест выдает исключение при покупке недопустимого места по column
     */
    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, -1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест выдает исключение при покупке недопустимого места по row более 10
     */
    @Test
    public void whenBuyOnInvalidRowOver10ThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 11, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест выдает исключение при покупке недопустимого места по column более 10
     */
    @Test
    public void whenBuyOnInvalidColumnOver10ThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, 11, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест выдает исключение при попытке покупки занятого места
     */
    @Test
    public void whenBuyThePlaceIsOccupiedThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(new AccountCinema(), 1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }
}