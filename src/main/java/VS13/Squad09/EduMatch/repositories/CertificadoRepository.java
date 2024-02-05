package VS13.Squad09.EduMatch.repositories;
import VS13.Squad09.EduMatch.entities.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {

    @Query(value = "SELECT ce.id_certificado, ce.trilha, ce.dificuldade, ce.conclusao, u.nome, u.sobrenome " +
            "FROM ( " +
            "    SELECT ce.id_certificado, ce.trilha, ce.dificuldade, ce.conclusao, u.nome, u.sobrenome " +
            "    FROM VS_13_EQUIPE_9.CERTIFICADO ce " +
            "    INNER JOIN VS_13_EQUIPE_9.USUARIO u ON ce.ID_USUARIO = u.ID_USUARIO " +
            "    WHERE ce.ID_USUARIO = ?1 " +
            "    ORDER BY ce.conclusao DESC " +
            ") c " +
            "WHERE ROWNUM <= 1", nativeQuery = true)
    Certificado listarUltimo(Integer idUsuario);

    @Query(value = "SELECT c.trilha, c.dificuldade, c.conclusao, u.nome, u.sobrenome " +
                "FROM CERTIFICADO c " +
                "INNER JOIN USUARIO u ON c.ID_USUARIO = u.ID_USUARIO " +
                "WHERE c.idUsuario = ?1", nativeQuery = true)
    List<Certificado> listarPorUsuario(Integer idUsuario);
}
