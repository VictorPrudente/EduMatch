package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {

    ;
    @Query(value = "SELECT * FROM (SELECT * FROM QUESTAO ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM = 1 and TRILHA = :trilha AND DIFICULDADE = :dificuldade AND STATUS = 1", nativeQuery = true)
    Questao findByTrilhaAndDificuldade(@Param("trilha") Integer trilha, @Param("dificuldade") Integer dificuldade);

    @Query(value = "SELECT * FROM QUESTAO q WHERE q.trilha = :trilha AND q.dificuldade = :dificuldade AND q.status = 1 ORDER BY DBMS_RANDOM.VALUE", nativeQuery = true)
    List<Questao> findAllByTrilhaAndDificuldade(@Param("trilha") Integer trilha, @Param("dificuldade") Integer dificuldade);

    @Query(value = "SELECT * FROM QUESTAO q WHERE q.trilha = :trilha AND q.status = 1 ORDER BY q.dificuldade", nativeQuery = true)
    List<Questao> findAllByTrilhaOrderByDificuldade(@Param("trilha") Integer trilha);

    @Query(value = "SELECT * FROM QUESTAO q WHERE q.status = :status ORDER BY q.trilha, q.dificuldade", nativeQuery = true)
    List<Questao> findAllByStatus(@Param("status") Integer status);

}

