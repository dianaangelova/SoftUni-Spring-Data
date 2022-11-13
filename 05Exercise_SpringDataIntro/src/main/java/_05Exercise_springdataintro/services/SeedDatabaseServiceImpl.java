package _05Exercise_springdataintro.services;

import _05Exercise_springdataintro.models.*;
import _05Exercise_springdataintro.repositories.AuthorRepository;
import _05Exercise_springdataintro.repositories.BookRepository;
import _05Exercise_springdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedDatabaseServiceImpl implements SeedDatabaseService {
    private static final String AUTHORS_FILE_PATH = "src\\main\\resources\\files\\authors.txt";
    private static final String CATEGORIES_FILE_PATH = "src\\main\\resources\\files\\categories.txt";
    private static final String BOOKS_FILE_PATH = "src\\main\\resources\\files\\books.txt";
    @Autowired // това е field injection, може да се ползва така, но е по-добре да се направи конструктор и той да бъде @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank()) // филтрирам и взимам всички, които не са празни
                .map(s -> s.split(" ")) // разделям всеки ред по интервал, което ми дава първо име и второ име
                .map(names -> new Author(names[0], names[1])) // създавам от всеки ред нов автор
                .forEach(author -> authorRepository.save(author)); // запаметявам в репозиторито
    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(line -> {
                    try {
                        return getBookObject(line);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(bookRepository::save);
    }

    private Book getBookObject(String line) throws IOException {

        String[] bookParts = line.split(" ");

        int editionTypeIndex = Integer.parseInt(bookParts[0]);
        EditionType editionType = EditionType.values()[editionTypeIndex];

        LocalDate publishDate =
                LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);

        int ageRestrictionIndex = Integer.parseInt(bookParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String title = Arrays.stream(bookParts)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, copies, publishDate,
                ageRestriction, author, categories);


    }
}
