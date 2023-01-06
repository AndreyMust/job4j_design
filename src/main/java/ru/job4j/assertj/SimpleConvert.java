package ru.job4j.assertj;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleConvert {

    public String[] toArray(String... example) {
        return example;
    }

    public List<String> toList(String... example) {
        List<String> list = new ArrayList<>();
        for (String s : example) {
            list.add(s);
        }
        return list;
    }

    public Set<String> toSet(String... example) {
        return Arrays.stream(example).collect(Collectors.toSet());
    }

    public Map<String, Integer> toMap(String... example) {
        return Stream.iterate(0, i -> i < example.length, i -> i + 1)
                .collect(Collectors.toMap(i -> example[i], i -> i, (s1, s2) -> s1));
    }

    public static void main(String[] args) {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("1", "2", "3");
        System.out.println(Arrays.toString(array));
        List<String> list = simpleConvert.toList("1", "2", "3");
        System.out.println(list);
        Set<String> set = simpleConvert.toSet("1", "2", "3");
        System.out.println(set);
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three");
        System.out.println(map);
    }
}
