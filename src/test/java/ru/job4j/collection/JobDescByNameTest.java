package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JobDescByNameTest {

    @Test
    public void nameDecrease() {
        Job first = new Job("Qwerty", 1);
        Job second = new Job("Arnold", 2);
        Job third = new Job("Felix", 3);
        List<Job> jobs = Arrays.asList(first, second, third);
        List<Job> expected = List.of(first, third, second);
        jobs.sort(new JobDescByName());
        assertThat(jobs, is(expected));
    }
}