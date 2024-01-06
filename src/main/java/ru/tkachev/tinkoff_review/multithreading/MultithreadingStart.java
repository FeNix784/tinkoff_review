package ru.tkachev.tinkoff_review.multithreading;

import ru.tkachev.tinkoff_review.multithreading.task1.Task1;
import ru.tkachev.tinkoff_review.multithreading.task2.Task2;

import java.util.concurrent.ExecutionException;

public class MultithreadingStart {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task1.run();
        Task2.run();
    }
}
