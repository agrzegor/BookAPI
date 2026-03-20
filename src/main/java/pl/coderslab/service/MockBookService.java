package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MockBookService implements BookService {
    private List<Book> bookList;
    private static Long nextId = 10L;

    public MockBookService() {
        bookList = new ArrayList<>();

        bookList.add(new Book(1L, "9780747532743", "Harry Potter i Kamień Filozoficzny", "J.K. Rowling", "Bloomsbury", "Fantasy"));
        bookList.add(new Book(2L, "9780451524935", "Rok 1984", "George Orwell", "Secker & Warburg", "Dystopia"));
        bookList.add(new Book(3L, "9780140449136", "Zbrodnia i kara", "Fiodor Dostojewski", "Penguin Classics", "Powieść"));
        bookList.add(new Book(4L, "9780307474278", "Kod Leonarda da Vinci", "Dan Brown", "Doubleday", "Thriller"));
        bookList.add(new Book(5L, "9788375780635", "Wiedźmin: Ostatnie życzenie", "Andrzej Sapkowski", "SuperNOWA", "Fantasy"));
        bookList.add(new Book(6L, "9780061120084", "Zabić drozda", "Harper Lee", "J.B. Lippincott & Co.", "Powieść"));
        bookList.add(new Book(7L, "9780553380163", "Gra o tron", "George R.R. Martin", "Bantam Books", "Fantasy"));
        bookList.add(new Book(8L, "9780679783268", "Duma i uprzedzenie", "Jane Austen", "Modern Library", "Romans"));
        bookList.add(new Book(9L, "9781593279509", "Clean Code", "Robert C. Martin", "Prentice Hall", "Programowanie"));
        bookList.add(new Book(10L, "9780134685991", "Effective Java", "Joshua Bloch", "Addison-Wesley", "Programowanie"));

    }

    @Override
    public List<Book> getBooks() {
        return bookList;
    }

    @Override
    public Optional<Book> get(Long id) {
        Optional<Book> opt = bookList.stream()
                .filter(book -> Objects.equals(book.getId(), id))
                .findAny();
        return opt;
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        bookList.add(book);

    }

    @Override
    public void delete(Long id) {
        if (get(id).isPresent()){
            bookList.remove(this.get(id).get());
        }

    }

    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()){
            int indexOfBook = getBooks().indexOf(this.get(book.getId()).get());
            bookList.set(indexOfBook, book);
        }
    }
}
