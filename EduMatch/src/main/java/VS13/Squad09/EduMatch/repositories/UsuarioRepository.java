package repository;

import entities.Usuario;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repositorio<Integer, Usuario> {


    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT VS_13_EQUIPE_9.SEQ_USUARIO.nextval AS mysequence from DUAL";
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


    @Override
    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;

        try {
            con =ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = """
                            INSERT INTO VS_13_EQUIPE_9.USUARIO
                            (id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao)
                            VALUES(?,?,?,?,?,?,?,?)
                            """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4,usuario.getNome());
            stmt.setString(5,usuario.getSobrenome());
            stmt.setString(6,usuario.getCPF());
            stmt.setInt(7,usuario.getIdade());
            stmt.setInt(8,usuario.getPontuacao());

            int res = stmt.executeUpdate();
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

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "DELETE FROM VS_13_EQUIPE_9.USUARIO WHERE id_usuario = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

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

    @Override
    public boolean editar(Integer id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = """
                    UPDATE VS_13_EQUIPE_9.USUARIO
                        SET 
                            nome = ?, 
                            sobrenome = ?, 
                            idade = ?, 
                            pontuacao = ? 
                        WHERE
                            id_usuario = ?""";
            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setInt(3, usuario.getIdade());
            ps.setInt(4, usuario.getPontuacao());
            ps.setInt(5, usuario.getId());

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

    public Usuario listarPorId(int id) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.USUARIO WHERE id_usuario = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet res = st.executeQuery();
            if (res.next()){
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));
                usuario.setCPF(res.getString("cpf"));
                usuario.setIdade(res.getInt("idade"));
                usuario.setPontuacao(res.getInt("pontuacao"));
                return usuario;
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
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.USUARIO WHERE email = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);

            ResultSet res = st.executeQuery();

            if (res.next()){
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));
                usuario.setCPF(res.getString("cpf"));
                usuario.setIdade(res.getInt("idade"));
                usuario.setPontuacao(res.getInt("pontuacao"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                return usuario;
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

    @Override
    public List<Usuario> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.USUARIO";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));
                usuario.setCPF(res.getString("cpf"));
                usuario.setIdade(res.getInt("idade"));
                usuario.setPontuacao(res.getInt("pontuacao"));

                usuarios.add(usuario);
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


    public List<Usuario> rankearJogadores() throws BancoDeDadosException{
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.USUARIO ORDER BY pontuacao DESC";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNome(res.getString("nome"));
                usuario.setIdade(res.getInt("idade"));
                usuario.setSobrenome(res.getString("sobrenome"));
                usuario.setPontuacao(res.getInt("pontuacao"));

                usuarios.add(usuario);
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
}
