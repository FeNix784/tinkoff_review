package ru.tkachev.tinkoff_review.multithreading.task1;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {

    public static void run() {
        fixedThreadPool();
        cachedThreadPool();
        scheduledThreadPool();
        singleThreadExecutor();
    }

    private static void fixedThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        runTasks(executor);

        assertEquals(2, executor.getPoolSize());
        assertEquals(1, executor.getQueue().size());
    }

    private static void cachedThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        runTasks(executor);

        assertEquals(3, executor.getPoolSize());
        assertEquals(0, executor.getQueue().size());
    }

    private static void singleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        runTasks(executor);
    }

    private static void scheduledThreadPool() {
        CountDownLatch lock = new CountDownLatch(3);

        ScheduledExecutorService  executor = Executors.newScheduledThreadPool(5);

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);


        try {
            lock.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future.cancel(true);

        executor.shutdown();
    }

    private static void runTasks(ExecutorService executor) {
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.shutdown();
    }
}
