package com.oracle.functionalprogramming.streams;

import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonTerminalOperationsTest {

    @Test
    public void testCount() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(s.count());
    }

    @Test
    public void testMin() {
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");

        Comparator<String> sortByLength = (s1, s2) -> s1.length() - s2.length();
        Optional<String> min = s.min(sortByLength);
        min.ifPresent(System.out::println);
    }

    @Test
    public void testMax() {
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");

        Comparator<String> sortByLength = (s1, s2) -> s1.length() - s2.length();
        Optional<String> max = s.max(sortByLength);
        max.ifPresent(System.out::println);
    }

    @Test
    public void testFindAny() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.findAny().ifPresent(System.out::println);

        Stream<String> infinite = Stream.generate(() -> "chimp");
        System.out.println(infinite.findAny());
    }

    @Test
    public void testMatch() {
        var list = List.of("monkey", "2", "chimp");
        Predicate<String> predicate = s -> Character.isLetter(s.charAt(0));  // Checks if the first character is a letter.

        System.out.println(list.stream().anyMatch(predicate));
        System.out.println(list.stream().allMatch(predicate));
        System.out.println(list.stream().noneMatch(predicate));

        Stream<String> infinite = Stream.generate(() -> "chimp");
        System.out.println(infinite.anyMatch(predicate));
    }

    @Test
    public void testForEach() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (s1, s2) -> s1 + s2);
        System.out.println(word);
    }

    @Test
    public void testReduce2() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", String::concat);
        System.out.println(word);

    }

    @Test
    public void testReduce3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        int number = stream.reduce(1, (i1, i2) -> i1 * i2);
        System.out.println(number);
    }

    @Test
    public void testReduce4() {
        BinaryOperator<Integer> op = (a, b) -> a * b;

        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty.reduce(op).ifPresent(System.out::println);
        oneElement.reduce(op).ifPresent(System.out::println);
        threeElements.reduce(op).ifPresent(System.out::println);
    }

    @Test
    public void testReduce5() {
        Stream<String> stream = Stream.of("w", "o", "l", "f!");

        BiFunction<Integer, String, Integer> integerStringIntegerBiFunction = (i, s) -> i + s.length();
        BinaryOperator<Integer> integerBinaryOperator = (a, b) -> a + b;
        int length = stream.reduce(0, integerStringIntegerBiFunction, integerBinaryOperator);
        System.out.println(length);
    }

    @Test
    public void testCollect1() {
        var stream = Stream.of("w", "o", "l", "f");

        Supplier<StringBuilder> supplier = StringBuilder::new;
        BiConsumer<StringBuilder, String> accumulator = StringBuilder::append;
        BiConsumer<StringBuilder, StringBuilder> combiner = StringBuilder::append;

        StringBuilder word = stream.collect(supplier, accumulator, combiner);
        System.out.println(word);
    }

    @Test
    public void testCollect2() {
        var stream = Stream.of("w", "o", "l", "f");

        Supplier<TreeSet> supplier = TreeSet::new;
        BiConsumer<TreeSet, String> accumulator = TreeSet::add;
        BiConsumer<TreeSet, TreeSet> combiner = TreeSet::addAll;

        TreeSet set = stream.collect(supplier, accumulator, combiner);
        System.out.println(set);
        // TreeSet is sorted
    }

    @Test
    public void testCollect3() {
        var stream = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);  // TreeSet is sorted
    }

    @Test
    public void testCollect4() {
        var stream = Stream.of("w", "o", "l", "f");
        Set<String> set = stream.collect(Collectors.toSet());
        System.out.println(set);
    }

}
