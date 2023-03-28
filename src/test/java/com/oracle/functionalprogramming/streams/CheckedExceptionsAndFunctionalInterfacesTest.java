package com.oracle.functionalprogramming.streams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class CheckedExceptionsAndFunctionalInterfacesTest {

    @Test
    public void testGood() throws IOException {
        ExceptionCaseStudy.create().stream().count();
    }

    @Test
    public void testBad() throws IOException {
//        Supplier<List<String>> s = ExceptionCaseStudy::create ();  // Lambda expression doesn't handle checked exceptions
    }

    @Test
    public void testWrapper() {
        Supplier<List<String>> s = ExceptionCaseStudy::safe;
    }

}

class ExceptionCaseStudy {
    public static List<String> create() throws IOException {
        throw new IOException();
    }

    public static List<String> safe() {
        try {
            return create();
        } catch (IOException e ) {
            throw new RuntimeException(e);
        }
    }
}
