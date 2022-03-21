package OnlyTest;


import Project.Item;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cer {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String NAME = "postgres";
    public static final String PASSWORD = "westside";
    private static String FIND_BY_ID = "SELECT*FROM items WHERE id=?";

    static Map<Long, Item> collections = new ConcurrentHashMap<>();


    public boolean update(long id, Item item) {
        if (collections.containsKey(id)) {
            Item result = collections.get(id);
            result.setDescription(item.getDescription());
            result.setName(item.getName());
            collections.put(id, result);
        }
        return false;
    }

    public static boolean corpo(int x) {
        if (x > 0) {
            System.out.println("s");
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(Cer.corpo(3));
    }

}


class Animal {
    void animal() {
        System.out.println("animal");
    }
}

class Monkey extends Animal {
    String name;

    void animal() {
        System.out.println("monkey");
    }

    void monkey() {
        System.out.println("monkeymethod");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Monkey(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                '}';
    }
}