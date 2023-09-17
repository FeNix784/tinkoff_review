package ru.tkachev.tinkoff_review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tkachev.tinkoff_review.hibernate.task1.Task1;
import ru.tkachev.tinkoff_review.hibernate.task2.Task2;

@SpringBootApplication
public class TinkoffReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkoffReviewApplication.class, args);

        // Hibernate
        Task1.run();
        Task2.run();
    }
}
