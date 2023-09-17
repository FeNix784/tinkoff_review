package ru.tkachev.tinkoff_review.hibernate.task2;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.tkachev.tinkoff_review.hibernate.HibernateSessionFactoryUtil;

public class Task2 {

    public static void run() {
        fill();
        firstLevelCache();
        secondLevelCache();
        thirdLevelCache();
    }

    private static void fill() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Person person1 = new Person(1L, "Daniil");
        Person person2 = new Person(2L, "Tkachev");

        session.persist(person1);
        session.persist(person2);

        tx1.commit();
        session.close();
    }

    private static void firstLevelCache() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Person person = session.getReference(Person.class, 1L);
        System.out.println(person.getName());

        person = session.getReference(Person.class, 1L);
        System.out.println(person.getName());

        session.close();
    }

    private static void secondLevelCache() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Person person = session.getReference(Person.class, 1L);
        System.out.println(person.getName());

        session.close();

        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        person = session.getReference(Person.class, 1L);
        System.out.println(person.getName());

        session.close();
    }

    private static void thirdLevelCache() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Query<Person> query = session.createQuery("select p from person p where id=:id", Person.class);
        query.setCacheable(true);

        query.setParameter("id", 1L);
        Person person = query.getSingleResult();
        System.out.println(person.getName());

        query.setParameter("id", 1L);
        person = query.getSingleResult();
        System.out.println(person.getName());

        session.close();
    }
}
