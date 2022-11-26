package ru.job4j.tracker;

public record CreateMuchAction(Output out) implements UserAction {
    private static final Integer VALUE = 100000;

    @Override
    public String name() {
        return "Add new much Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new much Item ====");
        int count = 1;
        while (count <= VALUE) {
            String name = String.valueOf(count);
            Item item = new Item(name);
            tracker.add(item);
            count++;
        }
        out.println("Заявки добавлены");
        return true;
    }
}
