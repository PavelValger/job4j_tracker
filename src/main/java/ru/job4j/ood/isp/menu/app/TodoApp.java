package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.*;

import java.util.List;

public class TodoApp {
    private static final String SEPARATOR = System.lineSeparator();
    private static final ActionDelegate ADD_DELEGATE =
            () -> System.out.println("Action for add point root");
    private static final ActionDelegate ADD_CHILD_DELEGATE =
            () -> System.out.println("Action for add point child");
    private final Output out;

    public TodoApp(Output out) {
        this.out = out;
    }

    private void showMenu(List<TodoAction> actions) {
        out.print(String.format("%s%s%s", SEPARATOR, "Menu:", SEPARATOR));
        for (int index = 0; index < actions.size(); index++) {
            out.print(index + ". " + actions.get(index).name() + SEPARATOR);
        }
    }

    public void init(Input input, Menu menu, List<TodoAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.print(String.format("%s%s%s",
                        "Wrong input, you can select: 0 .. ", actions.size() - 1, SEPARATOR));
                continue;
            }
            TodoAction action = actions.get(select);
            run = action.execute(input, menu);
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        Menu menu = new SimpleMenu();
        List<TodoAction> actions = List.of(
                new AddRootTodoAction(out, ADD_DELEGATE),
                new AddChildTodoAction(out, ADD_CHILD_DELEGATE),
                new StepTodoAction(out),
                new OutputTodoAction(out, new IndentionMenuPrinter(out)),
                new ExitTodoAction(out)
        );
        new TodoApp(out).init(input, menu, actions);
    }
}
