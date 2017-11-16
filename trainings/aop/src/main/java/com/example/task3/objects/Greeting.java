package com.example.task3.objects;

/**
 * Created by tkaczenko on 24.01.17.
 */
public class Greeting {
    private final long id;
    private final String name;

    public Greeting(long id, String name) {

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
