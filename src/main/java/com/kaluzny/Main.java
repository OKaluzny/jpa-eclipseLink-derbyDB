package com.kaluzny;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {

    // 1-Creates an instance of book
    Book book = new Book("Thermodynamics", "A section of physics that studies the nature of energy", 10.5F, "1-84023-542-8", 233, false);
    Book book2 = new Book("Fast Cars", "The device and operating principle of a sports car", 12.5F, "1-84023-722-9", 354, true);

    // 2-Obtains an entity manager and a transaction
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    EntityManager em = emf.createEntityManager();

    // 3-Persists the book to the database
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(book);
    em.persist(book2);
    tx.commit();

    // 4-Executes the named query
    book = em.createNamedQuery("findBookThermodynamics", Book.class).getSingleResult();
    book2 = em.createNamedQuery("findBookCars", Book.class).getSingleResult();

    System.out.println("######### " + book.getDescription());
    System.out.println("######### " + book2.getDescription());

    // 5-Closes the entity manager and the factory
    em.close();
    emf.close();
  }
}