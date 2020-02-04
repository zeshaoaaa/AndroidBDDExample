package org.jay.example;

public class Foo {

    public String foo(String text) {
        if (text == null) {
            return null;
        }

        if (!text.contains(",")) {
            return null;
        }

        int index = text.indexOf(",") ;

        String substring = text.substring(index);

        return substring;

    }

}
