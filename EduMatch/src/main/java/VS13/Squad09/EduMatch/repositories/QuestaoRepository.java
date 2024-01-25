package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class QuestaoRepository{


    public Integer getNextId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT VS_13_EQUIPE_9.SEQ_QUESTAO.nextval AS mysequence from DUAL";
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


    public Questao create(Questao questao) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer nextid = this.getNextId(con);
            questao.setId(nextid);

            String sql = """
                    INSERT INTO VS_13_EQUIPE_9.QUESTAO
                    (id, pergunta, pontos, opcao_correta, dificuldade, trilha)
                    VALUES(?, ?, ?, ?, ?, ?)""";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, questao.getId());
            ps.setString(2, questao.getPergunta());
            ps.setInt(3, questao.getPontos());
            ps.setString(4, questao.getOpcaoCerta());
            ps.setInt(5, questao.getDificuldade().ordinal());
            ps.setInt(6, questao.getTrilha().ordinal());

            ps.executeUpdate();

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


    public String delete(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.QUESTAO WHERE id_questao = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            return (result > 0) ? "Questão deletada com sucesso" : "Questão não deletado";

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


    public Questao update(Integer id, Questao questao) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            String sql = """
             UPDATE VS_13_EQUIPE_9.QUESTAO SET
             pergunta = ?,
             pontos = ?,
             opcao_correta = ?,
             dificuldade = ?,
             trilha = ?
             WHERE id = ?""";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, questao.getPergunta());
            ps.setInt(2, questao.getPontos());
            ps.setString(3, questao.getOpcaoCerta());
            ps.setInt(4, questao.getDificuldade().ordinal());
            ps.setInt(5, questao.getTrilha().ordinal());

            ps.executeUpdate();
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


    public List<Questao> listAll() throws BancoDeDadosException {
        List<Questao> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement ps = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO";

            ResultSet res = ps.executeQuery(sql);

            while (res.next()){
                Questao questao = new Questao();
                questao.setId(res.getInt("id_matematica"));
                questao.setPergunta(res.getString("pergunta"));
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

    public Questao listByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException {
        Questao questao = new Questao();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO WHERE DIFICULDADE = ? AND TRILHA = ? ORDER BY DBMS_RANDOM.VALUE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trilha);
            ps.setInt(2, dificuldade);

            ResultSet res = ps.executeQuery();

            if (res.next()){
                questao.setId(res.getInt("id"));
                questao.setPergunta(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                questao.setTrilha(Trilha.valueOf(res.getInt("trilha")));
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

