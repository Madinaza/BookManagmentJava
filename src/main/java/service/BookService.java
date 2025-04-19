package service;

import model.Book;

import java.util.*;

public class BookService {
    private final Map<String, Book> books = new HashMap<>();

    public void add(Book book) {
        books.put(book.getId(), book);
    }

    public void delete(String id) {
        books.remove(id);
    }

    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }
    public Book getById(String id) {
        return books.get(id);
    }

    public boolean edit(String id, Book book) {
        if (!books.containsKey(id)) return false;
        books.put(id, book);
        return true;
    }

}
