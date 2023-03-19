package com.oracle.functionalprogramming;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.function.Supplier;

public class SupplierTest {

    @Test
    public void testSupplier1() {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();

        System.out.println(s1.get());
        System.out.println(s2.get());
    }

    @Test
    public void testSupplier2() {
        Supplier<StringBuilder> s1 = StringBuilder::new;
        Supplier<StringBuilder> s2 = () -> new StringBuilder();

        StringBuilder sb1 = s1.get().append("hello");
        System.out.println(sb1);

        StringBuilder sb2 = s2.get().append("bye");
        System.out.println(sb2);
    }


}
