package ru.job4j.solid.osp;

public class PaymentServiceNew {
    private DiscountCalculator calculator;

    public PaymentServiceNew(DiscountCalculator calculator) {
        this.calculator = calculator;
    }
    public double calculateDiscount(double amount) {
        return calculator.calculateDiscount(amount);
    }
}
