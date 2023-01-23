package ru.job4j.solid.osp;

public class FileReaderNew  implements FileContent {

    private String content;

    public FileReaderNew(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
