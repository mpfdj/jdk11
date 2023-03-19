package com.oracle.functionalprogramming;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest {

    @Test
    public void testFunction() {
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = s -> s.length();

        System.out.println(f1.apply("hello"));
        System.out.println(f2.apply("bye"));
    }

    @Test
    public void testBiFunction() {
        BiFunction<String, String, String> bf1 = String::concat;
        BiFunction<String, String, String> bf2 = (s1, s2) -> s1.concat(s2);

        System.out.println(bf1.apply("hello", "world"));
        System.out.println(bf2.apply("good", "bye"));
    }

    @Test
    public void testWithConvenienceMethods() {
        // The compose() method on Function chains functional interfaces. It passes along the output of one to the input of another.

        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;

        Function<Integer, Integer> combined = f1.andThen(f2);
        System.out.println(combined.apply(3));
    }

}
