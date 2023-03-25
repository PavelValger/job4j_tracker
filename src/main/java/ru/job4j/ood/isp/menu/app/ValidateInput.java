package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.Output;

public class ValidateInput implements Input {
    private static final String SEPARATOR = System.lineSeparator();
    private final Output out;
    private final Input input;

    public ValidateInput(Output out, Input input) {
        this.out = out;
        this.input = input;
    }

    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                out.print(String.format(
                        "%s%s", "Please enter validate data again.", SEPARATOR));
            }
        } while (invalid);
        return value;
    }
}
