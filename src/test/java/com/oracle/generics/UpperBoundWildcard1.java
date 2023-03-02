package com.oracle.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UpperBoundWildcard1 {

    @Test
    public void test() {


        // Accepts List<Bird> or List<Sparrow>
        List<? extends Bird> birds = new ArrayList<>();

//        java: incompatible types: com.oracle.generics.Bird cannot be converted to capture#1 of ? extends com.oracle.generics.Bird
//        java: incompatible types: com.oracle.generics.Sparrow cannot be converted to capture#2 of ? extends com.oracle.generics.Bird
//        birds.add(new Bird());
//        birds.add(new Sparrow());

        // Bird doesn't go in List<Sparrow>
        // Sparrow doesn't go in List<Bird>

    }
}

class Sparrow extends Bird {}
class Bird{}