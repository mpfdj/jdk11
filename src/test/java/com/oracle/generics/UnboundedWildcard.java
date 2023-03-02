package com.oracle.generics;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnboundedWildcard {

    @Test
    public void testWithUnboundedWildcard() {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(new Greeting("hello"));
        greetings.add(new Greeting("bye"));
        greetings.add(new Greeting("ciao"));
        printlist(greetings);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(100);
        numbers.add(200);
        numbers.add(300);
        printlist(numbers);
    }

    public void printlist(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

}

@AllArgsConstructor
@ToString
class Greeting {
    private String message;
}