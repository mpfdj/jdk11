package com.oracle.exceptions;

import org.junit.jupiter.api.Test;

public class SuppressedExceptions {

    @Test
    public void testTurkeyCage() {
        try (var cage = new TurkeyCage()) {
            System.out.println("Try block");
        }
    }

    @Test
    public void testJammedTurkeyCage() {
        try (var jammedCage = new JammedTurkeyCage()) {
            System.out.println("Put turkey in");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    @Test
    public void testTurkeyRanOff() {
        try (var jammedCage = new JammedTurkeyCage()) {
            throw new IllegalStateException("Turkey ran off");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }

    @Test
    // Java remembers the suppressed exceptions that go with a primary exception even if we don't handle them in the code.
    public void testTurkeyRanOff2() {
        try(var jammedCage = new JammedTurkeyCage()) {
            throw new RuntimeException("Turkey ran off");  // The primary exception is a RuntimeException and is not being caught in this example
        } catch (IllegalStateException e) {                // The catch clause is only applicable to primary exceptions which is from a different type here
            System.out.println("Caught: " + e.getMessage());
        }
    }
}


class TurkeyCage implements AutoCloseable {
    public void close() {
        System.out.println("Close gate");
    }
}

class JammedTurkeyCage implements AutoCloseable {
    public void close() {
        throw new IllegalStateException("Cage door doesn't close");
    }
}