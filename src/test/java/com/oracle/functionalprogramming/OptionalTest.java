package com.oracle.functionalprogramming;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    public Optional<Double> average(int... scores) {
      if (scores.length == 0) return Optional.empty();
      int sum = 0;
      for(int score: scores) sum += score;
      return Optional.of((double) sum / scores.length);
    }


    @Test
    public void testWithOptional() {
        System.out.println(average(90, 100));
        System.out.println(average());

        Optional<Double> optional = average(90, 100);
        optional.ifPresent(System.out::println);
    }

    @Test
    public void testWithAnEmptyOptional() {
        Optional<Double> optional = average();
        System.out.println(optional.orElse(Double.NaN));
        System.out.println(optional.orElseGet(() -> Math.random()));

//        System.out.println(optional.orElseThrow());
//        System.out.println(optional.orElseThrow(() -> new IllegalStateException()));


    }
}
