package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу системы банковских переводов, в частности:
 * 1. Регистрация пользователя {@link User};
 * 2. Удаление пользователя из системы;
 * 3. Открытие пользователю банковского счета {@link Account}. У пользователя может
 * быть несколько счетов;
 * 4. Перевод денег с одного банковского счета на другой счет.
 * @author PAVEL VALGER
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных всех пользователей системы с привязанными к ним счетами
     * осуществляется в коллекции типа HashMap ключ - значение.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя в систему с проверкой по паспорту {@link User#getPassport()}.
     * @param user пользователь, который регистрируется в системе.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя из системы.
     * @param user пользователь, которого удаляем.
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Метод добавляет новый счет к пользователю с проверкой наличия дубликата счета по реквизитам
     * счета {@link Account#getRequisite()}. Поиск пользователя ведется по паспорту.
     * @param passport паспортные данные искомого пользователя.
     * @param account новый счет пользователя.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            if (!list.contains(account)) {
                list.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по паспорту.
     * @param passport паспортные данные искомого пользователя.
     * @return возвращает пользователя или null, если пользователь не найден.
     */
    public User findByPassport(String passport) {
        User rsl = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод ищет счет пользователя по реквизитам. Поиск пользователя ведется по паспорту.
     * @param passport паспортные данные искомого пользователя.
     * @param requisite реквизиты искомого счета.
     * @return возвращает банковский счет или null, если счет не найден.
     */
    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            for (Account account : list) {
                if (requisite.equals(account.getRequisite())) {
                    rsl = account;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт.
     * @param srcPassport паспортные данные исходящего счета.
     * @param srcRequisite реквизиты исходящего счета.
     * @param destPassport паспортные данные входящего счета.
     * @param destRequisite реквизиты входящего счета.
     * @param amount сумма перевода.
     * @return возвращает true, если перевод выполнен успешно или false, если:
     * - счет пользователя не найден;
     * - недостаточно средств на исходящем счете.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account source = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (source != null && dest != null && source.getBalance() >= amount) {
            dest.setBalance(amount + dest.getBalance());
            source.setBalance(source.getBalance() - amount);
            rsl = true;
        }
        return rsl;
    }
}
