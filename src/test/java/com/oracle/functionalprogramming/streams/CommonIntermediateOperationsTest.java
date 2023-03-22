package com.oracle.functionalprogramming.streams;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class CommonIntermediateOperationsTest {

    @Test
    public void testFilter() {
        var stream = Stream.of("monkey", "gorilla", "bonobo");
        stream.filter(ape -> ape.startsWith("m")).forEach(System.out::println);
    }

    @Test
    public void testDistinct() {
        var stream = Stream.of("duck", "duck", "duck", "goose");
        stream.distinct().forEach(System.out::println);
        // Java calls equals() to determine whether the objects are the same
    }

    @Test
    public void testLimitAndSkip() {
        var stream = Stream.iterate(1, n -> n + 1);  // This is an infinite stream
//        stream.forEach(System.out::println);
        stream.skip(5)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void testMap() {
        var stream = Stream.of("monkey", "gorilla", "bonobo");

        Function<String, Integer> toLength = String::length;

        stream.map(toLength)
                .forEach(System.out::println);
    }

    @Test
    public void testFlatMap() {
        var zero = List.of();
        var one = List.of("Bonobo");
        var two = List.of("Mama Gorilla", "Baby Gorilla");
        var nested = List.of(List.of("aaa", "bbb"), List.of("ccc", "ddd"));

        var animals = Stream.of(zero, one, two, nested);

        Function<List<?>, Stream<?>> toStream = m -> m.stream();

        animals.flatMap(toStream)
                .forEach(System.out::println);
    }

    @Test
    public void testSorted1() {
        // Java uses natural ordering unless we specify a Comparator
        var stream = Stream.of("brown-", "bear-");
        stream.sorted().forEach(System.out::print);
    }

    @Test
    public void testSorted2() {
        // Java uses natural ordering unless we specify a Comparator
        var stream = Stream.of("brown bear-", "grizzly-");
        stream.sorted(Comparator.reverseOrder())
                .forEach(System.out::print);
    }

    @Test
    public void testPeek() {
        // It is useful for debugging because it allows us to perform a stream operation without actually changing the stream.

        var stream = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream.filter(b -> b.startsWith("g"))
                .peek(System.out::println)
                .count();

    }

}
