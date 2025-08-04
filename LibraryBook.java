import java.util.*;

class Book {
    String title;
    String author;
    boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book.title + " by " + book.author + " - " +
                (book.isAvailable ? "Available" : "Checked Out"));
        }
    }

    public void checkoutBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.isAvailable) {
                book.isAvailable = false;
                System.out.println("Checked out: " + title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && !book.isAvailable) {
                book.isAvailable = true;
                System.out.println("Returned: " + title);
                return;
            }
        }
        System.out.println("Book not found or already returned.");
    }
}

// Example usage:
// Library lib = new Library();
// lib.addBook("1984", "George Orwell");
// lib.listBooks();
// lib.checkoutBook("1984");
// lib.returnBook("1984");

