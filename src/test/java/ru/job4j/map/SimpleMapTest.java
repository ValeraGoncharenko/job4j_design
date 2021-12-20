package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenMapPutThenTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        assertTrue(map.put(2, "second"));
    }

    @Test
    public void whenMapPutThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        assertFalse(map.put(1, "first"));
    }

    @Test
    public void whenMapGet() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        assertThat("first", is(map.get(1)));
    }

    @Test
    public void whenMapGetNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        assertThat(null, is(map.get(2)));
    }

    @Test
    public void whenMapRemove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenMapExpand() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenIteratorAndNextThenKey() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        assertTrue(map.put(2, "second"));
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNextIsFalseAndTryNextThenNSEE() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        assertFalse(it.hasNext());
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterIteratorAndNextThenCME() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        Iterator<Integer> it = map.iterator();
        map.put(2, "second");
        it.next();
    }

    @Test
    public void whenExpandT() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");
        map.put(4, "fourth");
        map.put(5, "fifth");
        map.put(6, "sixth");
        map.put(7, "seventh");
        map.put(8, "eighth");
        map.put(9, "ninth");
        assertThat(map.get(1), is("first"));
        assertThat(map.get(9), is("ninth"));
    }
}