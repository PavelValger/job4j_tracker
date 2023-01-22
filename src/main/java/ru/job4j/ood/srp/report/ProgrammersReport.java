package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class ProgrammersReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ProgrammersReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var rowSeparator = "-".repeat(59).concat(System.lineSeparator());
        var header = String.format("%-10s|%-18s|%-18s|%-10s%n", "Name", "Hired", "Fired", "Salary");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        for (Employee employee : store.findBy(filter)) {
            buffer.add(String.format("%-10s|%-18s|%-18s|%-10s%n",
                    employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    employee.getSalary()));
        }
        return buffer.toString();
    }
}
