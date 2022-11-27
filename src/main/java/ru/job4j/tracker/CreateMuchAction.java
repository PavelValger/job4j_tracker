package ru.job4j.tracker;

public class CreateMuchAction implements UserAction {
    private final Output out;

    public CreateMuchAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new much Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new much Item ====");
        int count = Integer.parseInt(input.askStr("Enter count: "));
        for (int i = 1; i <= count; i++) {
            String name = String.valueOf(i);
            Item item = new Item(name);
            tracker.add(item);
        }
        out.println("Заявки добавлены");
        return true;
    }
}
