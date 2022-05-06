package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(20);

    @Override
    public boolean add(T value) {
        boolean res = false;
        if (!contains(value)) {
            set.add(value);
            res = true;
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    public static void main(String[] args) {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}