package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

    Page<Ranking> findAllByTitulo(String titulo, Pageable pageable);

    @Query(value = "SELECT * FROM RANKING r WHERE r.titulo = :titulo", nativeQuery = true)
    Ranking findByElo(@Param("titulo") String titulo);
}
