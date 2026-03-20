package pl.coderslab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.service.BookService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @ResponseBody
    public List<Book> allBooks() {
        return bookService.getBooks();
    }


    @PostMapping("")
    @ResponseBody
    public void addBook(@RequestBody Book book) {
      bookService.add(book);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable ("id") Long id){
        return bookService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book){
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void removeBook (@PathVariable ("id") Long id){
        bookService.delete(id);
    }
}