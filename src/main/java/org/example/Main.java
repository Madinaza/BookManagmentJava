package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import controller.AuthorController;
import controller.BookController;
import model.Author;
import model.Book;
import util.JsonUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    static final String BOOKS_FILE = "src/main/resources/book.json";
    static final String AUTHORS_FILE = "src/main/resources/authors.json";

    public static void main(String[] args) {
        BookController bookCtrl = new BookController();
        AuthorController authorCtrl = new AuthorController();

        List<Book> books = JsonUtil.load(BOOKS_FILE, new TypeReference<List<Book>>() {});
        if (books != null) books.forEach(bookCtrl::addBook);

        List<Author> authors = JsonUtil.load(AUTHORS_FILE, new TypeReference<List<Author>>() {});
        if (authors != null) authors.forEach(authorCtrl::addAuthor);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:\n" +
                    "1. View All Books\n" +
                    "2. Add Book\n" +
                    "3. Delete Book\n" +
                    "4. View All Authors\n" +
                    "5. Add Author\n" +
                    "6. Delete Author\n" +
                    "7. Search Book by Title\n" +
                    "8. Filter Books by Status\n" +
                    "9. Show Stats (Books Read, Avg. Rating, etc.)\n" +
                    "10. Edit a Book\n" +
                    "11. Export All Books to TXT\n" +
                    "12. Exit");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    bookCtrl.listBooks();
                    break;

                case "2":
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author ID: ");
                    String aid = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Status (To Read / Reading / Completed): ");
                    String status = sc.nextLine();
                    System.out.print("Rating (1–5): ");
                    int rating = Integer.parseInt(sc.nextLine());
                    System.out.print("Review: ");
                    String review = sc.nextLine();
                    System.out.print("Year: ");
                    int year = Integer.parseInt(sc.nextLine());
                    bookCtrl.addBook(new Book(id, title, aid, genre, status, rating, review, year));
                    break;

                case "3":
                    System.out.print("Enter Book ID to delete: ");
                    bookCtrl.deleteBook(sc.nextLine());
                    break;

                case "4":
                    authorCtrl.listAuthors();
                    break;

                case "5":
                    System.out.print("Author ID: ");
                    String authorId = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Country: ");
                    String country = sc.nextLine();
                    authorCtrl.addAuthor(new Author(authorId, name, country));
                    break;

                case "6":
                    System.out.print("Enter Author ID to delete: ");
                    authorCtrl.deleteAuthor(sc.nextLine());
                    break;

                case "7":
                    System.out.print("Enter book title to search: ");
                    String keyword = sc.nextLine().trim().toLowerCase();
                    List<Book> booksFound = bookCtrl.getAllBooks();
                    boolean foundAny = false;

                    for (Book b : booksFound) {
                        if (b.getTitle().toLowerCase().contains(keyword)) {
                            System.out.println(b);
                            foundAny = true;
                        }
                    }

                    if (!foundAny) {
                        System.out.println("No books found with that title.");
                    }
                    break;



                case "8":
                    System.out.print("Enter status to filter (To Read / Reading / Completed): ");
                    String filterStatus = sc.nextLine().trim();
                    List<Book> allBooks = bookCtrl.getAllBooks();
                    boolean anyMatch = false;

                    for (Book b : allBooks) {
                        if (b.getStatus().equalsIgnoreCase(filterStatus)) {
                            System.out.println(b);
                            anyMatch = true;
                        }
                    }

                    if (!anyMatch) {
                        System.out.println("No books with that status.");
                    }
                    break;

                case "9":
                    List<Book> statsBooks = bookCtrl.getAllBooks();
                    int total = statsBooks.size();
                    int completed = 0;
                    int reading = 0;
                    int toRead = 0;
                    int ratingSum = 0;
                    int ratedCount = 0;

                    for (Book b : statsBooks) {
                        if ("Completed".equalsIgnoreCase(b.getStatus())) completed++;
                        if ("Reading".equalsIgnoreCase(b.getStatus())) reading++;
                        if ("To Read".equalsIgnoreCase(b.getStatus())) toRead++;
                        if (b.getRating() > 0) {
                            ratingSum += b.getRating();
                            ratedCount++;
                        }
                    }

                    double avgRating = ratedCount > 0 ? (double) ratingSum / ratedCount : 0;

                    System.out.println("Total Books: " + total);
                    System.out.println("Completed: " + completed);
                    System.out.println("Reading: " + reading);
                    System.out.println("To Read: " + toRead);
                    System.out.printf("Average Rating: %.2f\n", avgRating);
                    break;

                case "10":
                    System.out.print("Enter Book ID to edit: ");
                    String editId = sc.nextLine();
                    Book bookToEdit = bookCtrl.getBookById(editId);

                    if (bookToEdit != null) {
                        System.out.println("Editing: " + bookToEdit);
                        System.out.print("New status: ");
                        bookToEdit.setStatus(sc.nextLine());
                        System.out.print("New rating (1–5): ");
                        bookToEdit.setRating(Integer.parseInt(sc.nextLine()));
                        System.out.print("New review: ");
                        bookToEdit.setReview(sc.nextLine());

                        bookCtrl.updateBook(editId, bookToEdit);
                        System.out.println("Book updated.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case "11":
                    bookCtrl.exportBooksToTxt("books_export.txt");
                    System.out.println("Books exported to books_export.txt");
                    break;

                case "12":
                    running = false;
                    JsonUtil.save(BOOKS_FILE, bookCtrl.getAllBooks());
                    JsonUtil.save(AUTHORS_FILE, authorCtrl.getService().getAll());
                    System.out.println("Data saved. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

    }
}
