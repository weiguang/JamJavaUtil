package com.okayjam.test;

import com.okayjam.annotation.JamAnnotation;

import java.util.Date;

public class User {

    int id;

    //可以指定转换json的字段
    @JamAnnotation("BBBBBBB")
    String name;

    int age;
    Date birthday;
    double weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", weight=" + weight +
                '}';
    }
}
