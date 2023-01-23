package ru.job4j.solid.isp;

/*
*
* В этом примере, класс Plane реализуют интерфейс Vehicle, но он не использует методы drive() и swim().
* Поэтому мы нарушаем Interface Segregation Principle.
 */
public class Plane implements Vehicle {
    public void drive() {
        throw new UnsupportedOperationException();
    }
    public void fly() {
        System.out.println("Plane is flying in the sky");
    }
    public void swim() {
        throw new UnsupportedOperationException();
    }
}
