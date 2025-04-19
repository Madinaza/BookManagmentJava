package controller;

import model.Book;
import service.BookService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BookController {
    private final BookService bookService = new BookService();

    public void addBook(Book book) {
        bookService.add(book);
        System.out.println("Book added.");
    }

    public void deleteBook(String id) {
        bookService.delete(id);
    }

    public void listBooks() {
        bookService.getAll().forEach(System.out::println);
    }

    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    public BookService getService() {
        return bookService;
    }

    public Book getBookById(String id) {
        return getService().getById(id);
    }

    public void updateBook(String id, Book updatedBook) {
        getService().edit(id, updatedBook);
    }


    public void exportBooksToTxt(String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Book b : bookService.getAll()) {
                writer.println(b);
            }
        } catch (IOException e) {
            System.out.println("Error exporting books: " + e.getMessage());
        }
    }


}
