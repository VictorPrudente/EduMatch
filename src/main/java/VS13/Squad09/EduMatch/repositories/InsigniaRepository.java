package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {

    Insignia findByTagIgnoreCase(String tag);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.response.InsigniaDTO
            (i.id, i.imagemUrl, i.titulo, i.descricao, i.tag, i.status)
            FROM INSIGNIA  i
            WHERE i.status = 1 AND i.id = :idInsignia
            """)
    InsigniaDTO findInsignia(@Param("idInsignia") Integer idInsignia);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.response.InsigniaDTO
            (i.id, i.imagemUrl, i.titulo)
            FROM INSIGNIA i
            WHERE i.status = 1""")
    List<InsigniaDTO> findInsignias();
}
