package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        User userFirst = new User("Valery", 1, calendar);
        User userSecond = new User("Valery", 1, calendar);

        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());

        for (User k : map.keySet()) {
            Object value = map.get(k);
            System.out.println("key: " + k + " and value: " + value);
        }

        System.out.println(userFirst.hashCode());
        System.out.println(userSecond.hashCode());

        System.out.println(userFirst.equals(userSecond));

        System.out.println(binary(1989780873));
        System.out.println(binary(1480010240));


    }
}
