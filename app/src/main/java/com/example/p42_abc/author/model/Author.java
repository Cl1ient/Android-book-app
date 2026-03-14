package com.example.p42_abc.author.model;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    // Méthode temporaire pour générer de faux auteurs pour tester l'interface
    public static List<Author> getFakeAuthors() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1, "Victor Hugo"));
        list.add(new Author(2, "J.K. Rowling"));
        list.add(new Author(3, "George Orwell"));
        list.add(new Author(4, "Agatha Christie"));
        return list;
    }
}
