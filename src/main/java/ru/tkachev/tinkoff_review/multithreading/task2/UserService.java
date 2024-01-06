package ru.tkachev.tinkoff_review.multithreading.task2;

public class UserService {

    public static User getUser(String id) {
        return new User("user", "creditRating");
    }

    public static String getCreditRating(User user) {
        return user.creditRating();
    }
}
