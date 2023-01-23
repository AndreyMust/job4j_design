package ru.job4j.solid.isp;


/*
*
* В этом примере, класс Car реализуют интерфейс Vehicle, но он не использует методы fly() и swim().
* Поэтому мы нарушаем Interface Segregation Principle.
 */
public class Car implements Vehicle {

    public void drive() {
        System.out.println("Car is driving on the road");
    }
    public void fly() {
        throw new UnsupportedOperationException();
    }
    public void swim() {
        throw new UnsupportedOperationException();
    }
}
