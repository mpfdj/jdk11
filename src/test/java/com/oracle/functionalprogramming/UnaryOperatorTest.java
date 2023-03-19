package com.oracle.functionalprogramming;

import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class UnaryOperatorTest {

    // Notice that we only specify 1 generic type, since they are all the same...

    @Test
    public void testUnaryOperator() {
        UnaryOperator<String> uo1 = String::toUpperCase;
        UnaryOperator<String> uo2 = s -> s.toUpperCase();

        System.out.println(uo1.apply("hello"));
        System.out.println(uo2.apply("bye"));
    }

    @Test
    public void testBinaryOperator() {
        BinaryOperator<String> bo1 = String::concat;
        BinaryOperator<String> bo2 = (s1, s2) -> s1.concat(s2);

        System.out.println(bo1.apply("hello", "world"));
        System.out.println(bo2.apply("good", "bye"));
    }
}
