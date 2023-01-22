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
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        Employee worker = new Employee("Ivan", now, exit, 200);
        Employee specialist = new Employee("Igor", now, exit, 100);
        store.add(worker);
        store.add(specialist);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ProgrammersReport(store, parser);
        String expect = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n",
                "-----------------------------------------------------------",
                "Name      |Hired             |Fired             |Salary    ",
                "-----------------------------------------------------------",
                "Ivan      |25:02:2015 00:00  |15:05:2020 00:00  |200.0     ",
                "-----------------------------------------------------------",
                "Igor      |25:02:2015 00:00  |15:05:2020 00:00  |100.0     ",
                "-----------------------------------------------------------");
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}