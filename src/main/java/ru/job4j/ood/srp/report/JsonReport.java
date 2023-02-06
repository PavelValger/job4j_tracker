package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarAdapterJson;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReport implements Report<Employee> {
    private final Store<Employee> store;
    private final  GsonBuilder builder;

    public JsonReport(Store<Employee> store) {
        this.store = store;
        this.builder = new GsonBuilder();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        builder.registerTypeAdapter(Calendar.class, new CalendarAdapterJson());
        builder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapterJson());
        Gson gson = builder.create();
        return gson.toJson(store.findBy(filter));
    }
}
