package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Endereco findEnderecoByUsuarioId(Integer idUsuario);
}
