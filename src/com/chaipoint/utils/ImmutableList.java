package com.chaipoint.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableList {
    @SafeVarargs
    public static <T> List<T> of(T... elements) {
        ArrayList<T> arrayList = new ArrayList<>();
        for(T element : elements) {
            arrayList.add(element);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
