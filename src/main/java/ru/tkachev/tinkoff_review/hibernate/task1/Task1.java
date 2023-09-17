package ru.tkachev.tinkoff_review.hibernate.task1;

import jakarta.persistence.EntityGraph;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tkachev.tinkoff_review.hibernate.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Map;

public class Task1 {

    public static void run() {
        fill();
        problem();
        withoutProblem();
        entityGraph();
    }

    private static void fill() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Topic topic1 = new Topic(1L, "topic1");
        Topic topic2 = new Topic(2L, "topic2");

        Message message1 = new Message(1L, "message1", topic1);
        Message message2 = new Message(2L, "message2", topic2);

        session.persist(topic1);
        session.persist(topic2);
        session.persist(message1);
        session.persist(message2);

        tx1.commit();
        session.close();
    }

    private static void problem() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        List<Message> messages = session.createQuery("select m from message m", Message.class).getResultList();

        session.close();
    }

    private static void withoutProblem() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        List<Message> messages = session.createQuery("select m from message m join fetch m.topic", Message.class).getResultList();

        session.close();
    }

    private static void entityGraph() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        EntityGraph<?> graph1 = session.getEntityGraph("message-only-entity-graph");
        Map<String, Object> properties1 = Map.of("javax.persistence.fetchgraph", graph1);

        EntityGraph<?> graph2 = session.getEntityGraph("message-topic-entity-graph");
        Map<String, Object> properties2 = Map.of("javax.persistence.fetchgraph", graph2);

        Message message1 = session.find(Message.class, 1L, properties1);
        Message message2 = session.find(Message.class, 2L, properties2);

        session.close();
    }
}
