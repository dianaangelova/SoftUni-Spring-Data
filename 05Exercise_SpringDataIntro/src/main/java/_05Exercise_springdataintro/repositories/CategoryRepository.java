package _05Exercise_springdataintro.repositories;

import _05Exercise_springdataintro.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
