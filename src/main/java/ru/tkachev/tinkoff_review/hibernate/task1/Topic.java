package ru.tkachev.tinkoff_review.hibernate.task1;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic")
@Entity(name = "topic")
public class Topic {

    @Id
    private Long id;

    private String title;
}
