package com.ctrip.dal.demo.ds.jdbc.entity;

public class Dog {

    private long id;
    private String name;
    private int age;

    public long getId() {
        return id;
    }

    public Dog setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Dog setAge(int age) {
        this.age = age;
        return this;
    }
}
