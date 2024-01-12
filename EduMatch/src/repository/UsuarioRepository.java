package repository;

import entities.Usuario;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.List;

public class UsuarioRepository implements Repositorio<Integer, Usuario> {


    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT seq_contato.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()){
                return res.getInt("SEQ_USUARIO");
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
        return false;
    }

    @Override
    public boolean editar(Integer id, Usuario usuario) throws BancoDeDadosException {
        return false;
    }

    @Override
    public List<Usuario> listar() throws BancoDeDadosException {
        return null;
    }
}
