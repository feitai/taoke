package com.yc.bean1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Test3 {
    public static void main(String[] args) {
        Test3 test = new Test3();
        List<Integer> list = test.all();
        //增强for循环
        for (Integer i : list) {
            System.out.print(i +"\t");
        }
        System.out.println();
        //普通for循环
        for (int i =1;i<list.size();i++) {
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();

        Stream<Integer> stream = test.all2();
        //Steam流普通写法
        stream.map(new Function<Object, String>() {

            @Override
            public String apply(Object o) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return o + "\t";
            }
        }).forEach(System.out::print);
        System.out.println();
        //Steam流lambda写法
        Stream<Integer> stream1 = test.all2();
        stream1.map( o -> o + "\t" ).forEach(System.out::print);
        System.out.println();
        // 3. Flux
        Flux<Integer> flux = test.all3();
        //订阅Flux 序列 ，只有进行订阅后才会触发数据流  ，不订阅就什么都不会发生
        flux.map( o -> o + "\t" ).subscribe(System.out::print);

        System.out.println();
        Mono<String> mono = test.all4();
        //订阅Flux 序列 ，只有进行订阅后才会触发数据流  ，不订阅就什么都不会发生
        mono.map( o -> o + "Hello World" ).map(o -> o+ "sss").subscribe(System.out::print);



    }

    public List<Integer> all() {return Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13);}

    public Stream<Integer> all2() {return Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13);}

    public Flux<Integer> all3() {return Flux.just(1,2,3,4,5,6,7,8,9,10,11,12,13);}

    public Mono<String> all4() {return Mono.just("Hello World!!!");}
}
