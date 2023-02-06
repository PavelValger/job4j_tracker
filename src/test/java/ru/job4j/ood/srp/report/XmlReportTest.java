package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {

    @Test
    public void whenOldGenerated() {
        Store<Employee> store = new MemStore<>();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        Employee worker = new Employee("Ivan", now, exit, 100);
        store.add(worker);
        Report<Employee> engine = new XmlReport(store);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <wrapper>
                    <employees name="Ivan" salary="100.0">
                        <hired>25:02:2015 00:00</hired>
                        <fired>15:05:2020 00:00</fired>
                    </employees>
                </wrapper>
                """;
        assertThat(engine.generate(obj -> true)).isEqualTo(expect);
    }
}