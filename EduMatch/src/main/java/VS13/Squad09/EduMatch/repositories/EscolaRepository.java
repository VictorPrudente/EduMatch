package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Escola;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscolaRepository {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT VS_13_EQUIPE_9.seq_escola.nextval AS mysequence from DUAL";
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

    public Escola adicionar(Escola escola) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            escola.setId(proximoId);

            String sql = """
                            INSERT INTO VS_13_EQUIPE_9.ESCOLA
                            (id_escola, nome, tipo, cnpj)
                            VALUES(?,?,?,?)
                            """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,escola.getId());
            stmt.setString(2,escola.getNome());
            stmt.setInt(3,escola.getTipo().ordinal());
            stmt.setString(4,escola.getCnpj());

            int res = stmt.executeUpdate();
            return escola;

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

    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.ESCOLA WHERE id_escola = ?";

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

    public boolean editar(Integer id, Escola escola) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE VS_13_EQUIPE_9.ESCOLA SET ");
            sql.append(" nome = ?,");
            sql.append(" tipo = ? ");
            sql.append(" WHERE id_escola = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, escola.getNome());
            stmt.setInt(2, escola.getTipo().ordinal());
            stmt.setInt(3, id);

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

    public List<Escola> listar() throws BancoDeDadosException {
        List<Escola> escolas = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.ESCOLA";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Escola escola = new Escola();
                escola.setId(res.getInt("id_escola"));
                escola.setNome(res.getString("nome"));
                escola.setTipo(VS13.Squad9.EduMatch.entities.enums.TipoEscola.valueOf(res.getInt("tipo")));
                escola.setCnpj(res.getString("cnpj"));
                escolas.add(escola);
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
        return escolas;
    }

    public Escola listarPorId(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.ESCOLA WHERE ID_ESCOLA = ? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet res = st.executeQuery();

            if (res.next()) {
                Escola escola = new Escola();
                escola.setId(res.getInt("id_escola"));
                escola.setNome(res.getString("nome"));
                escola.setTipo(VS13.Squad9.EduMatch.entities.enums.TipoEscola.valueOf(res.getInt("tipo")));
                escola.setCnpj(res.getString("cnpj"));
                return escola;
            }
            return null;
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
}