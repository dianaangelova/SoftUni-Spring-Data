package _05Exercise_springdataintro.repositories;

import _05Exercise_springdataintro.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReleaseDateAfter(LocalDate year2000);

    int countByReleaseDateAfter(LocalDate year2000);
}
