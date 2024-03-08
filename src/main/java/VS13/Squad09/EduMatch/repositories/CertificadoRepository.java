package VS13.Squad09.EduMatch.repositories;


import VS13.Squad09.EduMatch.entities.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {

}
