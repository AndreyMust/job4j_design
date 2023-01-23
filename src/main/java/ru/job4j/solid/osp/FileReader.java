package ru.job4j.solid.osp;

/*
*  Класс нарушает  Open-Closed Principle
*  Если мы хотим изменить способ чтения файла, например, для поддержки чтения из другой файловой
*  системы или для сжатия файла перед чтением, нам придется изменить метод readFile.
*  Это приведет к тому, что любой код, который зависит от класса FileReader, также изменится,
*  нарушив принцип открытого-закрытого.
*/

public class FileReader {

    public String readFile(String fileName) {
        String fileContent = "";
        return fileContent;
    }
}
