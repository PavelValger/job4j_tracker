package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport<T extends Person> implements Report<T> {
    private final Store<T> store;
    private final Marshaller marshaller;

    public XMLReport(Store<T> store) {
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
    public String generate(Predicate<T> filter) {
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Wrapper<>(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}
