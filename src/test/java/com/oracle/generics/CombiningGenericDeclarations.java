package com.oracle.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CombiningGenericDeclarations {

    @Test
    public void test() {
        List<?> list1 = new ArrayList<A>();
        List<? extends A> list2 = new ArrayList<A>();
        List<? super A> list3 = new ArrayList<A>();

//        List<? extends B> list4 = new ArrayList<A>();
        List<? super B> list5 = new ArrayList<A>();
//        List<?> list6 = new ArrayList<? extends A>();
    }

}

class A {}
class B extends A {}
class C extends B {}
