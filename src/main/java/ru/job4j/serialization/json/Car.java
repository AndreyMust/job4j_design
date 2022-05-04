package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private String brand;
    private String name;
    private Integer power;
    private boolean isElectric;
    private Contact ownerContact;
    private String[] insurances;

    public Car(String brand, String name, Integer power,
               boolean isElectric, Contact ownerContact, String[] insurances) {
        this.brand = brand;
        this.name = name;
        this.power = power;
        this.isElectric = isElectric;
        this.ownerContact = ownerContact;
        this.insurances = insurances;
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand=" + brand
                + ", name=" + name
                + ", power=" + power
                + ", isElectric=" + isElectric
                + ", contact=" + ownerContact
                + ", insurances=" + Arrays.toString(insurances)
                + '}';
    }
}
