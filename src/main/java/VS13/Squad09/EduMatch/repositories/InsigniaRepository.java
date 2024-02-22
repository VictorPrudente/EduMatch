package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {

    Insignia findByTagIgnoreCase(String tag);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.response.InsigniaDTO
            (i.id, i.imagemUrl, i.titulo, i.descricao, i.tag, i.status)
            FROM INSIGNIA  i
            WHERE i.status = 1 AND i.id = :idInsignia
            """)
    List<InsigniaDTO> findInsignia(@Param("idInsignia") Integer idInsignia);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.response.InsigniaDTO
            (i.id, i.imagemUrl, i.titulo)
            FROM INSIGNIA i
            WHERE i.status = 1""")
    List<InsigniaDTO> findInsignias();


    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.response.InsigniaDTO
            (i.id, i.imagemUrl, i.titulo)
            FROM INSIGNIA i
            JOIN i.usuarios ui
            WHERE ui.idUsuario = :idUsuario""")
    List<InsigniaDTO> findAllByOwner(@Param("idUsuario") Integer idUsuario);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.response.InsigniaDTO
            (i.id, i.imagemUrl, i.titulo, i.descricao, i.tag, i.status)
            FROM INSIGNIA i
            JOIN i.usuarios ui
            WHERE ui.idUsuario = :idUsuario AND i.id = :idInsignia""")
    List<InsigniaDTO> findOneByOwner(@Param("idUsuario") Integer idUsuario, @Param("idInsignia") Integer idInsignia);
}
