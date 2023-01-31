package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine implements Report<Employee> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<Employee> store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportEngine(Store<Employee> store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Hired; Fired; Salary;%s", SEPARATOR));
        for (Employee employee : store.findBy(filter)) {
            text.append(String.format("%s %s %s %s%s",
                    employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    employee.getSalary(),
                    SEPARATOR));
        }
        return text.toString();
    }
}
