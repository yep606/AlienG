package spittr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spittr.entity.Priority;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {
}
