package ru.job4j.gc;

/* Пустой Java объект занимает 8 байт
* int - 4 байта, выравнивание до 8
* Sting - Пустая строка занимает 40 байт + длина строки*2
* Итого: примерно 64 байт на один объект с выравниванием
* */

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
