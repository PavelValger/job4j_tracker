package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report<Employee> {
    private final Store<Employee> store;
    private final Marshaller marshaller;

    public XmlReport(Store<Employee> store) {
        this.store = store;
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Wrapper(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    @XmlRootElement(name = "wrapper")
    private static class Wrapper {
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
}
