package ru.job4j.collection;

/**
 * реализация очереди на двух стеках
 */
public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * счетчик первого стека
     */
    private int countIn = 0;

    /**
     * счетчик второго стека
     */
    private int countOut = 0;

    /**
     * Метод возвращает первое значение и удаляет его
     */
    public T poll() {
        if (countOut != countIn) {
            while (countIn != 0) {
                out.push(in.pop());
                countIn--;
                countOut++;
            }
            countOut--;
        }
        return out.pop();
    }

    /**
     * Метод добавляет значение в конец
     *
     * @param value - эл
     */
    public void push(T value) {
        in.push(value);
        countIn++;
    }
}
