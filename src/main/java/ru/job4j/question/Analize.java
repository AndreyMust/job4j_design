package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
    //public static Long diff(Set<User> previous, Set<User> current) {

        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();

        previous.forEach(e -> previousMap.put(e.getId(), e.getName()));
        current.forEach(e -> currentMap.put(e.getId(), e.getName()));

        int addedCount = (int) currentMap.keySet().stream()
                                    .filter(e -> !previousMap.containsKey(e))
                                    .count();

        int deletedCount = (int) previousMap.keySet().stream()
                                    .filter(e -> !currentMap.containsKey(e))
                                    .count();

        int changedCount = (int) previousMap.keySet().stream()
                                    .filter(currentMap::containsKey)
                                    .filter(e -> !previousMap.get(e).equals(currentMap.get(e)))
                                    .count();

        return new Info(addedCount, changedCount, deletedCount);
    }

    public static void main(String[] args) {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");

        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
        System.out.println(Analize.diff(previous, current));
    }
}
