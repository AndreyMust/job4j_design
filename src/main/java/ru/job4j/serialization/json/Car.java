package ru.job4j.serialization.json;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import java.util.Arrays;

public class Car {
    private String brand;
    private String name;
    private Integer power;
    private boolean isElectric;
    private Contact ownerContact;
    private String[] insurances;

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Integer getPower() {
        return power;
    }

    public boolean isElectric() {
        return isElectric;
    }

    /* @JSONPropertyIgnore */
    public Contact getOwnerContact() {
        return ownerContact;
    }

    public String[] getInsurances() {
        return insurances;
    }

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

    public static void main(String[] args) {

        Car car = new Car("BMW", "X6", 510, false,
                new Contact("910-233-31"), new String[] {"2019", "2018"});

        System.out.println(new JSONObject(car));
    }
}
