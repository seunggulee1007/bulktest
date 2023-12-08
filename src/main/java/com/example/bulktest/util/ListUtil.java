package com.example.bulktest.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    private ListUtil() {throw new AssertionError();}

    public static <T> List<List<T>> divideArrayList(List<T> list, int batchSize) {
        List<List<T>> dividedLists = new ArrayList<>();
        int size = list.size();

        for (int start = 0; start < size; start += batchSize) {
            int end = Math.min(start + batchSize, size);
            List<T> sublist = list.subList(start, end);
            dividedLists.add(new ArrayList<>(sublist));
        }

        return dividedLists;
    }

}
