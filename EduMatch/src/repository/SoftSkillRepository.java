package repository;

import entities.SoftSkill;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoftSkillRepository implements Repositorio<Integer, SoftSkill> {


    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT SEQ_SOFTSKILL.nextval AS mysequence from DUAL";
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
    public SoftSkill adicionar(SoftSkill questao) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            Integer nextid = this.getProximoId(con);
            questao.setId(nextid);

            String sql = "INSERT INTO SOFTSKILL\n" +
                    "(id_softskill, pergunta, pontos, opcao_correta, dificuldade)\n" +
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

            String sql = "DELETE FROM SOFTSKILL WHERE id_softskill = ?";

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
    public boolean editar(Integer id, SoftSkill softskill) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDadosLocal.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE SOFTSKILL SET \n");
            sql.append(" questao = ?,");
            sql.append(" pontos = ?,");
            sql.append(" opcao_correta = ?,");
            sql.append(" dificuldade = ? ");
            sql.append(" WHERE id_softskill = ? ");
            PreparedStatement ps = con.prepareStatement(sql.toString());

            ps.setString(1, softskill.getQuestao());
            ps.setInt(2, softskill.getPontos());
            ps.setString(3, softskill.getOpcaoCerta());
            ps.setInt(4, softskill.getDificuldade().ordinal());

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
    public List<SoftSkill> listar() throws BancoDeDadosException {
        List<SoftSkill> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();
            Statement ps = con.createStatement();

            String sql = "SELECT * FROM SOFTSKILL";

            ResultSet res = ps.executeQuery(sql);

            while (res.next()){
                SoftSkill questao = new SoftSkill();
                questao.setId(res.getInt("id_softskill"));
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

    public SoftSkill listarPorDificuldade(int dificuldade) throws BancoDeDadosException {
        SoftSkill questao = new SoftSkill();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.SOFTSKILL WHERE DIFICULDADE = ? ORDER BY DBMS_RANDOM.VALUE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, dificuldade);

            ResultSet res = ps.executeQuery();

            if (res.next()){
                questao.setId(res.getInt("id_softskill"));
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

