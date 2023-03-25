package ru.job4j.ood.isp.menu;

public class StubOutput implements Output {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public void print(Object obj) {
        if (obj == null) {
            obj = "null";
        }
        builder.append(obj);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
