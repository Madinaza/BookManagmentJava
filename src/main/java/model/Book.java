package model;

public class Book {
    private String id;
    private String title;
    private String authorId;
    private String genre;
    private String status; // "To Read", "Reading", "Completed"
    private int rating; // 1–5
    private String review;
    private int yearPublished;

    public Book() {
    }

    public Book(String id, String title, String authorId, String genre, String status,
                int rating, String review, int yearPublished) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genre = genre;
        this.status = status;
        this.rating = rating;
        this.review = review;
        this.yearPublished = yearPublished;
    }

    // Getters and Setters
    // toString()

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorId='" + authorId + '\'' +
                ", genre='" + genre + '\'' +
                ", status='" + status + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
}