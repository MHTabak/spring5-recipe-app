package guru.springframework.repositories;

// Lecture 104
// Lecture 106 Add query method to get category by description

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>{

    // Spring 5 uses Optional<T> as the return type
    Optional<Category> findByDescription(String description);

}
