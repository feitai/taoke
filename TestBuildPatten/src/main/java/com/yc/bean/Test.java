package com.yc.bean;

public class Test {
    public static void main(String[] args) {
        Person p = Person.builder().age(12).name("历史").gender("F").build();

        System.out.println(p.toString());
    }
}
