package spittr.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spittr.entity.Stat;

@Repository
public interface StatRepo extends CrudRepository<Stat, Long> {
}
