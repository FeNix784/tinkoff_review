package ru.tkachev.tinkoff_review.spring.task3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final User user1;
    private final User user2;

    @Autowired
    public UserService(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.printf("Бин с именем %s был успешно заинжектен\n", user1.name());
        System.out.printf("Бин с именем %s был успешно заинжектен\n", user2.name());
    }
}
