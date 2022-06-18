package ru.job4j.gc;

import org.ehcache.sizeof.SizeOf;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class Memory {
    private int cnt;
    private int cnt2;
    //private int cnt3;
    private String name = "A111";

    public static void main(String[] args) {
        Memory memory = new Memory();
        Person person = new Person(25,"Andrew");
        int x = 1;
        String name = "A";

        SizeOf sizeOf = SizeOf.newInstance(); // (1)
        long shallowSize = sizeOf.sizeOf(memory);

        System.out.println(shallowSize);
        //System.out.println(sizeOf(memory));
        //System.out.println(sizeOf(name));
        //System.out.println("Person size =" + sizeOf(person));
        Runtime env = Runtime.getRuntime();
        System.out.println(System.getProperty("os.arch"));
    }
}
