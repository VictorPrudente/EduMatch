package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Prova;
import VS13.Squad09.EduMatch.entities.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProvaRepository extends JpaRepository<Prova, Integer> {

//    @Query(value = "SELECT * FROM PROVA_QUESTAO pq JOIN QUESTAO q ON pq.ID_PROVA_QUESTAO = q.ID_QUESTAO WHERE pq.ID_PROVA = :provaId", nativeQuery = true)
//    Prova receberQuestoes(@Param("provaId") Integer id);

}
