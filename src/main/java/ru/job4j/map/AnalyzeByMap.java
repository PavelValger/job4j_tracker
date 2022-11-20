package ru.job4j.map;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzeByMap {
    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0D);
    }

    public static List<Label> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.map(pupil -> new Label(
                        pupil.getName(),
                        pupil.getSubjects().stream()
                                .mapToInt(Subject::getScore)
                                .average()
                                .orElse(0D)))
                .collect(Collectors.toList());
    }

    public static List<Label> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::getScore)))
                .entrySet().stream()
                .map(k -> new Label(k.getKey(), k.getValue()))
                .collect(Collectors.toList());
    }

    public static Label bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Label(
                        pupil.getName(),
                        pupil.getSubjects().stream()
                                .mapToInt(Subject::getScore)
                                .sum()))
                .max(Comparator.comparingDouble(Label::score))
                .orElse(null);
    }

    public static Label bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.summingDouble(Subject::getScore)))
                .entrySet().stream()
                .map(k -> new Label(k.getKey(), k.getValue()))
                .max(Comparator.comparingDouble(Label::score))
                .orElse(null);
    }
}
