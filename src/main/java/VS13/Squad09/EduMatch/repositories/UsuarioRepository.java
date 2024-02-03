package VS13.Squad09.EduMatch.repositories;
import VS13.Squad09.EduMatch.dtos.ContatoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.EnderecoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM VEM_SER.PESSOA p WHERE p.email = ?1", nativeQuery = true)
    Usuario listarPorEmail(String email);

//    @Query(value = "SELECT * FROM VEM_SER.PESSOA p ORDER BY p.pontuacao DESC")
//    List <Usuario> rankearJogadores();

//    @Query(
//            """
//                    SELECT new VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO(
//                        u.id_usuario,  p.nome,  p.email)
//                    FROM USUARIO u
//                    WHERE u.idUsuario IS NULL OR u.idUsuario = ?1)
//                    """
//    )
//    List<UsuarioCompletoRelatorioDTO> procurarUsuarioCompletoDTO(Integer idUsuario);

//    @Query("""
//            SELECT new VS13.Squad09.EduMatch.dtos.EnderecoRelatorioDTO(
//              e.cep, e.cidade, e.estado, e.pais)
//            FROM usuario u
//            JOIN u.enderecos e
//            WHERE u.idUsuario = ?1 """)
//    List<EnderecoRelatorioDTO> procurarEnderecos(Integer idUsuario);
//
//
//    @Query("""
//            SELECT new VS13.Squad09.EduMatch.dtos.ContatoRelatorioDTO(
//                c.numero)
//            FROM USUARIO u
//            JOIN u.contatos c
//            WHERE c.usuario.idUsuario = ?1 """)
//    List<ContatoRelatorioDTO> procurarContatos(Integer idUsuario);
}
