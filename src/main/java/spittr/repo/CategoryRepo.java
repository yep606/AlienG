package spittr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spittr.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
}
