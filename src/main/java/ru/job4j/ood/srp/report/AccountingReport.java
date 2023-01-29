package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.BaseReport;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport<T extends BaseReport> implements Report<T> {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store<T> store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;
    private final Currency source;
    private final Currency target;

    public AccountingReport(Store<T> store, DateTimeParser<Calendar> dateTimeParser,
                            CurrencyConverter converter, Currency source, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.source = source;
        this.target = target;
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
                    converter.convert(source, obj.getSalary(), target),
                    SEPARATOR));
        }
        return text.toString();
    }
}
