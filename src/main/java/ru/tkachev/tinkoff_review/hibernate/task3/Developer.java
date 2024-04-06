package ru.tkachev.tinkoff_review.hibernate.task3;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "developer")
@Entity(name = "developer")
public class Developer {

    @Id
    @Column(name = "developer_id", unique = true, nullable = false)
    private Long developerId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "developer", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
