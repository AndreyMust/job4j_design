package ru.job4j.map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar(2013, Calendar.JANUARY, 31);
        System.out.println(sdf.format(calendar.getTime()));

        User user1 =  new User("Albert", 2, calendar);
        User user2 =  new User("Albert", 2, calendar);

        HashMap<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(user1.hashCode() & 15);
        System.out.println(user2.hashCode() & 15);
        System.out.println(map);



    }
}
