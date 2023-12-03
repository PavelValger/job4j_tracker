package ru.job4j.tracker.input;

import ru.job4j.tracker.output.Output;

public class Validate implements Input {
    private final Output out;
    private final Input input;

    public Validate(Output out, Input input) {
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
                out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
