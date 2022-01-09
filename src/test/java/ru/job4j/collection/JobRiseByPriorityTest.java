package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JobRiseByPriorityTest {

    @Test
    public void risePriority() {
        Job first = new Job("Qwerty", 2);
        Job second = new Job("Arnold", 1);
        Job third = new Job("Felix", 3);
        List<Job> jobs = Arrays.asList(first, second, third);
        List<Job> expected = List.of(second, first, third);
        jobs.sort(new JobRiseByPriority());
        assertThat(jobs, is(expected));
    }
}