package ru.tkachev.tinkoff_review.collections.task3;

import java.util.*;

public class Task3 {

    public static void run() {
        System.out.println("==============Map====================");
        System.out.println("---Insert elements (5kk)\n");

        int elementsCount = 5000000;

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        insertElements(hashMap, "HashMap", elementsCount);
        insertElements(treeMap, "TreeMap", elementsCount);
    }

    private static void insertElements(Map<Integer, Integer> map, String mapType, int elementsCount) {
        Date startMapTime = new Date();
        long startMapMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        for (int element = 0; element < elementsCount; element++) {
            map.put(element, element);
        }

        Date finishMapTime = new Date();
        long finishMapMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long listTime = finishMapTime.getTime() - startMapTime.getTime();
        long listMemory = (finishMapMemory - startMapMemory) / 1024 / 1024;

        System.out.println(mapType + " time: " + listTime + " ms");
        System.out.println(mapType + " memory: " + listMemory + " Mb\n");
    }
}
