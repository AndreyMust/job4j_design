package ru.job4j.solid.osp;

public class OrderState {
    private boolean isDiscounted;
    private boolean isDelivered;

    public double applyDiscount(double price) {
        if (isDiscounted) {
            return price * 0.9;
        } else {
            return price;
        }
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
