package org.example;

public class Main {
    public static void main(String[] args) {
    Cooking cooking = new cooktomato();
    cooking= new cookfood();
    cooking.start();
    }

}
 abstract class Cooking{
    protected  abstract void step1();
     protected  abstract void step2();

     public void start() {
         System.out.println("加油");
         step1();
         step2();
         System.out.println("出锅");
     }
        }


        class cooktomato extends Cooking{

            @Override
            protected void step1() {
                System.out.println("加入西红柿和鸡蛋");


            }

            @Override
            protected void step2() {
                System.out.println("翻炒");
            }
        }

class cookfood extends Cooking{

    @Override
    protected void step1() {
        System.out.println("加入食物");


    }

    @Override
    protected void step2() {
        System.out.println("翻炒");
    }
}