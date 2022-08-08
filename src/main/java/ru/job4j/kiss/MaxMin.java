package ru.job4j.kiss;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, (t1, t2) -> comparator.compare(t1, t2) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, (t1, t2) -> comparator.compare(t2, t1) > 0);
    }

    private <T> T compare(List<T> value, BiPredicate<T, T> predicator) {
        Iterator<T> iterator = value.iterator();
        T resultValue = iterator.next();
        while (iterator.hasNext()) {
            T nextValue = iterator.next();
            if (predicator.test(resultValue, nextValue)) {
                resultValue = nextValue;
            }
        }
        return resultValue;
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3, 4, 5);
        MaxMin maxMin = new MaxMin();
        System.out.println(maxMin.max(integerList, (o1, o2) -> o2 - o1));
        System.out.println(maxMin.min(integerList, (o1, o2) -> o2 - o1));
    }

}
