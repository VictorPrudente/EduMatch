package VS13.Squad09.EduMatch.repositories;
import VS13.Squad09.EduMatch.entities.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {

}
