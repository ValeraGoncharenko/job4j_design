package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод возвращает значение и удаляет его из коллекции
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в коллекцию
     *
     * @param value - значение
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}
