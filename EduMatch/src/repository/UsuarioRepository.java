package repository;

import entities.Contato;
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
            String sql = "SELECT SEQ_USUARIO.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()){
                return res.getInt("mysequence");
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
                            (id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa,id_escola)
                            VALUES(?,?,?,?,?,?,?,?)
                            """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());
            stmt.setString(2,usuario.getNome());
            stmt.setString(3,usuario.getSobrenome());
            stmt.setString(4,usuario.getCPF());
            stmt.setInt(5,usuario.getIdade());
            stmt.setInt(6,usuario.getPontuacao());
            stmt.setInt(6,usuario.getEmpresa());
            stmt.setInt(7, usuario.getEscola());

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

        try{
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.USUARIO WHERE ID_USUARIO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorID.res=" + res);

            return res > 0;

        }
        catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
        finally {
            try {
                if(con != null){
                    con.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean editar(Integer id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = """
                    UPDATE VS_13_EQUIPE_9.USUARIO SET
                    nome = ?,
                    sobrenome = ?,
                    cpf = ?,
                    idade = ?
                    WHERE id_usuario = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2,usuario.getSobrenome());
            stmt.setString(3, usuario.getCPF());
            stmt.setInt(4, usuario.getIdade());
            stmt.setInt(5,id);

            int res = stmt.executeUpdate();
            System.out.println("editarUsuario.res=" + res);

            return res > 0;
        }
        catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
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
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.USUARIO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNome(res.getString("nome"));
                usuario.setCPF(res.getString("cpf"));
                usuario.setIdade(res.getInt("idade"));
                usuario.setSobrenome(res.getString("sobrenome"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;


    }
}
