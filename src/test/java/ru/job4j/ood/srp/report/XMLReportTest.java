package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Contact;
import ru.job4j.ood.srp.model.Person;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {

    @Test
    public void whenOldGenerated() {
        Store<Person> store = new MemStore<>();
        Person person = new Person(false, 30,
                new Contact("11-111"), "Worker", "Married");
        store.add(person);
        Report<Person> engine = new XMLReport<>(store);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <wrapper>
                    <obj sex="false" age="30">
                        <contact phone="11-111"/>
                        <statuses>
                            <status>Worker</status>
                            <status>Married</status>
                        </statuses>
                    </obj>
                </wrapper>
                """;
        assertThat(engine.generate(obj -> true)).isEqualTo(expect);
    }
}