package ru.tkachev.tinkoff_review.spring.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyB {

    @Autowired
    public CircularDependencyB(CircularDependencyA circularDependencyA) {
    }
}
