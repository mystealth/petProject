package web.bibliography.dataflow;

public class Book {
    private String author;
    private String bookName;
    private String publishingHouse;
    private Long idOfBook;

    public Book() {

    }

    public Book(String author, String bookName, String publishingHouse, Long idOfBook) {
        this.author = author;
        this.bookName = bookName;
        this.publishingHouse = publishingHouse;
        this.idOfBook = idOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Long getIdOfBook() {
        return idOfBook;
    }

    public void setIdOfBook(Long idOfBook) {
        this.idOfBook = idOfBook;
    }
}
