package com.oracle.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UpperBoundWildcard {

    private void anyFlyer(List<Flyer> flyers) {}
    private void groupOfFlyers(List<? extends Flyer> flyers) {}
    // This is what we want, this method can only be called with the same type.
    // Geese fly together but don't fly with hang gliders.

    @Test
    public void test() {
        List<Flyer> flyers = new ArrayList<>();
        flyers.add(new HangGlider());
        flyers.add(new Goose());

        anyFlyer(flyers);
        groupOfFlyers(flyers);


        List<HangGlider> hangGliders = new ArrayList<>();
        hangGliders.add(new HangGlider());

//        anyFlyer(hangGliders);  // Doesn't compile
        groupOfFlyers(hangGliders);


        List<Goose> geese = new ArrayList<>();
        geese.add(new Goose());

//        anyFlyer(geese);  // Doesn't compile
        groupOfFlyers(geese);

    }

}


interface Flyer {
    void fly();
}

class HangGlider implements Flyer {
    public void fly() {
        System.out.println("HangGlider is flying");
    }
}

class Goose implements Flyer {
    public void fly() {
        System.out.println("Goose is flying");
    }
}