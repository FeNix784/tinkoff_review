package ru.tkachev.tinkoff_review.spring.task3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean("user1")
    public User getUser1() {
        return new User("user1");
    }

    @Bean("user2")
    public User getUser2() {
        return new User("user2");
    }
}
