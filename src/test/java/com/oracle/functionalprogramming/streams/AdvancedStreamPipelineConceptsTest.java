package com.oracle.functionalprogramming.streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.minBy;

public class AdvancedStreamPipelineConceptsTest {
    private void threeDigit(Optional<Integer> optional) {
        optional.map(n -> "" + n)
                .filter(s -> s.length() == 3)
                .ifPresent(System.out::println);
    }

    private Optional<Integer> toLength(Optional<Integer> optional) {
        return optional.map(i -> "" + i)
                .map(String::length);
    }

    private static Optional<Integer> calculator(Integer i) {
        return Optional.of(i.toString().length());
    }

    private Optional<Integer> toLengthWithFlatMap(Optional<Integer> optional) {
//        Optional<Optional<Integer>> result = optional.map(i -> calculator(i));
        Optional<Integer> result = optional.flatMap(AdvancedStreamPipelineConceptsTest::calculator);
        return result;
    }



    @Test
    public void testThreeDigit() {
        threeDigit(Optional.empty());
        threeDigit(Optional.of(4));
        threeDigit(Optional.of(123));
    }

    @Test
    public void testToLength() {
        Optional<Integer> result = toLength(Optional.of(4));
        result.ifPresent(System.out::println);
    }

    @Test
    public void testWithFlatMap() {
        Optional<Integer> result = toLengthWithFlatMap(Optional.of(4));
        result.ifPresent(System.out::println);
    }

    @Test
    public void testBasicCollectors1() {
        var animals = Stream.of("lions", "tigers", "bears");
        String result = animals.collect(Collectors.joining(", "));
        System.out.println(result);
    }

    @Test
    public void testBasicCollectors2() {
        var animals = Stream.of("lions", "tigers", "bears");
//        Double avgLength = animals.collect(Collectors.averagingInt(s -> s.length()));
        Double avgLength = animals.collect(Collectors.averagingInt(String::length));
        System.out.println(avgLength);
    }

    @Test
    public void testBasicCollectors3() {
        var animals = Stream.of("lions", "tigers", "bears");
        TreeSet result = animals.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result);
    }

    @Test
    public void testCollectingIntoMaps1() {
        var animals = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> result = animals.collect(
                Collectors.toMap(s -> s, s -> s.length())
        );
        System.out.println(result);
    }

    @Test
    public void testCollectingIntoMaps2() {
        var animals = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> result = animals.collect(
                Collectors.toMap(s -> s.length(), s -> s)
        );
        System.out.println(result);  // java.lang.IllegalStateException: Duplicate key 5 (attempted merging values lions and bears)
    }

    @Test
    public void testCollectingIntoMaps3() {
        var animals = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> result = animals.collect(
                Collectors.toMap(
                        s -> s.length(),
                        s -> s,
                        (s1, s2) -> s1 + ", " + s2)
        );
        System.out.println(result);
        System.out.println(result.getClass());
    }

    @Test
    public void testCollectingIntoMaps4() {
        var animals = Stream.of("lions", "tigers", "bears");

//        Supplier<TreeMap<Integer, String>> newTreeMap = TreeMap::new;
        Supplier<TreeMap<Integer, String>> newTreeMap = () -> new TreeMap<>();

        BinaryOperator<String> mergeFunction = (s1, s2) -> s1 + ", " + s2;

        TreeMap<Integer, String> result = animals.collect(
                Collectors.toMap(
                        s -> s.length(),
                        s -> s,
                        mergeFunction,
                        newTreeMap)
        );
        System.out.println(result);
        System.out.println(result.getClass());
    }

    @Test
    public void testGroupingBy1() {
        var animals = Stream.of("lions", "tigers", "bears");
//        Map<Integer, List<String>> map = animals.collect(Collectors.groupingBy(String::length));
        Map<Integer, List<String>> map = animals.collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(map);
    }

    @Test
    public void testGroupingBy2() {
        var animals = Stream.of("lions", "tigers", "bears");

        TreeMap<Integer, Set<String>> map = animals.collect(
                Collectors.groupingBy(
                        s -> s.length(),
                        () -> new TreeMap<>(),
                        Collectors.toSet())
        );
        System.out.println(map);
    }

    @Test
    public void testGroupingBy2UsingMethodReference() {
        var animals = Stream.of("lions", "tigers", "bears");

        TreeMap<Integer, Set<String>> map = animals.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toSet())
        );
        System.out.println(map);
    }

    @Test
    public void testPartitioningBy() {
        var animals = Stream.of("lions", "tigers", "bears");

        Map<Boolean, Set<String>> map = animals.collect(
                Collectors.partitioningBy(
                        s -> s.length() <= 5,
                        Collectors.toSet())
        );
        System.out.println(map);
    }

    @Test
    public void testGroupingByMapping() {
        var animals = Stream.of("lions", "tigers", "bears");

        Map<Integer, Optional<Character>> map = animals.collect(
                Collectors.groupingBy(
                        s -> s.length(),
                        Collectors.mapping(
                                s -> s.charAt(0),
                                Collectors.minBy((s1, s2) -> s1 - s2)
                        ))
        );
        System.out.println(map);
    }

    @Test
    public void testGroupingByMappingWithMethodReference() {
        var animals = Stream.of("lions", "tigers", "bears");

        Map<Integer, Optional<Character>> map = animals.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                s -> s.charAt(0),
                                minBy((s1, s2) -> s1 - s2)
                        ))
        );
        System.out.println(map);
    }
}
