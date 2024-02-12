package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO;
import VS13.Squad09.EduMatch.dtos.usuario.response.UsuarioMinDTO;
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

    //MELHORAR
    Page<Ranking> findAllByTitulo(String titulo, Pageable pageable);


    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO
            (rank.id, rank.titulo, rank.urlImagem, rank.descricao, rank.pontuacaoNecessaria, rank.status)
            FROM RANKING rank
            WHERE rank.status = 1
            AND (:titulo IS NULL OR rank.titulo = :titulo)
            ORDER BY rank.pontuacaoNecessaria DESC""")
    List<RankingDTO> findElo(@Param("titulo") String titulo);

    @Query("""
    SELECT new VS13.Squad09.EduMatch.dtos.usuario.response.UsuarioMinDTO
    (user.idUsuario, user.nome, user.sobrenome, user.pontuacao)
     FROM USUARIO user
     WHERE user.ranking.id = :idRanking AND user.tipoUsuario = 1 AND user.status = 1
     ORDER BY user.pontuacao DESC""")
    List<UsuarioMinDTO> getUsers(@Param("idRanking") Integer idRanking);

    @Query(value = """
    SELECT posicao FROM
        (SELECT u.id_usuario, RANK() OVER (ORDER BY u.pontuacao DESC, u.id_usuario) AS posicao
        FROM usuario u
        WHERE u.TIPO_USUARIO = 1 AND u.STATUS = 1 AND u.ID_RANKING IS NOT NULL) posicao
     WHERE id_usuario = :idUsuario""", nativeQuery = true)
    Integer posicaoJogador(@Param("idUsuario") Integer idUsuario);

    Ranking findByTitulo(String titulo);
}
