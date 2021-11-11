package ru.job4j.collection.list;

import java.util.*;

/**
 * Реализуем двусвязный список
 * Класс SimpleLinkedList имплементирует интерфейс List
 *
 * @author goncharikvv@gmail.com
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {

    /**
     * поле для подсчета кол-ва эл
     */
    private int size;

    /**
     * счетчик изменений
     */
    private int modCount;

    /**
     * первый эл- т коллекции
     */
    transient Node<E> first;

    /**
     * последний эл- т коллекции
     */
    transient Node<E> last;

    /**
     * вложенный класс
     */
    private static class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item) {
            this.item = item;
        }
    }

    /**
     * Метод добавляет эл-т в конец списка
     *
     * @param value - эл
     */
    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(value);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
            newNode.prev = l;
        }
        size++;
        modCount++;
    }

    /**
     * Метод выдает эл-т
     *
     * @param index - индекс эл
     * @return эл
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            current = current.next;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            /**
             * счетчик для проверки счета операций добавления -удаления
             */
            private final int expectedModCount = modCount;

            /**
             * индекс для итератора
             */
            private Node<E> point = first;

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
                return point != null;
            }

            /**
             * Метод возвращает следующий эл
             *
             * @return элемент массива или NoSuchElementException() если элементов нет
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = point.item;
                point = point.next;
                return value;
            }

        };
    }
}



