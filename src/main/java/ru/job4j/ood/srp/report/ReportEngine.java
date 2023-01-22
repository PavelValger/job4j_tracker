package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Hired; Fired; Salary;%n"));
        for (Employee employee : store.findBy(filter)) {
            text.append(String.format("%s %s %s %s%n",
                    employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    employee.getSalary()));
        }
        return text.toString();
    }
}
