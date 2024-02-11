package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

    Page<Ranking> findAllByTitulo(String titulo, Pageable pageable);

    @Query(value = "SELECT * FROM RANKING r WHERE r.titulo = :titulo AND r.STATUS = 1", nativeQuery = true)
    Ranking findByElo(@Param("titulo") String titulo);

    @Query("SELECT new VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO(rank.id, rank.titulo, rank.urlImagem, rank.descricao, rank.pontuacaoNecessaria) FROM RANKING rank WHERE rank.status = 1 ORDER BY rank.pontuacaoNecessaria asc")
    List<RankingDTO> findElos();

}
