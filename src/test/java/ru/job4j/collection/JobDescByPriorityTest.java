package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JobDescByPriorityTest {

    @Test
    public void priorityDecrease() {
        Job first = new Job("Qwerty", 1);
        Job second = new Job("Arnold", 2);
        Job third = new Job("Felix", 3);
        List<Job> jobs = Arrays.asList(first, second, third);
        List<Job> expected = List.of(third, second, first);
        jobs.sort(new JobDescByPriority());
        assertThat(jobs, is(expected));
    }
}