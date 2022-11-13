package _05Exercise_springdataintro.services;

import _05Exercise_springdataintro.models.Author;
import _05Exercise_springdataintro.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        List<Author> authorsList = this.authorRepository.findAll();

        authorsList.sort(Comparator.comparing(Author::getId));

        Author firstAuthor = authorsList.stream().findFirst().get();

        Long firstAuthorID = firstAuthor.getId();
        Long lastAuthorID = firstAuthorID + size;

        Random rand = new Random();
        int randomAuthorID = (int) (rand.nextInt((int) (lastAuthorID-firstAuthorID)) + firstAuthorID);

        return this.authorRepository.findById(Long.valueOf(randomAuthorID)).get();
    }
}
