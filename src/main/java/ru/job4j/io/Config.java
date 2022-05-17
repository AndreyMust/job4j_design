package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /* Читает пары ключ-значение. Пары разделены символом равно.*/
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.startsWith("#")
                            && s.contains("="))
                    .peek(s -> {
                        if (s.startsWith("=") || s.endsWith("=")) {
                            throw new IllegalArgumentException();
                        }
                    })
                    .forEach(str -> {

                                        int pos = str.indexOf("=");
                                        String s1 = str.substring(0, pos);
                                        String s2 =  str.substring(pos + 1);
                                        values.put(s1, s2);
                                    }
                            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        /*Config config = new Config("./data/app.properties");*/
        Config config = new Config("./data/pair_config.properties");
        config.load();
        for (String key : config.values.keySet()) {
            System.out.println("key= " + key + " value=" + config.values.get(key));
        }
    }

}
