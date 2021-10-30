package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализуем шаблон "Итератор"
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод возвращает следующий элемент начиная с последнего
     * @return элемент массива или NoSuchElementException() если элементов нет
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1 - point++];
    }
}
