package ru.tkachev.tinkoff_review.hibernate.task1;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
@Entity(name = "message")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "message-only-entity-graph"),
        @NamedEntityGraph(name = "message-topic-entity-graph", attributeNodes = {@NamedAttributeNode("topic")})
})
public class Message {

    @Id
    private Long id;

    private String text;
    @ManyToOne
    private Topic topic;
}
