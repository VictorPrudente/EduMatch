package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Questao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {

    @Query(value = "SELECT * FROM QUESTAO q WHERE q.trilha = :trilha AND q.dificuldade = :dificuldade AND q.status = :status", nativeQuery = true)
    Page<Questao> pageTrilhaDificuldade(@Param("trilha") Integer trilha, @Param("dificuldade") Integer dificuldade, Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM QUESTAO q WHERE q.trilha = :trilha AND q.status = :status", nativeQuery = true)
    Page<Questao> pageTrilha(@Param("trilha") Integer trilha, Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM QUESTAO q WHERE q.status = :status", nativeQuery = true)
    Page<Questao> pageStatus(@Param("status") Integer status, Pageable pageable);


    @Query(value = "SELECT * FROM QUESTAO q", nativeQuery = true)
    Page<Questao> pageAll(Pageable pageable);



//    @Query(value = """
//            SELECT new VS13.Squad09.EduMatch.dtos.response.QuestaoDTO
//            (q.id, q.pontos, q.pergunta, q.opcoes, q.opcaoCerta, q.trilha, q.dificuldade, q.status)
//            FROM QUESTAO q
//            LEFT JOIN q.opcoes
//             WHERE
//            (:trilha IS NULL OR q.trilha = :trilha) AND
//            (:dificuldade IS NULL OR q.dificuldade = :dificuldade) AND
//            (:status IS NULL OR q.status = :status)""")
//    Page<QuestaoDTO> pageByTrilhaDificuldadeStatus(
//            @Param("trilha") Integer trilha,
//            @Param("dificuldade") Integer dificuldade,
//            @Param("status") Integer status,
//            Pageable pageable);


    @Query(value = """
                SELECT * FROM QUESTAO
                WHERE ROWNUM <= 5 AND TRILHA = :trilha AND DIFICULDADE = :dificuldade AND STATUS = 1
                ORDER BY DBMS_RANDOM.VALUE""", nativeQuery = true)
    List<Questao> prepareQuestoes(@Param("trilha") Integer trilha, @Param("dificuldade") Integer dificuldade);

}

