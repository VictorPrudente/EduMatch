package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    Cargo findByNome(String nome);
}
