package com.oracle.methodreference;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferenceTest {

    @Test
    public void testWithStaticMethod() {
        List<Integer> numbers = Arrays.asList(5, 4, 3, 2, 1);
        Consumer<List<Integer>> methodRef = Collections::sort;
        Consumer<List<Integer>> lambda = list -> Collections.sort(list);

        System.out.println(numbers);
        methodRef.accept(numbers);
        System.out.println(numbers);
    }

    @Test
    public void testWithInstanceMethodOnObject() {
        String str = "abcdef";
        Predicate<String> methodRef = str::startsWith;
        Predicate<String> lambda = s -> str.startsWith(s);

        System.out.println(methodRef.test("abc"));
        System.out.println(lambda.test("abc"));
    }

    @Test
    public void testWithInstanceMethodOnParameter() {
        Predicate<String> methodRef = String::isEmpty;
        Predicate<String> lambda = s -> s.isEmpty();

        System.out.println(methodRef.test(""));
        System.out.println(lambda.test(""));

        BiPredicate<String, String> methodRef2 = String::startsWith;
        BiPredicate<String, String> lambda2 = (s, t) -> s.startsWith(t);

        System.out.println(methodRef2.test("abcdef", "abc"));
        System.out.println(lambda2.test("abcdef", "abc"));
    }

    @Test
    public void testWithConstructor() throws Exception {
        Supplier<List<String>> methodRef = ArrayList::new;
        Supplier<List<String>> lambda = () -> new ArrayList<>();

        System.out.println(methodRef.get().getClass() + " " + methodRef.get().size());
        System.out.println(lambda.get().getClass() + " " + lambda.get().size());

        // Init ArrayList with capacity
        Function<Integer, List<String>> methodRef2 = ArrayList::new;
        Function<Integer, List<String>> lambda2 = i -> new ArrayList<>(i);

        List<String> list1 = methodRef2.apply(100);
        System.out.println(getDefaultCapacity((ArrayList<?>) list1));

        List<String> list2 = lambda2.apply(50);
        System.out.println(getDefaultCapacity((ArrayList<?>) list2));
    }

    @Test
    public void testWithDifferenceContext() {
        Supplier<Integer> methodRef1 = Penguin::countBabies;
        Supplier<Integer> lambda1 = () -> Penguin.countBabies();

        Function<Penguin, Integer> methodRef2 = Penguin::countBabies;
        Function<Penguin, Integer> lambda2 = p -> Penguin.countBabies(p);

        BiFunction<Penguin, Penguin, Integer> methodRef3 = Penguin::countBabies;
        BiFunction<Penguin, Penguin, Integer> lambda3 = (p1, p2) -> Penguin.countBabies(p1, p2);
    }


    // https://www.baeldung.com/java-list-capacity-array-size
    public int getDefaultCapacity(ArrayList<?> arrayList) throws Exception {
        if (arrayList == null) {
            return 0;
        }

        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);

        return ((Object[]) field.get(arrayList)).length;
    }
}

class Penguin {

    public static Integer countBabies(Penguin... cuties) {
        return cuties.length;
    }

}