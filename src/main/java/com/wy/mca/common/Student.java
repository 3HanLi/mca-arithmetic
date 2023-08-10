package com.wy.mca.common;

import lombok.Data;

@Data
public class Student {

    private int id;

    private int age;

    private String name;

    public Student(){

    }

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
