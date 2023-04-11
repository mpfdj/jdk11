package com.oracle.exceptions;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class TryWithResourcesTest {

    @Test
    public void test1() {
        try (var bookReader = new MyFileReader("monkey")) {
            System.out.println("Try block");
        } finally {
            System.out.println("Finally block");
        }
    }

    @Test
    public void test2() {
        try (var bookReader = new MyFileReader("1");
             var movieReader = new MyFileReader("2");
             var tvReader = new MyFileReader("3")) {
            System.out.println("Try block");
        } finally {
            System.out.println("Finally block");
        }
    }


    public void copyData(Path path1, Path path2) throws Exception {
        BufferedReader in = null;
        BufferedWriter out = null;

        try {
            in = Files.newBufferedReader(path1);
            out = Files.newBufferedWriter(path2);
            out.write(in.readLine());
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    public void copyData2(Path path1, Path path2) throws Exception {
        try (var in = Files.newBufferedReader(path1);
             var out = Files.newBufferedWriter(path2)) {
            out.write(in.readLine());
        }
    }
}

@AllArgsConstructor
class MyFileReader implements AutoCloseable {
    private String tag;

    // The close method is executed right after the last statement in the try block
    @Override
    public void close() {
        System.out.println("Closed: " + tag);
    }
}