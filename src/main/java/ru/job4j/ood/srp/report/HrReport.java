package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.BaseReport;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReport<T extends BaseReport> implements Report<T> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<T> store;
    private final Comparator<T> comparator;

    public HrReport(Store<T> store, Comparator<T> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<T> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Salary;%s", SEPARATOR));
        List<T> list = store.findBy(filter);
        list.sort(comparator);
        for (T obj : list) {
            text.append(String.format("%s %s%s",
                    obj.getName(),
                    obj.getSalary(),
                    SEPARATOR));
        }
        return text.toString();
    }
}
