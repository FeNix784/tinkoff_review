package ru.tkachev.tinkoff_review.collections.task2;

import java.util.*;
import java.util.function.Consumer;

public class Task2 {

    public static void run() {
        System.out.println("==============Begin==================");
        insertElementsBegin();

        System.out.println("==============Middle=================");
        insertElementsMiddle();

        System.out.println("==============End====================");
        insertElementsEnd();
    }

    private static void insertElementsBegin() {
        System.out.println("---Insert elements to begin (100k)\n"); // вставка элементов в начало списка

        int elementsCount = 100000; // сто тысяч элементов

        Consumer<List<Integer>> operationInsert = list -> {
            for (int element = 0; element < elementsCount; element++) {
                list.add(0, element);
            }
        };

        prepareInsert(operationInsert);
    }

    private static void insertElementsMiddle() {
        System.out.println("---Insert elements to middle (100k)\n"); // вставка элементов в середину списка

        int elementsCount = 100000; // сто тысяч элементов

        Consumer<List<Integer>> operationInsert = list -> {
            for (int element = 0; element < elementsCount; element++) {
                list.add(0, list.size() / 2);
            }
        };

        prepareInsert(operationInsert);
    }

    private static void insertElementsEnd() {
        System.out.println("---Insert elements to end (5kk)\n"); // вставка элементов в конец списка

        int elementsCount = 5000000; // пять миллионов элементов

        Consumer<List<Integer>> operationInsert = list -> {
            for (int element = 0; element < elementsCount; element++) {
                list.add(element);
            }
        };

        prepareInsert(operationInsert);
    }

    private static void prepareInsert(Consumer<List<Integer>> operationInsert) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        insertElements(arrayList, "ArrayList", operationInsert);
        insertElements(linkedList, "LinkedList", operationInsert);
    }

    private static void insertElements(List<Integer> list, String listType, Consumer<List<Integer>> operationInsert) {
        Date startListTime = new Date();
        long startListMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        operationInsert.accept(list);

        Date finishListTime = new Date();
        long finishListMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long listTime = finishListTime.getTime() - startListTime.getTime();
        long listMemory = (finishListMemory - startListMemory) / 1024 / 1024; // перевод в Мб

        System.out.println(listType + " time: " + listTime + " ms");
        System.out.println(listType + " memory: " + listMemory + " Mb\n");
    }
}
