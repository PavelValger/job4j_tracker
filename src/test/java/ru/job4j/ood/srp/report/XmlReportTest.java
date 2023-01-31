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

class XmlReportTest {

    @Test
    public void whenOldGenerated() {
        Store<Employee> store = new MemStore<>();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        DateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
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
        Report<Employee> engine = new XmlReport(store);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <wrapper>
                    <employees name="Ivan" salary="100.0">
                        <hired>2015-02-25T00:00:00+05:00</hired>
                        <fired>2020-05-15T00:00:00+05:00</fired>
                    </employees>
                </wrapper>
                """;
        assertThat(engine.generate(obj -> true)).isEqualTo(expect);
    }
}