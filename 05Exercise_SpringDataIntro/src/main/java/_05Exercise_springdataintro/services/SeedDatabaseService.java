package _05Exercise_springdataintro.services;

import java.io.IOException;

public interface SeedDatabaseService {

    void seedAuthors() throws IOException;

    void seedBooks() throws IOException;

    void seedCategories() throws IOException;

    default void seedAll() throws IOException{
        seedAuthors();
        seedCategories();
        seedBooks();
    }
}
