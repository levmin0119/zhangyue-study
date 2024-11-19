package com.zy.zhangyue001.demo;

public class Person {
    private String name;
    private int age;
    private String address;
    private String phone;


    public String mailInformation(String address, String phone) {
        String mailAndInformation = address + ":" + phone;
        return mailAndInformation;
    }

    public Person(String phone, String address, int age, String name) {
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
