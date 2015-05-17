package com.example.user.myapplication;

import java.util.Random;

/**
 * Created by User on 5/17/2015.
 */
public class Student implements MyCustomAdapter.AdapterInterface {

    private String  name;
    private int     age;


    public Student (String name){
        this.name = name;
        age = new Random().nextInt(120);
    }

    @Override
    public String getText1() {
        return name;
    }

    @Override
    public String getText2() {
        return Integer.toString(age);
    }
}
