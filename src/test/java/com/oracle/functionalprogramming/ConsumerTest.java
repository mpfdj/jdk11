package com.oracle.functionalprogramming;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void testConsumer() {
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = s -> System.out.println(s);

        c1.accept("hello");
        c2.accept("bye");
    }

    @Test
    public void testBiConsumer() {
        var map = new HashMap<String, Integer>();

        BiConsumer<String, Integer> bc1 = map::put;
        BiConsumer<String, Integer> bc2 = (k, v) -> map.put(k, v);


        bc1.accept("one", 1);
        System.out.println(map);

        bc2.accept("two", 2);
        System.out.println(map);
    }

    @Test
    public void testWithConvenienceMethods() {
        Consumer<String> c1 = s -> System.out.print("1: " + s);
        Consumer<String> c2 = s -> System.out.print(", 2: " + s);

        Consumer<String> combined = c1.andThen(c2);
        combined.accept("hello");
    }

}
