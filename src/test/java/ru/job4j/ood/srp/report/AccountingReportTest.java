package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class AccountingReportTest {

    @Test
    void whenAccountingReport() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        Employee worker = new Employee("Ivan", now, exit, 100);
        Employee specialist = new Employee("Igor", now, exit, 200);
        store.add(worker);
        store.add(specialist);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report engine = new AccountingReport(store, parser, converter, Currency.RUB, Currency.USD);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(specialist.getName()).append(" ")
                .append(parser.parse(specialist.getHired())).append(" ")
                .append(parser.parse(specialist.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, specialist.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}