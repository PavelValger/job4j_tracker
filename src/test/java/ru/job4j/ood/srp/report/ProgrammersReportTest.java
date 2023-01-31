package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class ProgrammersReportTest {

    @Test
    void whenProgrammersReport() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        Employee worker = new Employee("Ivan", now, exit, 200);
        Employee specialist = new Employee("Igor", now, exit, 100);
        store.add(worker);
        store.add(specialist);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String delimiter = ",";
        Report<Employee> engine = new ProgrammersReport(store, parser, delimiter);
        StringBuilder expect = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(delimiter)
                .append(parser.parse(worker.getHired())).append(delimiter)
                .append(parser.parse(worker.getFired())).append(delimiter)
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .append(specialist.getName()).append(delimiter)
                .append(parser.parse(specialist.getHired())).append(delimiter)
                .append(parser.parse(specialist.getFired())).append(delimiter)
                .append(specialist.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}