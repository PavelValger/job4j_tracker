package ru.job4j.search;

import org.junit.Test;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk"));
        ArrayList<Person> persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
        ArrayList<Person> surname = phones.find("Ars");
        assertThat(surname.get(0).getName(), is("Petr"));
        ArrayList<Person> phone = phones.find("87");
        assertThat(phone.get(0).getName(), is("Petr"));
        ArrayList<Person> address = phones.find("yan");
        assertThat(address.get(0).getName(), is("Petr"));
    }
}