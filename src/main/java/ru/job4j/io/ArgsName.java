package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .peek(s -> {
                    if (s.startsWith("=")
                            || ((s.endsWith("=") && (s.length() == 1)))
                            || s.startsWith("==")) {
                        throw new IllegalArgumentException("Does not match the pattern -key=value");
                    }
                })
                .filter(str -> str.contains("="))
                .forEach(str -> {
                                    int pos = str.indexOf("=");
                                    String s1 = str.substring(1, pos);
                                    String s2 =  str.substring(pos + 1);
                                    if (s2.length() == 0) {
                                        throw new IllegalArgumentException("Does not match the pattern -key=value");
                                    } else {
                                        values.put(s1, s2);
                                    }
                                });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
