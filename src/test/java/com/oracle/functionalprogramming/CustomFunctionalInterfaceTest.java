package com.oracle.functionalprogramming;

public class CustomFunctionalInterfaceTest {
}


// For example wheel speed on a tricycle
@FunctionalInterface
interface TriFunction<T,U, V, R> {
    R apply(T t, U u, V v);
}


// For example determine how fast your quad-copter is flying
@FunctionalInterface
interface QuadFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}