package org.jay.example;

import org.junit.Test;

public class ExampleTest {

    @Test
    public void test() {
        Foo example = new Foo();
        String result = example.foo(null);
        assert(result == null);
    }

}
