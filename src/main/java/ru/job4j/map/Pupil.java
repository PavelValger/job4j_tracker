package ru.job4j.map;

import java.util.List;
import java.util.Objects;

public record Pupil(String name, List<Subject> subjects) {

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pupil pupil = (Pupil) o;
        return Objects.equals(name, pupil.name) && Objects.equals(subjects, pupil.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subjects);
    }

    @Override
    public String toString() {
        return "Pupil{"
                + "name='" + name + '\''
                + ", subjects=" + subjects
                + '}';
    }
}
