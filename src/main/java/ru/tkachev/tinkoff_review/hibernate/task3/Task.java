package ru.tkachev.tinkoff_review.hibernate.task3;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@Entity(name = "task")
public class Task {

    @Id
    @Column(name = "task_id", unique = true, nullable = false)
    private Long taskId;

    @JoinColumn(name = "developer_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Developer developer;

    private String name;

    @Override
    public String toString() {
        return String.format("Task %s develop by %s", name, developer.getName());
    }
}
