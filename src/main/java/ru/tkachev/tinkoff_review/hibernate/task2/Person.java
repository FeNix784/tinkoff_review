package ru.tkachev.tinkoff_review.hibernate.task2;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

@Getter
@Setter
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@Entity(name = "person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person {

    @Id
    private Long id;

    private String name;
}
