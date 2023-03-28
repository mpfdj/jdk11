package com.oracle.functionalprogramming.streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class PuttingTogetherThePipelineTest {

    @Test
    public void testExample1() {
        var list = List.of("Toby", "Anna", "Leroy", "Alex");

        list.stream()
                .filter(s -> s.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void testExample2() {
        var infinite = Stream.iterate(1, x -> x + 1);
        infinite.filter(x -> x % 2 == 1)
                .peek(System.out::print)
                .limit(5)
                .forEach(System.out::print);
    }
}
