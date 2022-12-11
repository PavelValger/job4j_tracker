package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {
    private static final Charset CODING = Charset.forName("UTF-8");
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    private void validation(String string) {
        Path path = Paths.get(string);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", path));
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(
                    String.format("Not file %s", path));
        }
        if (!path.toFile().getName().endsWith(".txt")) {
            throw new IllegalArgumentException(
                    "The file extension must end with \".txt\"");
        }
    }

    private List<String> read(String key) {
        String string = String.format("%s%s", cachingDir, key);
        validation(string);
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(string, CODING))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (!line.isEmpty()) {
                    rsl.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public String load(String key) {
        var rsl = String.join(", ", read(key));
        System.out.println("Загрузка в кэш прошла успешно");
        return rsl;
    }
}
