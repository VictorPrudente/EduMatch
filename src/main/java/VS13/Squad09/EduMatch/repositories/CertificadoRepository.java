package VS13.Squad09.EduMatch.repositories;
import VS13.Squad09.EduMatch.entities.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {

    @Query(value = """
                    SELECT ce.id_certificado, ce.trilha, ce.dificuldade, u.ID_USUARIO, u.NOME, u.SOBRENOME
                    FROM CERTIFICADO ce
                    INNER JOIN USUARIO u ON ce.ID_USUARIO = u.ID_USUARIO
                    WHERE ce.ID_USUARIO = 1
                    ORDER BY ce.CONCLUSAO
                       """, nativeQuery = true)
    Certificado listarUltimo(Integer idUsuario);

    @Query(value = "SELECT ce.trilha, ce.dificuldade, ce.conclusao, u.nome, u.sobrenome " +
                "FROM CERTIFICADO ce " +
                "INNER JOIN USUARIO u ON ce.ID_USUARIO = u.ID_USUARIO " +
                "WHERE u.ID_USUARIO= ?1", nativeQuery = true)
    List<Certificado> listarPorUsuario(Integer idUsuario);
}
