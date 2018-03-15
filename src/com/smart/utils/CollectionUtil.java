package com.smart.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionUtil {

    private static final String ELEMENT_SEPARATOR = "，";

    public static String listStr(Collection collection) {
        StringBuilder builder = new StringBuilder("[");
        Iterator it = collection.iterator();
        while(it.hasNext()) {
            builder.append(it.next() + ELEMENT_SEPARATOR);
        }
        if (collection.size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(listStr(Arrays.asList(1,2,3,4,5)));
    }
}
