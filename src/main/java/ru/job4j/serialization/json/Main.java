package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));


         Car car = new Car("BMW", "X6", 510, false,
                new Contact("123123331"), new String[] {"2021", "2022"});
        System.out.println(car);
        System.out.println(gson.toJson(car));

        /* Создадим JSON файл для новой машины*/
        String jsonCarString = "{"
                                + " \"brand\":\"BMW\",\"name\":\"3\","
                                + " \"power\":210,\"isElectric\":false,"
                                + " \"ownerContact\":{\"phone\":\"7777777\"},"
                                + " \"insurances\":[\"2011\",\"2012\"]"
                                + "}";

        Car jsonCar = gson.fromJson(jsonCarString, Car.class);
        System.out.println(jsonCar);
    }
}
