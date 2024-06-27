package ru.job4j.naumen;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    void whenInvalidFolderThenException() {
        assertThatThrownBy(() -> new Cache().start(""))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenInvalidInputThenException() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("5" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "3");
        }
        assertThatThrownBy(() -> new Cache().start(input.toPath().toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenInvalidInputNumbersCacheThenException() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("-5 5" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "3");
        }
        assertThatThrownBy(() -> new Cache().start(input.toPath().toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenInvalidInputNumbersCache120ThenException() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("120000 5" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "3");
        }
        assertThatThrownBy(() -> new Cache().start(input.toPath().toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenInvalidInputNumbersRequestThenException() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 -5" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "3");
        }
        assertThatThrownBy(() -> new Cache().start(input.toPath().toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenInvalidInputNumbersRequest110ThenException() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 110000" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "3");
        }
        assertThatThrownBy(() -> new Cache().start(input.toPath().toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenInvalidInputNumbersStringsRequestThenException() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 10" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "3");
        }
        assertThatThrownBy(() -> new Cache().start(input.toPath().toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenClassicInputThen9() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("5 15" + ln +
                    "3" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "5" + ln +
                    "9" + ln +
                    "2" + ln +
                    "6" + ln +
                    "5" + ln +
                    "3" + ln +
                    "5" + ln +
                    "8" + ln +
                    "7" + ln +
                    "9" + ln +
                    "3");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("9");
    }

    @Test
    void whenInputThen8() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 17" + ln +
                    "5" + ln +
                    "55" + ln +
                    "4" + ln +
                    "3" + ln +
                    "7" + ln +
                    "2" + ln +
                    "1" + ln +
                    "7" + ln +
                    "7" + ln +
                    "7" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "4" + ln +
                    "3");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("8");
    }

    @Test
    void whenMinInputThen1() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("1 1" + ln +
                    "574645645645645645645645645645645645645645564564563452534534457454");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("1");
    }

    @Test
    void whenCacheMoreRequestThenCacheSize() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("8 5" + ln +
                    "5" + ln +
                    "55" + ln +
                    "4" + ln +
                    "3" + ln +
                    "5");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("4");
    }

    @Test
    void whenIdenticalRequestThen1() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 7" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("1");
    }

    @Test
    void whenDifferentRequestThen7() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 7" + ln +
                    "1" + ln +
                    "2" + ln +
                    "3" + ln +
                    "4" + ln +
                    "5" + ln +
                    "6" + ln +
                    "7");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("7");
    }

    @Test
    void whenInputThen6() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 17" + ln +
                    "3" + ln +
                    "2" + ln +
                    "1" + ln +
                    "4" + ln +
                    "4" + ln +
                    "4" + ln +
                    "1" + ln +
                    "1" + ln +
                    "1" + ln +
                    "100" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "5" + ln +
                    "4" + ln +
                    "3");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("6");
    }

    @Test
    void whenInputThen5() throws IOException {
        folder.create();
        File input = folder.newFile("inputTest.txt");
        var ln = System.lineSeparator();
        try (PrintWriter in = new PrintWriter(input)) {
            in.println("3 10" + ln +
                    "3" + ln +
                    "2" + ln +
                    "1" + ln +
                    "4" + ln +
                    "3" + ln +
                    "2" + ln +
                    "1" + ln +
                    "4" + ln +
                    "1" + ln +
                    "1");
        }
        new Cache().start(input.toPath().toString());
        String result = Files.readString(Path.of("output.txt"));
        assertThat(result).isEqualTo("5");
    }
}