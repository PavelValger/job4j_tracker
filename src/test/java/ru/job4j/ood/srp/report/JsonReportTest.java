package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class JsonReportTest {

    @Test
    public void whenJSONReport() {
        Store<Employee> store = new MemStore<>();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        DateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        try {
            Date dateNow = formatter.parse(parser.parse(now));
            Date dateExit = formatter.parse(parser.parse(exit));
            now.setTime(dateNow);
            exit.setTime(dateExit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Employee worker = new Employee("Ivan", now, exit, 100);
        store.add(worker);
        Report<Employee> engine = new JsonReport(store);
        String expect = "[{"
                + "\"name\":\"Ivan\","
                + "\"hired\":"
                + "{"
                + "\"year\":2015,"
                + "\"month\":1,"
                + "\"dayOfMonth\":25,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0"
                + "},"
                + "\"fired\":"
                + "{"
                + "\"year\":2020,"
                + "\"month\":4,"
                + "\"dayOfMonth\":15,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0"
                + "},"
                + "\"salary\":100.0"
                + "}]";
        assertThat(engine.generate(obj -> true)).isEqualTo(expect);
    }
}
