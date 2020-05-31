package spittr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spittr.entity.Category;
import spittr.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {

    List<Priority> findAllByOrderByIdAsc();
}
