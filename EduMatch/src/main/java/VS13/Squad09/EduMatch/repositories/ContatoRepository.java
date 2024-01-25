package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepository {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT VS_13_EQUIPE_9.SEQ_CONTATO.nextval AS mysequence from DUAL";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                int id = res.getInt("mysequence");
                return id;
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }


    public Contato adicionar(Integer idUsuario, Contato contato) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            contato.setId(proximoId);

            String sql = "INSERT INTO VS_13_EQUIPE_9.CONTATO\n" +
                    "(ID_CONTATO, DESCRICAO, TELEFONE, TIPO_CONTATO, ID_USUARIO) \n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, contato.getId());
            stmt.setString(2, contato.getDescricao());
            stmt.setString(3, contato.getTelefone());
            stmt.setInt(4, contato.getTipo().ordinal());
            stmt.setInt(5, idUsuario);

            int res = stmt.executeUpdate();
            return contato;

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
    }


    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.CONTATO WHERE id_contato = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();

            return res > 0;
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
    }


    public boolean editar(Integer id, Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = """
                    UPDATE VS_13_EQUIPE_9.CONTATO 
                        SET
                        descricao = ?,
                        telefone = ?,
                        tipo_contato = ?
                    WHERE id_contato = ? """;


            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, contato.getDescricao());
            stmt.setString(2, contato.getTelefone());
            stmt.setInt(3, contato.getTipo().ordinal());
            stmt.setInt(4, id);

            int res = stmt.executeUpdate();

            return res > 0;
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
    }


    public List<Contato> listar() throws BancoDeDadosException {
        List<Contato> contatos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.CONTATO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Contato contato = new Contato();
                contato.setId(res.getInt("id_contato"));
                contato.setDescricao(res.getString("descricao"));
                contato.setTipo(TipoDeContato.valueOf(res.getInt("tipo_contato")));
                contato.setIdUsuario(res.getInt("id_usuario"));
                contatos.add(contato);
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
        return contatos;
    }

    public Contato listarPorDono(int id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                SELECT c.id_contato, c.telefone, c.tipo_contato, c.descricao, c.id_usuario
                FROM VS_13_EQUIPE_9.CONTATO c
                WHERE c.id_usuario = ?""";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();
                    if (res.next()) {
                        Contato contato = new Contato();
                        contato.setId(res.getInt("id_contato"));
                        contato.setTelefone(res.getString("telefone"));
                        contato.setTipo(TipoDeContato.valueOf(res.getInt("tipo_contato")));
                        contato.setDescricao(res.getString("descricao"));
                        return contato;
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
        return null;
    }
}