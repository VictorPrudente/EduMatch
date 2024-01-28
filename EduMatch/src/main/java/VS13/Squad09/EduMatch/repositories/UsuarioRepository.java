package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Role;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UsuarioRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT SEQ_USUARIO.nextval AS mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()){
                int id = res.getInt("mysequence");
                return id;
            }

            return null;
        }
        catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            log.info("Conectado");

            Integer proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = """
                            INSERT INTO USUARIO
                            (id_usuario, email, senha, nome, sobrenome, cpf, cnpj, data_nascimento, pontuacao, tipo_documentacao, role, status, tipo_empresa)
                            VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
                            """;

            PreparedStatement stmt = con.prepareStatement(sql);
            log.info("SQL pronto");

            stmt.setInt(1, usuario.getId());
            log.info("id");
            stmt.setString(2, usuario.getEmail());
            log.info("email");
            stmt.setString(3, usuario.getSenha());
            log.info("senha");
            stmt.setString(4,usuario.getNome());
            log.info("nome");
            stmt.setString(5,usuario.getSobrenome());
            log.info("sobrenome");
            stmt.setString(6,usuario.getCPF());
            log.info("cpf");
            stmt.setString(7,usuario.getCNPJ());
            log.info("cnpj");
            Date dataParaSql = Date.valueOf(usuario.getDataNascimento());
            stmt.setDate(8, dataParaSql);
            log.info("Data de nascimento");
            stmt.setInt(9, usuario.getPontuacao());
            log.info("pontuação");
            stmt.setInt(10, usuario.getTipoUsuario().ordinal());
            log.info("tipo documento");
            stmt.setInt(11, usuario.getRole().ordinal());
            log.info("status");
            stmt.setInt(12, usuario.getStatus().ordinal());
            log.info("role");
            stmt.setInt(13, usuario.getTipoEmpresa().ordinal());

            stmt.executeUpdate();
            log.info("SQL executada");
            return usuario;

        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
        finally {
            try {
                if(con!=null){
                    con.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public List<Usuario> listarTodos() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();

        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM USUARIO";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                usuarios.add(querryUsuario(res));
            }
        } catch (SQLException e){
            log.error(e.getMessage());
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    public Usuario listarPorId(int id) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM USUARIO WHERE id_usuario = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet res = st.executeQuery();
            if (res.next()){
                return querryUsuario(res);
            }
            return null;
        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean atualizar(Integer id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                UPDATE USUARIO
                    SET 
                        email = ?,
                        senha = ?,
                        nome = ?, 
                        sobrenome = ?,
                        data_nascimento = ?, 
                        pontuacao = ?,
                        tipo_documentacao = ?,
                        role = ?,
                        status = ?
                    WHERE
                        id_usuario = ?""";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setString(4, usuario.getSobrenome());
            Date dataParaSql = Date.valueOf(usuario.getDataNascimento());
            ps.setDate(5, dataParaSql);
            ps.setInt(6, usuario.getPontuacao());
            ps.setInt(7, usuario.getTipoUsuario().ordinal());
            ps.setInt(8, usuario.getRole().ordinal());
            ps.setInt(9, usuario.getStatus().ordinal());
            ps.setInt(10, id);

            int res = ps.executeUpdate();

            return res > 0;
        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public Usuario listarPorEmail(String email) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM USUARIO WHERE email = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);

            ResultSet res = st.executeQuery();

            if (res.next()){
                return querryUsuario(res);
            }
            return null;
        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public List<Usuario> rankearJogadores() throws BancoDeDadosException{
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM USUARIO WHERE status = 1 ORDER BY pontuacao DESC";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                usuarios.add(querryUsuario(res));
            }
        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    private Usuario querryUsuario(ResultSet res) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(res.getInt("id_usuario"));
        usuario.setEmail(res.getString("email"));
        usuario.setSenha(res.getString("senha"));
        usuario.setNome(res.getString("nome"));
        usuario.setSobrenome(res.getString("sobrenome"));
        usuario.setCPF(res.getString("cpf"));
        usuario.setCNPJ(res.getString("cnpj"));
        Date dataParaSql = res.getDate("data_nascimento");
        usuario.setDataNascimento(dataParaSql.toLocalDate());
        usuario.setPontuacao(res.getInt("pontuacao"));
        usuario.setTipoUsuario(TipoUsuario.valueOf(res.getInt("tipo_documentacao")));
        usuario.setRole(Role.valueOf(res.getInt("role")));
        usuario.setStatus(Status.valueOf(res.getInt("status")));
        usuario.setTipoEmpresa(TipoEmpresa.valueOf(res.getInt("tipo_empresa")));
        return usuario;
    }
}
