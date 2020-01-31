package org.jay.example;

import org.junit.Test;

public class ExampleTest {

    @Test
    public void testFoo() {
        Foo foo = new Foo();
        String result = foo.foo(null);
        assert (result == null);

        result = foo.foo("666,666");
        assert (result.equals("666"));

        result = foo.foo("666");
        assert (result == null);
    }

}
