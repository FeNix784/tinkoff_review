package ru.tkachev.tinkoff_review.spring.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyA {

    @Autowired
    public CircularDependencyA(@Lazy CircularDependencyB circularDependencyB) {
    }
}
