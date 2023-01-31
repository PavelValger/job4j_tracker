package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ProgrammersReport implements Report<Employee> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<Employee> store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimiter;

    public ProgrammersReport(Store<Employee> store,
                             DateTimeParser<Calendar> dateTimeParser, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name%sHired%sFired%sSalary%s",
                delimiter,
                delimiter,
                delimiter,
                SEPARATOR));
        for (Employee employee : store.findBy(filter)) {
            text.append(String.format("%s%s%s%s%s%s%s%s",
                    employee.getName(),
                    delimiter,
                    dateTimeParser.parse(employee.getHired()),
                    delimiter,
                    dateTimeParser.parse(employee.getFired()),
                    delimiter,
                    employee.getSalary(),
                    SEPARATOR));
        }
        return text.toString();
    }
}