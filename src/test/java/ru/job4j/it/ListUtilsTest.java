package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemove4fromList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(input, e -> e == 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 4, 5, 6)));
    }

    @Test
    public void whenReplace4with400() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ListUtils.replaceIf(input, e -> e == 3, 333);
        assertThat(input, is(Arrays.asList(0, 1, 2, 333, 4, 5, 6)));
    }

    @Test
    public void whenRemoveAllSelectedElements() {
        List<Integer> list = new ArrayList<>(List.of(0, 1, 4, 5, 2, 3));
        List<Integer> elements = new ArrayList<>(List.of(1, 2));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(0, 4, 5, 3)));
    }
}