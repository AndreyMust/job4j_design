package ru.job4j.solid.isp;

/*
*
* * В этом примере, класс Camera реализуют интерфейс IPhone, использует только takePhoto,
* но он не использует остальные методы.
 * Поэтому мы нарушаем Interface Segregation Principle.
 */

public class Camera implements IPhone {

    public void call() {
        throw new UnsupportedOperationException();
    }
    public void takePhoto() {
        System.out.println("Camera take a Photo");
    }
    public void makeVideo() {
        throw new UnsupportedOperationException();
    }
    public void browseInternet() {
        throw new UnsupportedOperationException();
    }
}
