package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    private String name;

    @XmlElement
    private int listCount;

    @XmlAttribute
    private boolean isPaperBook;

    @XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
    private String[] authors;

    private Contact contact;

    public Book() {
    }

    public Book(String name, int listCount, boolean isPaperBook, Contact contact, String... authors) {
        this.name = name;
        this.listCount = listCount;
        this.isPaperBook = isPaperBook;
        this.authors = authors;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name='" + name + '\''
                + ", listCount=" + listCount
                + ", isPaperBook=" + isPaperBook
                + ", authors=" + Arrays.toString(authors)
                + ", contact=" + contact
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {

        Book book = new Book("Book One", 125, true,
                new Contact("123-443-112"), "Ivanov", "Petrov");

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Book.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, как нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xmlDoc;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(book, writer);
            xmlDoc = writer.getBuffer().toString();
            System.out.println(xmlDoc);
        }

        /* теперь нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xmlDoc)) {
            /* десериализуем */
            Book result = (Book) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
