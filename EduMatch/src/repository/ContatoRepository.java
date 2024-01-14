package repository;

import entities.Contato;
import entities.enums.TipoDeContato;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository implements Repositorio<Integer, Contato> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_CONTATO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public Contato adicionar(Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            contato.setId_contato(proximoId);

            String sql = "INSERT INTO CONTATO\n" +
                    "(ID_CONTATO, DESCRICAO, TELEFONE, TIPO_CONTATO, ID_USUARIO, ID_EMPRESA, ID_ESCOLA)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, contato.getId_contato());
            stmt.setString(2, contato.getDescricao());
            stmt.setString(3, contato.getTelefone());
            stmt.setInt(4, contato.getTipo().ordinal());
            stmt.setInt(1, contato.getId_usuario());
            stmt.setInt(1, contato.getId_empresa());
            stmt.setInt(1, contato.getId_escola());

            int res = stmt.executeUpdate();
            System.out.println("adicionarContato.res=" + res);
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

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM CONTATO WHERE id_contato = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorId.res=" + res);

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

    @Override
    public boolean editar(Integer id, Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CONTATO SET ");
            sql.append(" descricao = ?,");
            sql.append(" telefone = ? ");
            sql.append(" tipo_contato = ? ");
            sql.append(" WHERE id_contato = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, contato.getDescricao());
            stmt.setString(2, contato.getTelefone());
            stmt.setInt(3, contato.getTipo().ordinal());
            stmt.setInt(4, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarContato.res=" + res);

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

    @Override
    public List<Contato> listar() throws BancoDeDadosException {
        List<Contato> contatos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CONTATO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Contato contato = new Contato();
                contato.setId_contato(res.getInt("id_contato"));
                contato.setDescricao(res.getString("descricao"));
                contato.setTipo(TipoDeContato.valueOf(res.getInt("tipo_contato")));
                contato.setId_usuario(res.getInt("id_usuario"));
                contato.setId_empresa(res.getInt("id_empresa"));
                contato.setId_escola(res.getInt("id_escola"));
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
}