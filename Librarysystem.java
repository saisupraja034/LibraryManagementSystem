import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getDetails() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable;
    }
}


class User {
    private int id;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}


class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private int bookIdCounter = 1;
    private int userIdCounter = 100;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public User getOrCreateUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        User newUser = new User(userIdCounter++, name);
        users.add(newUser);
        return newUser;
    }

    public void addBook(String title, String author) {
        Book book = new Book(bookIdCounter++, title, author);
        books.add(book);
        System.out.println("✅ Book added: " + book.getDetails());
    }

    public void issueBook(String title, String userName) {
        User user = getOrCreateUser(userName);
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                user.borrowBook(book);
                System.out.println("✅ " + user.getName() + " has borrowed \"" + title + "\".");
                return;
            }
        }
        System.out.println("❌ Book \"" + title + "\" is not available.");
    }

    public void returnBook(String title, String userName) {
        User user = getOrCreateUser(userName);
        for (Book book : user.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailable(true);
                user.returnBook(book);
                System.out.println("🔁 " + user.getName() + " has returned \"" + title + "\".");
                return;
            }
        }
        System.out.println("❌ Book not found in " + user.getName() + "'s borrowed list.");
    }

    public void searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("🔎 Book found: " + book.getDetails());
                return;
            }
        }
        System.out.println(" Book titled \"" + title + "\" not found.");
    }

    public void showAvailableBooks() {
        System.out.println("\n Available Books:");
        boolean anyAvailable = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getDetails());
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No books are currently available.");
        }
    }

    public void listAllBorrowedBooks() {
        System.out.println("\n All Borrowed Books:");
        boolean anyBorrowed = false;
        for (User user : users) {
            for (Book book : user.getBorrowedBooks()) {
                System.out.println(user.getName() + " borrowed: " + book.getTitle());
                anyBorrowed = true;
            }
        }
        if (!anyBorrowed) {
            System.out.println("No books are currently borrowed.");
        }
    }
}
public class Librarysystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int choice;


        do {
            System.out.println("Welcome To The Krishna's Library!");
            System.out.println(" ");
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. Show Available Books");
            System.out.println("6. List All Borrowed Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String addTitle = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String addAuthor = scanner.nextLine();
                    library.addBook(addTitle, addAuthor);
                    break;
                case 2:
                    System.out.print("Enter Book Title to Issue: ");
                    String issueTitle = scanner.nextLine();
                    System.out.print("Enter Your Name: ");
                    String issueUser = scanner.nextLine();
                    library.issueBook(issueTitle, issueUser);
                    break;
                case 3:
                    System.out.print("Enter Book Title to Return: ");
                    String returnTitle = scanner.nextLine();
                    System.out.print("Enter Your Name: ");
                    String returnUser = scanner.nextLine();
                    library.returnBook(returnTitle, returnUser);
                    break;
                case 4:
                    System.out.print("Enter Book Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchBook(searchTitle);
                    break;
                case 5:
                    library.showAvailableBooks();
                    break;
                case 6:
                    library.listAllBorrowedBooks();
                    break;
                case 0:
                    System.out.println("Thanks or visiting our library!");
                    break;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
