package ru.job4j.solid.osp;

public class RegularDiscountCalculator implements DiscountCalculator {
    public double calculateDiscount(double amount) {
        return amount * 0.1;
    }
}
