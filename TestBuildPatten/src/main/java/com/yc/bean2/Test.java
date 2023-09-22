package com.yc.bean2;

public class Test {
    public static void main(String[] args) {
        Apple apple = Apple.builder().color("red").name("apple").build();
        System.out.println(apple);
    }
}
