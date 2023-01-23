package ru.job4j.solid.osp;

/*
*
* Поле OrderState класс Order зависит от класса state, а не от абстракции
* этим нарушается принцип Open Closed Principle
 */

public class Order {
    private double price;
    private OrderState state;

    public double getPrice() {
        return state.applyDiscount(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
