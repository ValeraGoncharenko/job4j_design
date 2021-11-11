package ru.job4j.linkedList;

/**
 * Реализуем двусвязный список
 * Опишем интерфейс List
 *
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public interface List<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}
