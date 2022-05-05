package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();

        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();

        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        System.out.println("Любой класс");
        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);

        System.out.println();


        System.out.println("Ограничение сверху: Базовый класс вверху иерархии");
        /*gen.printBoundedWildCard(first);*/
        System.out.println("(Animal) исключен");
        gen.printBoundedWildCard(second); /*Predator */
        gen.printBoundedWildCard(third); /*Tiger*/

        System.out.println();

        System.out.println("Ограничение снизу: Дочернии классы внизу иерархии");
        gen.printLowerBoundedWildCard(first);   /* Animals    */
        gen.printLowerBoundedWildCard(second);  /* Predator */
        System.out.println("(Tiger) исключен");
        /* gen.printLowerBoundedWildCard(third); */
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
