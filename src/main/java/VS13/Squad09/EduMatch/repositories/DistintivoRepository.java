package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Distintivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistintivoRepository extends JpaRepository<Distintivo, Integer> {


    Page<Distintivo> findAll(Pageable page);
}
