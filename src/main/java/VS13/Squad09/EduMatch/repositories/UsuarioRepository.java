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
                            (id_usuario, email, senha, nome, tipo_usuario, cpf, sobrenome, data_nascimento, pontuacao, moedas, cnpj, tipo_empresa, role, status)
                            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                            """;

            PreparedStatement ps = con.prepareStatement(sql);
            log.info("SQL pronto");

            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4,usuario.getNome());
            ps.setInt(5, usuario.getTipoUsuario().ordinal());
            ps.setString(6,usuario.getCPF());
            ps.setString(7,usuario.getSobrenome());
            ps.setDate(8, Date.valueOf(usuario.getDataNascimento()));
            ps.setInt(9, usuario.getPontuacao());
            ps.setInt(10, usuario.getMoedas());
            ps.setString(11,usuario.getCNPJ());
            ps.setInt(12, usuario.getTipoEmpresa().ordinal());
            ps.setInt(13, usuario.getRole().ordinal());
            ps.setInt(14, usuario.getStatus().ordinal());

            ps.executeUpdate();
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


    public List<Usuario> listarPorStatus(Integer status) throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            log.info("ok banco");
            String sql = "SELECT * FROM USUARIO WHERE status = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, status);

            ResultSet res = st.executeQuery();

            while (res.next()){
                log.info("ok querry");
                usuarios.add(querryUsuario(res));
            }
            return usuarios;
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


    public Usuario listarPorId(Integer id) throws BancoDeDadosException {
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


    public Usuario listarPorEmail(String email) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            log.info("ok banco");
            String sql = "SELECT * FROM USUARIO WHERE email = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);

            ResultSet res = st.executeQuery();

            if (res.next()){
                log.info("ok querry");
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
                        tipo_usuario = ?,
                        sobrenome = ?,
                        data_nascimento = ?, 
                        pontuacao = ?,
                        moedas = ?,
                        tipo_empresa = ?,
                        role = ?,
                        status = ?
                    WHERE
                        id_usuario = ?""";
            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ps.setString(3,usuario.getNome());
            ps.setInt(4, usuario.getTipoUsuario().ordinal());
            ps.setString(5,usuario.getSobrenome());
            ps.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            ps.setInt(7, usuario.getPontuacao());
            ps.setInt(8, usuario.getMoedas());
            ps.setInt(9, usuario.getTipoEmpresa().ordinal());
            ps.setInt(10, usuario.getRole().ordinal());
            ps.setInt(11, usuario.getStatus().ordinal());
            ps.setInt(12, id);

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


    private Usuario querryUsuario(ResultSet res) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(res.getInt("id_usuario"));
        usuario.setEmail(res.getString("email"));
        usuario.setSenha(res.getString("senha"));
        usuario.setNome(res.getString("nome"));
        usuario.setTipoUsuario(TipoUsuario.valueOf(res.getInt("tipo_usuario")));
        usuario.setCPF(res.getString("cpf"));
        usuario.setSobrenome(res.getString("sobrenome"));
        usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
        usuario.setPontuacao(res.getInt("pontuacao"));
        usuario.setMoedas(res.getInt("moedas"));
        usuario.setCNPJ(res.getString("cnpj"));
        usuario.setTipoEmpresa(TipoEmpresa.valueOf(res.getInt("tipo_empresa")));
        usuario.setRole(Role.valueOf(res.getInt("role")));
        usuario.setStatus(Status.valueOf(res.getInt("status")));
        return usuario;
    }
}
