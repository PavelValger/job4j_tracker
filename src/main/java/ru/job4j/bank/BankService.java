package ru.job4j.bank;

import java.util.*;

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
        var user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> list = users.get(user.get());
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
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(t -> t.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод ищет счет пользователя по реквизитам. Поиск пользователя ведется по паспорту.
     * @param passport паспортные данные искомого пользователя.
     * @param requisite реквизиты искомого счета.
     * @return возвращает банковский счет или null, если счет не найден.
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        var user = findByPassport(passport);
        return user.flatMap(value -> users.get(value)
                .stream()
                .filter(t -> t.getRequisite().equals(requisite))
                .findFirst());
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
        var source = findByRequisite(srcPassport, srcRequisite);
        var dest = findByRequisite(destPassport, destRequisite);
        if (source.isPresent() && dest.isPresent() && source.get().getBalance() >= amount) {
            dest.get().setBalance(amount + dest.get().getBalance());
            source.get().setBalance(source.get().getBalance() - amount);
            rsl = true;
        }
        return rsl;
    }
}
