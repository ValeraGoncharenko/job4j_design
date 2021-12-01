package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    /**
     * первый эл- т связанного списка
     */
    private Node<T> head;

    /**
     * Метод добавляет эл-т в связанный список
     *
     * @param value - эл
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод добавляет первый эл-т в связанный список
     */
    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    /**
     * Метод удаляет первый элемент из связанного списка
     *
     * @return возвращает значение удаленного элемента связанного списка
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> deleteNode = head;
        head = deleteNode.next;
        deleteNode.next = null;
        return deleteNode.value;
    }

    /**
     * Метод переворачивает односвязный список
     *
     * @return boolean
     */
    public boolean revert() {
        boolean result = false;
        if (head != null && head.next != null) {
            Node<T> current = head.next;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
                result = true;
            }
        }
        return result;
    }

    /**
     * Итератор
     *
     * @return возвращает элемент
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * вложенный класс
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
