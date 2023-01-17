package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class NameGeneratorTest {
    private final Generator nameGenerator = new NameGenerator();

    /**
     * Тест проверяет работу генератора
     */
    @Test
    void checkGenerator() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        assertThat(nameGenerator.produce(template, args))
                .isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    /**
     * Тест кидает исключение, если шаблон null
     */
    @Test
    void whenTemplateIsNullThenException() {
        String template = null;
        Map<String, String> args = new HashMap<>();
        assertThatThrownBy(() -> nameGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    /**
     * Тест кидает исключение, если карта null
     */
    @Test
    void whenMapIsNullThenException() {
        String template = "";
        Map<String, String> args = null;
        assertThatThrownBy(() -> nameGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    /**
     * Тест кидает исключение, если ключ не найден
     */
    @Test
    void whenKeyNotFoundThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("names", "Petr Arsentev");
        args.put("subject", "you");
        assertThatThrownBy(() -> nameGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    /**
     * Тест кидает исключение, если размер карты args больше двух.
     */
    @Test
    void whenSizeMapOverTwoThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("subjects", "you");
        assertThatThrownBy(() -> nameGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}