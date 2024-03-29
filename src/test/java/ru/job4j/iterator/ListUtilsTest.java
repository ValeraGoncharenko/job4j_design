package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

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
    public void whenRemoveItems() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        Predicate<Integer> removeItems = (x -> x > 1 && x < 4);
        ListUtils.removeIf(input, removeItems);
        assertThat(input, is(Arrays.asList(0, 1, 4)));
    }

    @Test
    public void whenReplaceItems() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        Predicate<Integer> removeItems = (x -> x > 1 && x < 4);
        ListUtils.replaceIf(input, removeItems, 3);
        assertThat(input, is(Arrays.asList(0, 1, 3, 4)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> output = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, output);
        assertThat(input, is(Arrays.asList(1, 4)));
    }

}