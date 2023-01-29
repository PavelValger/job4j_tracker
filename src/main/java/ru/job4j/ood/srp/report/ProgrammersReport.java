package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.BaseReport;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ProgrammersReport<T extends BaseReport> implements Report<T> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<T> store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimiter;

    public ProgrammersReport(Store<T> store,
                             DateTimeParser<Calendar> dateTimeParser, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<T> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name%sHired%sFired%sSalary%s",
                delimiter,
                delimiter,
                delimiter,
                SEPARATOR));
        for (T obj : store.findBy(filter)) {
            text.append(String.format("%s%s%s%s%s%s%s%s",
                    obj.getName(),
                    delimiter,
                    dateTimeParser.parse(obj.getHired()),
                    delimiter,
                    dateTimeParser.parse(obj.getFired()),
                    delimiter,
                    obj.getSalary(),
                    SEPARATOR));
        }
        return text.toString();
    }
}