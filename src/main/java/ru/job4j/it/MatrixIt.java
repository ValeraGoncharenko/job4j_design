package ru.job4j.it;


import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Реализуем шаблон "Итератор" для двухмерного массива
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }
    /**
     * Метод проверяет есть ли следующий элемент
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length;
    }
    /**
     * Метод возвращает следующий элемент начиная
     * @return элемент массива или NoSuchElementException() если элементов нет
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
