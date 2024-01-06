package ru.tkachev.tinkoff_review.multithreading.task2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2 {

    public static void run() throws ExecutionException, InterruptedException {
        System.out.println("\n==============Example1==================");
        example1();
        System.out.println("\n==============Example2==================");
        example2();
        System.out.println("\n==============Example3==================");
        example3();
        System.out.println("\n==============Example4==================");
        example4();
        System.out.println("\n==============Example5==================");
        example5();
        System.out.println("\n==============Example6==================");
        example6();
        System.out.println("\n==============Example7==================");
        example7();
    }

    private static void example1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("example1");
        });

        future.get();
    }

    private static void example2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "example2";
        });

        System.out.println(future.get());

        assertEquals("example2", future.get());
    }

    private static void example3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "example";
        });

        CompletableFuture<String> result = future.thenApply(example -> {
            System.out.println(Thread.currentThread().getName());
            return example + "3";
        });

        System.out.println(Thread.currentThread().getName() + " go further");
        System.out.println(Thread.currentThread().getName() + " " + result.get());

        assertEquals("example3", result.get());
    }

    private static void example4() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = getUser("user4")
                .thenAccept(user -> System.out.println("User id: " + user.id()));

        future.get();
    }

    private static void example5() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getUser("user5")
                .thenCompose(Task2::getCreditRating);

        System.out.println("User credit rating: " + future.get());

        assertEquals("creditRating", future.get());
    }

    private static CompletableFuture<User> getUser(String userId) {
        return CompletableFuture.supplyAsync(() -> UserService.getUser(userId));
    }

    private static CompletableFuture<String> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> UserService.getCreditRating(user));
    }

    private static void example6() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight");
        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 80.0;
        });

        System.out.println("Retrieving height");
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 185.0;
        });

        System.out.println("Calculating BMI");
        CompletableFuture<Double> combinedFuture = weightFuture.thenCombine(heightFuture, (weightInKg, heightInCm) -> {
            Double heightInMeter = heightInCm/100;
            return weightInKg/(heightInMeter*heightInMeter);
        });

        System.out.println("BMI: " + combinedFuture.get());
    }

    private static void example7() throws ExecutionException, InterruptedException {
        List<String> webPageLinks = List.of("page1", "page2", "page3", "page4", "page5", "page6", "page7", "page8", "page9", "page10");

        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(Task2::downloadWebPage)
                .toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[0])
        );

        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> pageContentFutures.stream()
                .map(CompletableFuture::join)
                .toList()
        );

        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> pageContents.stream()
                .filter(pageContent -> pageContent.contains("CompletableFuture"))
                .count()
        );

        System.out.println("Number of web pages with CompletableFuture: " + countFuture.get());

        assertEquals(10, countFuture.get());
    }

    private static CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> "CompletableFuture");
    }
}
