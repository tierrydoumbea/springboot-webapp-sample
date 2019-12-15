package managesys.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import managesys.model.Book;
import managesys.model.Category;
import managesys.model.Format;
import managesys.report.AllListPdfReporter;
import managesys.repository.BookRepository;

@Service("bookLogic")
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    protected ResourceLoader resourceLoader;

    @Transactional
    public void saveBook(Book book) {
        Optional<Category> category = Category.findById(bookRepository, book.getCategory().getId());
        Optional<Format> format = Format.findById(bookRepository, book.getFormat().getId());

        if (category.isPresent() && format.isPresent()) {
            book.setCategory(category.get());
            book.setFormat(format.get());
            book.save(bookRepository);
        }
    }

    @Transactional
    public void updateBook(Book book) {
        Optional<Book> optBook = Book.findById(bookRepository, book.getId());

        if (optBook.isPresent()) {
            Book entity = optBook.get();
            entity.setTitle(book.getTitle());
            entity.setIsbn(book.getIsbn());

            Optional<Category> category = Category.findById(bookRepository, book.getCategory().getId());
            Optional<Format> format = Format.findById(bookRepository, book.getFormat().getId());

            if (category.isPresent() && format.isPresent()) {
                entity.setCategory(category.get());
                entity.setFormat(format.get());
                entity.save(bookRepository);
            }
        }
    }

    @Transactional
    public void deleteBook(Book book) {
        Optional<Book> optBook = Book.findById(bookRepository, book.getId());
        if (optBook.isPresent()) {
            book.delete(bookRepository);
        }
    }

    @Transactional
    public Page<Book> findAllBooks(Pageable pageable) {
        return Book.findAll(bookRepository, pageable);
    }

    @Transactional
    public Book findBookByIsbn(String isbn) {
        return Book.findByIsbn(bookRepository, isbn);
    }

    @Transactional
    public Page<Book> findBookByTitle(String keyword, Pageable pageable) {
        return Book.findByTitle(bookRepository, keyword, pageable);
    }

    @Transactional
    public byte[] exportAllListPdfReport() throws IOException {
        AllListPdfReporter builder = new AllListPdfReporter();
        return builder.makeReport(Book.findAll(bookRepository), resourceLoader.getResource("classpath:ipag.ttf").getURL().openStream());
    }
}
