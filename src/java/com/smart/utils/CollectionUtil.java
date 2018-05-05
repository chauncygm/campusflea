package com.smart.utils;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.util.*;

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

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("1".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(listStr(list));
    }
}
