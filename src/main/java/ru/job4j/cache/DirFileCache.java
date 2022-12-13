package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    private void validation(Path path) {
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

    @Override
    protected String load(String key) {
        String rsl = null;
        var path = Path.of(cachingDir, key);
        validation(path);
        try {
            rsl = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
