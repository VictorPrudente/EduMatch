package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvaRepository extends JpaRepository<Prova, Integer> {
}
