package com.zy.zhangyue001.io;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class Employee implements Serializable {

    public String name;
    public String address;
    public transient int SSN;
    public int number;

    public void mailCheck() {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }
}
