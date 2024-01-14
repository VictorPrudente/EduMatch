package repository;

import entities.Matematica;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatematicaRepository implements Repositorio<Integer, Matematica> {


    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT VS_13_EQUIPE_9.SEQ_MATEMATICA.nextval AS mysequence from DUAL";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()){
                return rs.getInt("mysequence");
            }
            return null;
        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Matematica adicionar(Matematica questao) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            Integer nextid = this.getProximoId(con);
            questao.setId(nextid);

            String sql = "INSERT INTO VS_13_EQUIPE_9.MATEMATICA\n" +
                    "(id_matematica, pergunta, pontos, opcao_correta, dificuldade)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, questao.getId());
            ps.setString(2, questao.getQuestao());
            ps.setInt(3, questao.getPontos());
            ps.setString(4, questao.getOpcaoCerta());
            ps.setInt(5, questao.getDificuldade().ordinal());

            int res = ps.executeUpdate();
            System.out.println("Adicionada questão " + res);
            return questao;
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
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.MATEMATICA WHERE id_matematica = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int result = ps.executeUpdate();
            System.out.printf("Remover questão pelo id %d: " + result, id);

            return result > 0;
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
    public boolean editar(Integer id, Matematica matematica) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDadosLocal.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE VS_13_EQUIPE_9.MATEMATICA SET \n");
            sql.append(" questao = ?,");
            sql.append(" pontos = ?,");
            sql.append(" opcao_correta = ?,");
            sql.append(" dificuldade = ? ");
            sql.append(" WHERE id_matematica = ? ");
            //id, questao, pontos, opcaoCerta, Dificuldade
            PreparedStatement ps = con.prepareStatement(sql.toString());

            ps.setString(1, matematica.getQuestao());
            ps.setInt(2, matematica.getPontos());
            ps.setString(3, matematica.getOpcaoCerta());
            ps.setInt(4, matematica.getDificuldade().ordinal());

            int res = ps.executeUpdate();
            System.out.println("Editar questão: " + res);
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
    public List<Matematica> listar() throws BancoDeDadosException {
        List<Matematica> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();
            Statement ps = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.MATEMATICA";

            ResultSet res = ps.executeQuery(sql);

            while (res.next()){
                Matematica questao = new Matematica();
                questao.setId(res.getInt("id_matematica"));
                questao.setQuestao(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));

                questoes.add(questao);
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
    return questoes;
    }

    public Matematica listarPorDificuldade(int dificuldade) throws BancoDeDadosException {
        Matematica questao = new Matematica();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.MATEMATICA WHERE DIFICULDADE = ? ORDER BY DBMS_RANDOM.VALUE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, dificuldade);

            ResultSet res = ps.executeQuery();

            if (res.next()){
                questao.setId(res.getInt("id_matematica"));
                questao.setQuestao(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
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
        return questao;
    }
}

