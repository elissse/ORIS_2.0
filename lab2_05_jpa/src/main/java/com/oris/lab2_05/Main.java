package com.oris.lab2_05;

import com.oris.lab2_05.model.*;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Author author = new Author();
            author.setName("Лев Толстой");
            author.setNationality("Россия");
            em.persist(author);

            Book book = new Book();
            book.setTitle("Война и мир");
            book.setIsbn("978-5-699-12014-5");
            book.setPublicationYear(1869);
            book.setAuthor(author);
            em.persist(book);

            Reader reader = new Reader();
            reader.setName("Иван Иванов");
            reader.setEmail("ivan@example.com");
            reader.setPhone("+79001234567");
            em.persist(reader);

            PrintedBook printed = new PrintedBook();
            printed.setTitle("Анна Каренина");
            printed.setAvailable(true);
            printed.setPages(864);
            printed.setPublisher("Эксмо");
            em.persist(printed);

            DigitalMedia digital = new DigitalMedia();
            digital.setTitle("Аудиокнига — Преступление и наказание");
            digital.setAvailable(true);
            digital.setFormat("MP3");
            digital.setSizeInMB(320.5);
            em.persist(digital);

            em.getTransaction().commit();

            System.out.println("Данные успешно сохранены в базу!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
