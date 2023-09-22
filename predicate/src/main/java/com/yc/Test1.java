package com.yc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test1 {

    class A implements Predicate<String>{
        @Override
        public boolean test(String s){
            if(s.length()>3){
                return true;
            }else {
                return false;
            }
        }
    }


    public static void main(String[] args){

        Predicate p;
        BiPredicate pp;


        List<String> ns   = Arrays.asList("Aleac","Bob","BobLie","Daved");
        List<String> names = new ArrayList<>(ns);

        Predicate<String> lengthPredicate = s -> s.length()> 3;
        names.removeIf(lengthPredicate);
        System.out.println(names);



        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
        Predicate<Integer> predicate1 = x -> x > 3;
        Predicate<Integer> predicate2 = x -> x < 9;

        List<Integer> collect = list.stream().filter(predicate1 .and(predicate2)).collect(Collectors.toList());
        System.out.println(collect);



        collect = list.stream().filter(predicate1.or(predicate2)).collect(Collectors.toList());
        System.out.println(collect);

        List<String> list3 = Arrays.asList("java","c++","python","c#","javaScript","kotlin","php");
        Predicate<String> predicate4 = x -> x.endsWith("+");

        List<String> collect3 = list3.stream().filter(predicate4.negate()).collect(Collectors.toList());
        System.out.println(collect3);



        List<String> list4 = Arrays.asList("java","c++","python","c#","javaScript","kotlin","php");
        Predicate<String> predicate5 = x -> x.endsWith("+");

        List<String> collect4 = list3.stream().filter(predicate5::test).collect(Collectors.toList());
        System.out.println(collect4);


        List<String> list5 = Arrays.asList("java","c++","python","c#","javaScript","kotlin","php");
        Predicate<String> predicate6 = x -> x.startsWith("c");

       boolean ret = predicate6.or(s ->s.endsWith("t")).test("javaScript");
        System.out.println(ret);

        boolean ret1 = predicate6.and(s ->s.length()==4).negate().test("java");
        System.out.println(ret1);

        BiPredicate<String,String> equalPredicate = (s1,s2) -> s1.equals(s2);
        System.out.println(  equalPredicate.test("java","java"));

        System.out.println(equalPredicate.test("java","javaScript"));
    }


}
