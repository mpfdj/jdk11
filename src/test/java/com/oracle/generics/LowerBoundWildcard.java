package com.oracle.generics;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LowerBoundWildcard {

    @Test
    public void test() {

        // Lower-bounds can get tricky

        // List<IOException>
        // List<Exception>
        // List<Object>
        List<? super IOException> exceptions = new ArrayList<Exception>();
//        exceptions.add(new Exception());
        exceptions.add(new IOException());
        exceptions.add(new FileNotFoundException());  // What happens is that Java says, FileNotFoundException also happens to be an IOException so everything is fine.


    }

}
