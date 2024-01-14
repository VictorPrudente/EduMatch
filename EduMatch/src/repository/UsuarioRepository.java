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
                System.out.println(id);
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
            con =ConexaoBancoDeDadosLocal.getConnection();

            Integer proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = """
                            INSERT INTO VS_13_EQUIPE_9.USUARIO
                            (id_usuario, nome, sobrenome, cpf, idade, pontuacao)
                            VALUES(?,?,?,?,?,?)
                            """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());
            stmt.setString(2,usuario.getNome());
            stmt.setString(3,usuario.getSobrenome());
            stmt.setString(4,usuario.getCPF());
            stmt.setInt(5,usuario.getIdade());
            stmt.setInt(6,usuario.getPontuacao());

            int res = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res= "+ res);
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
            con = ConexaoBancoDeDadosLocal.getConnection();
            String sql = "DELETE FROM USUARIO WHERE id_usuario = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int res = ps.executeUpdate();
            System.out.println("Remover pessoa por id res: " + res);

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
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = """
                    UPDATE USUARIO SET 
                    nome = ?, 
                    sobrenome = ?, 
                    idade = ?, 
                    pontuacao = ?, 
                    WHERE id_usuario = ?""";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setInt(3, usuario.getIdade());
            ps.setInt(4, usuario.getPontuacao());
            ps.setInt(5, usuario.getId());

            int res = ps.executeUpdate();
            System.out.println("Editar usuário res: " + res);

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
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "SELECT * FROM USUARIO WHERE ID = ? ";

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

    @Override
    public List<Usuario> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDadosLocal.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM USUARIO";

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
            con = ConexaoBancoDeDadosLocal.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.USUARIO ORDER BY pontuacao DESC";

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
}
