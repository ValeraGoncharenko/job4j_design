package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * итератор возвращающий только четные цифры
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }
    /**
     * Метод проверяет есть ли следующий элемент,
     * если элемент нечетный то увеличиваем index на 1
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        while (index < data.length) {
            if (data[index] % 2 != 0) {
                index++;
            } else {
                return true;
            }
        }
        return false;
    }
    /**
     * Метод возвращает следующий элемент
     * @return элемент массива или NoSuchElementException() если элементов нет
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}
