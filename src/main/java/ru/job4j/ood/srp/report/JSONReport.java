package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class JSONReport<T> implements Report<T> {
    private final Store<T> store;
    private final Gson gson;

    public JSONReport(Store<T> store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<T> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
