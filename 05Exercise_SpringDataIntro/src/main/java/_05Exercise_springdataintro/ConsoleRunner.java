package _05Exercise_springdataintro;

import _05Exercise_springdataintro.models.Author;
import _05Exercise_springdataintro.models.Book;
import _05Exercise_springdataintro.repositories.AuthorRepository;
import _05Exercise_springdataintro.repositories.BookRepository;
import _05Exercise_springdataintro.services.SeedDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private SeedDatabaseService seedService;

    @Autowired
    public ConsoleRunner(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            SeedDatabaseService seedService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.seedService = seedService;
    }

    private void _01_booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void _02_allAuthorsWithBookBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors.stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author ->
                        System.out.printf("%s %s -> %d%n",
                                author.getFirstName(),
                                author.getLastName(),
                                author.getBooks().size()
                        ));
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // this.seedService.seedAuthors();
        // this.seedService.seedCategories();
        // this.seedService.seedBooks();

        this.seedService.seedAll();
        System.out.println("Data from files inserted!");

        System.out.println("Result from task _01_booksAfter2000:");
        this._01_booksAfter2000();

        System.out.println("Result from task _02_allAuthorsWithBookBefore1990:");
        this._02_allAuthorsWithBookBefore1990();

        System.out.println("Result from task _03_allAuthorsOrderedByBookCount:");
        this._03_allAuthorsOrderedByBookCount();
    }
}
