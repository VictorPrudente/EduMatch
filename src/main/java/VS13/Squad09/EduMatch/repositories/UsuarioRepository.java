package VS13.Squad09.EduMatch.repositories;
import VS13.Squad09.EduMatch.dtos.*;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM USUARIO u WHERE u.email = ?1", nativeQuery = true)
    Usuario listarPorEmail(String email);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO(
                u.idUsuario, u.nome, u.email)
                from USUARIO u
                where u.idUsuario = :idUsuario
            """)
    UsuarioCompletoRelatorioDTO procurarUsuarioCompletoDTO(@Param("idUsuario") Integer idUsuario);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.EnderecoRelatorioDTO(
              e.cep, e.cidade, e.estado, e.pais)
            FROM USUARIO u
            JOIN u.endereco e
            WHERE u.idUsuario = :idUsuario""")
    EnderecoRelatorioDTO procurarEnderecos(@Param("idUsuario") Integer idUsuario);


    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.ContatoRelatorioDTO(
                c.telefone)
            FROM USUARIO u
            JOIN u.contato c
            where u.idUsuario = :idUsuario""")
    ContatoRelatorioDTO procurarContatos(@Param("idUsuario") Integer idUsuario);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO(
                u.idUsuario,  u.nome,  u.email)
            FROM USUARIO u
             where u.idUsuario = :idUsuario
            """)
    UsuarioECertificadoRelatorioDTO procurarUsuarioECertificadoDTO(@Param("idUsuario") Integer idUsuario);

    @Query("""
            SELECT new VS13.Squad09.EduMatch.dtos.CertificadoRelatorioDTO(
            ce.id_certificado, ce.trilha, ce.conclusao, ce.dificuldade)
            FROM USUARIO u
            JOIN u.certificados ce
            where u.idUsuario = :idUsuario
            """)
    List<CertificadoRelatorioDTO> procurarCertificado(@Param("idUsuario") Integer idUsuario);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByEmail(String email);

    @Query("""
    SELECT new VS13.Squad09.EduMatch.dtos.response.UsuarioDTO
    (e.idUsuario, e.email, e.nome, e.CNPJ, e.fotoUrl)
    FROM USUARIO e WHERE e.status = 1 AND e.tipoUsuario = 2""")
    Set<UsuarioDTO> listarEmpresas();
}

