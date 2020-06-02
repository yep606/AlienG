package spittr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spittr.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
}
