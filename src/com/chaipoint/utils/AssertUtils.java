package com.chaipoint.utils;

import java.util.List;
import java.util.stream.Collectors;

public class AssertUtils {
    public static <T> void assertEquals(T expected, T found, String testName) {
        if(!found.equals(expected)) {
            System.out.println(String.format("Test `%s` Failed ! expected - {%s}, found - {%s}", testName, expected, found));
        } else {
            System.out.println("Test `" + testName + "` Passed!");
        }
    }

    public static <T> void assertEquals(List<T> expected, List<T> found, String testName) {
        String expectedString = expected.stream().map(t -> t.toString()).collect(Collectors.joining(", ", "[", "]"));
        String foundString = found.stream().map(t -> t.toString()).collect(Collectors.joining(", ", "[", "]"));

        assertEquals(expectedString, foundString, testName);
    }
}
