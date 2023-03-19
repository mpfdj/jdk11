package com.oracle.functionalprogramming;

import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateTest {

    @Test
    public void testPredicate() {
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = s -> s.isEmpty();

        System.out.println(p1.test("hello"));
        System.out.println(p2.test("bye"));
    }

    @Test
    public void testBiPredicate() {
        BiPredicate<String, String> bp1 = String::startsWith;
        BiPredicate<String, String> bp2 = (s1, s2) -> s1.startsWith(s2);

        System.out.println(bp1.test("hello world", "hello"));
        System.out.println(bp2.test("goodbye world", "good"));
    }

    @Test
    public void testWithConvenienceMethods() {
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");

        System.out.println(egg.test("egg"));
        System.out.println(brown.test("brown egg"));



        Predicate<String> brownEggs = egg.and(brown);
        Predicate<String> otherEggs = egg.and(brown.negate());

        System.out.println(brownEggs.test("brown egg"));
        System.out.println(brownEggs.test("speckled egg"));
        System.out.println(otherEggs.test("white egg"));
    }


}
