package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void whenCompatorByNameAndPrority() {
        Comparator<Job> cmpNamePriority =
                new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void riseNameAndPriority() {
        Comparator<Job> comparator =
                new JobRiseByName().thenComparing(new JobRiseByPriority());
        Job first = new Job("Qwerty", 1);
        Job second = new Job("Qwerty", 2);
        Job third = new Job("Felix", 3);
        List<Job> jobs = Arrays.asList(first, second, third);
        List<Job> expected = List.of(third, first, second);
        jobs.sort(comparator);
        assertThat(jobs, is(expected));
    }

    @Test
    public void riseNameAndPriorityGreater() {
        Comparator<Job> comparator =
                new JobRiseByName().thenComparing(new JobRiseByPriority());
        Job first = new Job("Qwerty", 2);
        Job second = new Job("Qwerty", 1);
        int rsl = comparator.compare(first, second);
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void descNameAndPriority() {
        Comparator<Job> comparator =
                new JobDescByName().thenComparing(new JobDescByPriority());
        Job first = new Job("Qwerty", 1);
        Job second = new Job("Qwerty", 2);
        Job third = new Job("Felix", 3);
        List<Job> jobs = Arrays.asList(first, second, third);
        List<Job> expected = List.of(second, first, third);
        jobs.sort(comparator);
        assertThat(jobs, is(expected));
    }

    @Test
    public void descNameAndRisePriority() {
        Comparator<Job> comparator =
                new JobDescByName().thenComparing(new JobRiseByPriority());
        Job first = new Job("Qwerty", 1);
        Job second = new Job("Qwerty", 2);
        Job third = new Job("Felix", 3);
        List<Job> jobs = Arrays.asList(third, second, first);
        List<Job> expected = List.of(first, second, third);
        jobs.sort(comparator);
        assertThat(jobs, is(expected));
    }

    @Test
    public void descPriorityAndRiseName() {
        Comparator<Job> comparator =
                new JobDescByPriority().thenComparing(new JobRiseByName());
        Job first = new Job("Qwerty", 1);
        Job second = new Job("Qwerty", 2);
        Job third = new Job("Felix", 2);
        List<Job> jobs = Arrays.asList(first, second, third);
        List<Job> expected = List.of(third, second, first);
        jobs.sort(comparator);
        assertThat(jobs, is(expected));
    }
}