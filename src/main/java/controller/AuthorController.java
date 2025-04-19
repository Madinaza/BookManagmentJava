package controller;

import model.Author;
import service.AuthorService;

import java.util.List;

public class AuthorController {
    private final AuthorService authorService = new AuthorService();

    public void addAuthor(Author author) {
        authorService.add(author);
        System.out.println("Author added.");
    }

    public void deleteAuthor(String id) {
        authorService.delete(id);
    }

    public void listAuthors() {
        authorService.getAll().forEach(System.out::println);
    }

    public AuthorService getService() {
        return authorService;
    }
}
