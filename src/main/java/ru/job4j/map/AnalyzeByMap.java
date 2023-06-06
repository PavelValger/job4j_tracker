package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double score = 0D;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.getSubjects()) {
                count++;
                score += subject.getScore();
            }
        }
        return count != 0 ? score / count : score;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0D;
            for (Subject subject : pupil.getSubjects()) {
                score += subject.getScore();
            }
            if (!pupil.getSubjects().isEmpty()) {
                rsl.add(new Label(pupil.getName(), score / pupil.getSubjects().size()));
            }
        }
        return rsl;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.getSubjects()) {
                map.merge(subject.getName(), subject.getScore(), Integer::sum);
            }
        }
        for (var entry : map.entrySet()) {
            if (!pupils.isEmpty()) {
                rsl.add(new Label(entry.getKey(), (double) entry.getValue() / pupils.size()));
            }
        }
        return rsl;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0D;
            for (Subject subject : pupil.getSubjects()) {
                score += subject.getScore();
            }
            rsl.add(new Label(pupil.getName(), score));
        }
        rsl.sort(Comparator.naturalOrder());
        return !rsl.isEmpty() ? rsl.get(rsl.size() - 1) : null;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.getSubjects()) {
                map.merge(subject.getName(), subject.getScore(), Integer::sum);
            }
        }
        for (var entry : map.entrySet()) {
            rsl.add(new Label(entry.getKey(), (double) entry.getValue()));
        }
        rsl.sort(Comparator.naturalOrder());
        return !rsl.isEmpty() ? rsl.get(rsl.size() - 1) : null;
    }
}
