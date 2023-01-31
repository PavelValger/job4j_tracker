package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "wrapper")
public class Wrapper {
    private List<Employee> employees;

    public Wrapper() {
    }

    public Wrapper(List<Employee> obj) {
        this.employees = obj;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
