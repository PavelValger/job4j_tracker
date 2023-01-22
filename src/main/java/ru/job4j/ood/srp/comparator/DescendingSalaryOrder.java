package ru.job4j.ood.srp.comparator;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class DescendingSalaryOrder implements Comparator<Employee> {
    @Override
    public int compare(Employee first, Employee second) {
        return Double.compare(second.getSalary(), first.getSalary());
    }
}
