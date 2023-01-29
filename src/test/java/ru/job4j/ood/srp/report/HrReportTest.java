package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.comparator.DescendingSalaryOrder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class HrReportTest {

    @Test
    void whenHrReport() {
        MemStore<Employee> store = new MemStore<>();
        Calendar now = new GregorianCalendar(2015, Calendar.FEBRUARY, 25);
        Calendar exit = new GregorianCalendar(2020, Calendar.MAY, 15);
        Employee worker = new Employee("Ivan", now, exit, 200);
        Employee specialist = new Employee("Igor", now, exit, 100);
        Employee expert = new Employee("Roman", now, exit, 150);
        store.add(worker);
        store.add(specialist);
        store.add(expert);
        Report<Employee> engine = new HrReport<>(store, new DescendingSalaryOrder());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .append(expert.getName()).append(" ")
                .append(expert.getSalary())
                .append(System.lineSeparator())
                .append(specialist.getName()).append(" ")
                .append(specialist.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}