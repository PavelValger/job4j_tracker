package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReport implements Report<Employee> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<Employee> store;
    private final Comparator<Employee> comparator;

    public HrReport(Store<Employee> store, Comparator<Employee> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Salary;%s", SEPARATOR));
        List<Employee> list = store.findBy(filter);
        list.sort(comparator);
        for (Employee employee : list) {
            text.append(String.format("%s %s%s",
                    employee.getName(),
                    employee.getSalary(),
                    SEPARATOR));
        }
        return text.toString();
    }
}
