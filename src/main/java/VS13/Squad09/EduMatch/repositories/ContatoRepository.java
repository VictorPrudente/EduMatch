package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    Optional<Contato> findContatoByIdUsuario(Integer idUsuario);
}