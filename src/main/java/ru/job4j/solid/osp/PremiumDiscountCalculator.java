package ru.job4j.solid.osp;

public class PremiumDiscountCalculator implements DiscountCalculator {
    public double calculateDiscount(double amount) {
        return amount * 0.2;
    }
}
