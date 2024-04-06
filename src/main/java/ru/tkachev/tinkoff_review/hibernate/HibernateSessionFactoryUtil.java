package ru.tkachev.tinkoff_review.hibernate;

import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.tkachev.tinkoff_review.hibernate.task1.Message;
import ru.tkachev.tinkoff_review.hibernate.task1.Topic;
import ru.tkachev.tinkoff_review.hibernate.task2.Person;
import ru.tkachev.tinkoff_review.hibernate.task3.Task;
import ru.tkachev.tinkoff_review.hibernate.task3.Developer;

@NoArgsConstructor
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Topic.class);
                configuration.addAnnotatedClass(Message.class);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Developer.class);
                configuration.addAnnotatedClass(Task.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return sessionFactory;
    }
}
