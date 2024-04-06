package ru.tkachev.tinkoff_review.hibernate.task3;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tkachev.tinkoff_review.hibernate.HibernateSessionFactoryUtil;

import javax.transaction.Transactional;
import java.util.List;

public class Task3 {

    public void run() {
        fill();
        getDeveloper();
        saveTask();
    }

    private static void fill() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Developer developer = new Developer(1L, "Daniil", null);

        Task task = new Task(1L, developer, "ORM");

        developer.setTasks(List.of(task));

        session.persist(developer);

        tx1.commit();
        session.close();
    }

    private static void getDeveloper() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Developer developer = session.getReference(Developer.class, 1L);

        System.out.println(developer.getTasks().get(0).toString());

        session.close();
    }

    @Transactional
    void saveTask() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Developer developer = session.getReference(Developer.class, 1L);

        Task task = new Task(1L, developer, "Security");

        session.persist(task);

        session.close();
    }
}
