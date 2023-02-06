package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class JsonReportTest {

    @Test
    public void whenJSONReport() {
        Store<Employee> store = new MemStore<>();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        Employee worker = new Employee("Ivan", now, exit, 100d);
        store.add(worker);
        Report<Employee> engine = new JsonReport(store);
        String expect =
                "[{"
                        + "\"name\":\"Ivan\","
                        + "\"hired\":\"25:02:2015 00:00\","
                        + "\"fired\":\"15:05:2020 00:00\","
                        + "\"salary\":100.0"
                        + "}]";
        assertThat(engine.generate(obj -> true)).isEqualTo(expect);
    }
}
