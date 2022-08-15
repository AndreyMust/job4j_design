package ru.job4j.kiss;

import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void testMax() {
        List<Integer> integerList = Arrays.asList(0, 1, 2, 3);
        MaxMin maxMin = new MaxMin();
        Integer max = maxMin.max(integerList, (o1, o2) -> o2 - o1);
        Assert.assertEquals(3, (int) max);

    }

    @Test
    public void testMin() {
        List<Integer> integerList = Arrays.asList(0, 1, 2, 3);
        MaxMin maxMin = new MaxMin();
        Integer min = maxMin.min(integerList, (o1, o2) -> o2 - o1);
        Assert.assertEquals(0, (int) min);
    }

    @Test
    public void testNull() {
        List<Integer> integerList = Arrays.asList();
        MaxMin maxMin = new MaxMin();
        Integer min = maxMin.min(integerList, (o1, o2) -> o2 - o1);
        Assert.assertNull(min);
    }
}