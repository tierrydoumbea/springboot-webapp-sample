package managesys.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import configuration.JpaTestConfiguration;
import managesys.model.Book;
import managesys.model.Category;
import managesys.model.Format;
import managesys.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookService.class)
@ContextConfiguration(classes = JpaTestConfiguration.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    private Book[] books;
    private Category c;
    private Format f;

    @Before
    public void setup() {
        c = new Category("c1");
        c.save(bookRepository);
        f = new Format("f1");
        f.save(bookRepository);

        books = new Book[] { new Book("test1", "123-123-123-1", c, f),
                new Book("test2", "223-123-123-1", c, f),
                new Book("test3", "323-123-123-1", c, f),
                new Book("test4", "423-123-123-1", c, f),
                new Book("test5", "523-123-123-1", c, f),
                new Book("test6", "623-123-123-1", c, f),
                new Book("test7", "723-123-123-1", c, f) };
        saveBooks(books);
        bookRepository.flush();
    }

    private void saveBooks(Book... books) {
        for (Book b : books) {
            b.save(bookRepository);
        }
    }

    @Test
    @Transactional
    public void findAllBooks() {
        // 全件検索を行うと、1ページ分のデータだけが出力される。
        assertEquals(bookService.findAllBooks(PageRequest.of(0, 5)).getContent().size(), 5);
    }

    @Test
    @Transactional
    public void findById() {
        bookRepository.em().clear();

        // データを検索する。
        Book book = bookService.findById(books[1].getId());

        // 期待通りのデータが検出されたことを確認する。
        assertEquals(book.getTitle(), "test2");
        assertEquals(book.getIsbn(), "223-123-123-1");
        assertEquals(book.getCategory().getName(), "c1");
        assertEquals(book.getFormat().getName(), "f1");
    }

    @Test
    @Transactional
    public void saveBook() {
        // データを作成し保存する。
        Book b = new Book("test8", "823-123-123-1", c, f);

        bookService.saveBook(b);

        bookRepository.flush();

        // 保存したデータが存在することを確認する。
        Book book = Book.findByTitle(bookRepository, "test8", PageRequest.of(0, 5)).getContent().get(0);
        assertEquals(book.getTitle(), "test8");
        assertEquals(book.getIsbn(), "823-123-123-1");
        assertEquals(book.getCategory().getName(), "c1");
        assertEquals(book.getFormat().getName(), "f1");
    }

    @Test
    @Transactional
    public void updateBook() {
        // データを更新する。
        Book b = books[2];
        b.setTitle("test31");
        b.setIsbn("312-123-123-1");

        bookService.updateBook(b);

        bookRepository.flush();

        // データが更新されたことを確認する。
        Book book = Book.findById(bookRepository, books[2].getId());
        assertEquals(book.getTitle(), "test31");
        assertEquals(book.getIsbn(), "312-123-123-1");
        assertEquals(book.getCategory().getName(), "c1");
        assertEquals(book.getFormat().getName(), "f1");
    }

    @Test
    @Transactional
    public void deleteBook() {
        // データが存在することを確認する。
        assertNotNull(Book.findById(bookRepository, books[6].getId()));

        bookService.deleteBook(books[6]);

        bookRepository.flush();

        // データが存在しないことを確認する。
        assertNull(Book.findById(bookRepository, books[6].getId()));
    }

    @Test
    @Transactional
    public void findBookByIsbn() {
        // ISBNによる検索を行う。
        Book book = bookService.findBookByIsbn(books[3].getIsbn());

        // 取得したデータが期待通りであることを確認する。
        assertEquals(book.getTitle(), "test4");
        assertEquals(book.getIsbn(), "423-123-123-1");
        assertEquals(book.getCategory().getName(), "c1");
        assertEquals(book.getFormat().getName(), "f1");
    }

    @Test
    @Transactional
    public void findBookByTitle() {
        // タイトル検索を行うと、1ページ分のデータだけが出力される。
        assertEquals(bookService.findBookByTitle("test", PageRequest.of(0, 5)).getContent().size(), 5);
    }
}
