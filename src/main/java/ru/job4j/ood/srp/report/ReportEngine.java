package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.BaseReport;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine<T extends BaseReport> implements Report<T> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<T> store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportEngine(Store<T> store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<T> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Hired; Fired; Salary;%s", SEPARATOR));
        for (T obj : store.findBy(filter)) {
            text.append(String.format("%s %s %s %s%s",
                    obj.getName(),
                    dateTimeParser.parse(obj.getHired()),
                    dateTimeParser.parse(obj.getFired()),
                    obj.getSalary(),
                    SEPARATOR));
        }
        return text.toString();
    }
}
