package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Auto;
import ru.job4j.ood.srp.model.Number;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.*;

class JSONReportTest {

    @Test
    public void whenJSONReport() {
        Store<Auto> store = new MemStore<>();
        Auto auto = new Auto(true, 2, "red",
                new Number("x103cx"), "Transmission", "Drive");
        store.add(auto);
        Report<Auto> engine = new JSONReport<>(store);
        String expect = "[{"
                + "\"move\":true,"
                + "\"age\":2,"
                + "\"colour\":\"red\","
                + "\"number\":"
                + "{"
                + "\"state\":\"x103cx\""
                + "},\"characteristic\":"
                + "[\"Transmission\",\"Drive\"]"
                + "}]";
        assertThat(engine.generate(obj -> true)).isEqualTo(expect);
    }
}