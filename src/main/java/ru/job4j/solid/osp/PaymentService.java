package ru.job4j.solid.osp;

/*
* Этот метод открыт для модификации всякий раз, когда добавляются новые типы клиентов или когда
* изменяется расчет скидки.
* Это нарущает принцип открытости закрытости
 */
public class PaymentService {
    public double calculateDiscount(String type, double amount) {
        if (type.equals("regular")) {
            return amount * 0.1;
        } else if (type.equals("premium")) {
            return amount * 0.2;
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }
}
