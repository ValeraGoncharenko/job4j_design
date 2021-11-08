package ru.job4j.list;

import java.util.*;

/**
 * Реализуем динамический смисок на основе массива
 * Класс SimpleArrayList имплементирует интерфейс List
 *
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public class SimpleArrayList<T> implements List<T> {

    /**
     * поле для хранения эл-тов
     */
    private T[] container;

    /**
     * поле для подсчета кол-ва эл
     */
    private int size;

    /**
     * счетчик изменений
     */
    private int modCount;

    /**
     * Конструктор для создания массива с заданной емкостью
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод расширяет массив при наполнении
     */
    public void expansionArray() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    /**
     * Метод добавляет эл-т в массив
     *
     * @param value - эл
     */
    @Override
    public void add(T value) {
        expansionArray();
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод заменяет эл-т на переданный в параметрах
     *
     * @param index    - индекс эл
     * @param newValue - новый эл
     * @return замененный эл
     */
    @Override
    public T set(int index, T newValue) {
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    /**
     * Метод удаляет эл-т
     *
     * @param index - индекс эл
     * @return удаленный эл
     */
    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[size - 1] = null;
        size--;
        modCount++;
        return value;
    }

    /**
     * Метод выдает эл-т
     *
     * @param index - индекс эл
     * @return эл
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Метод выдает количество эл
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**счетчик для проверки счета операций добавления -удаления*/
            private final int expectedModCount = modCount;

            /**индекс для итератора*/
            private int point = 0;

            /**
             * Метод проверяет есть ли следующий элемент и
             * на каждой итерации сравнивает сохраненное значение, с текущим значением поля modCount, если они отличаются, то генерируется исключение.
             *
             * @return boolean или ConcurrentModificationException()
             */
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            /**
             * Метод возвращает следующий эл
             *
             * @return элемент массива или NoSuchElementException() если элементов нет
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}
