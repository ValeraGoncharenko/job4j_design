package ru.job4j.list;

/**
 * Реализуем динамический смисок на основе массива
 * Опишем интерфейс List
 *
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public interface List<T> extends Iterable<T> {
    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}
