package service;

import model.Author;

import java.util.*;

public class AuthorService {
    private final Map<String, Author> authors = new HashMap<>();

    public void add(Author author) {
        authors.put(author.getId(), author);
    }

    public void delete(String id) {
        authors.remove(id);
    }

    public List<Author> getAll() {
        return new ArrayList<>(authors.values());
    }
}
