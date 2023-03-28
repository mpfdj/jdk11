package com.oracle.functionalprogramming.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreamsTest {

    @Test
    public void testAverage() {
        var stream = IntStream.of(1, 2, 3);
        OptionalDouble avg = stream.average();
        System.out.println(avg);
    }

    @Test
    public void testRange() {
        var stream = IntStream.rangeClosed(1, 10);
        stream.forEach(System.out::println);
    }

    // https://mkyong.com/java8/java-8-flatmap-example/
    @Test
    public void testMappingStreams() {
        var stream = Stream.of("pinguin", "fish");
        IntStream intStream1 = stream.mapToInt(s -> s.length());
        intStream1.forEach(System.out::println);

        var integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        IntStream intStream2 = integerList.stream()
                .flatMapToInt(i -> IntStream.of(i));

        intStream2.forEach(System.out::println);
    }

    @Test
    public void testOptional() {
        var stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average();
        optional.ifPresent(System.out::println);
        System.out.println(optional.getAsDouble());
        System.out.println(optional.orElseGet(() -> Double.NaN));
    }

    @Test
    public void testStatistics() {
        var stream = IntStream.rangeClosed(1, 10);
        IntSummaryStatistics stats = stream.summaryStatistics();

        System.out.println(stats.getMax() - stats.getMin());
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
        System.out.println(stats.getSum());
    }



}
