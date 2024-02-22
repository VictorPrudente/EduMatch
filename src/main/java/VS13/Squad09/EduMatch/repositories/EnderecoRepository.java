package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Optional<Endereco> findEnderecoByIdUsuario(Integer idUsuario);
}
