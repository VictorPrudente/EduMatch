package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class QuestaoRepository{

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Integer getNextId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT VS_13_EQUIPE_9.SEQ_QUESTAO.nextval AS mysequence from DUAL";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("mysequence");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }


    public Questao create(Questao questao) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            Integer nextid = this.getNextId(con);
            questao.setId(nextid);

            String sql = """
                    INSERT INTO VS_13_EQUIPE_9.QUESTAO
                    (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
                    VALUES(?, ?, ?, ?, ?, ?, ?)""";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, questao.getId());
            ps.setString(2, questao.getPergunta());
            ps.setInt(3, questao.getPontos());
            ps.setString(4, questao.getOpcaoCerta());
            ps.setInt(5, questao.getDificuldade().ordinal());
            ps.setInt(6, questao.getTrilha().ordinal());
            ps.setInt(7, questao.getStatus().ordinal());

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

    public Questao update(Questao questao) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    UPDATE VS_13_EQUIPE_9.QUESTAO
                        SET 
                            pergunta = ?, 
                            pontos = ?, 
                            opcao_correta = ?, 
                            dificuldade = ?,
                            trilha = ?,
                            status = ?, 
                        WHERE
                            id_questao = ?""";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, questao.getId());


            ps.setString(1, questao.getPergunta());
            ps.setInt(2, questao.getPontos());
            ps.setString(3, questao.getOpcaoCerta());
            ps.setInt(4, questao.getDificuldade().ordinal());
            ps.setInt(5, questao.getTrilha().ordinal());
            ps.setInt(6, questao.getStatus().ordinal());

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


    public Questao findById(Integer id) throws BancoDeDadosException {
        Connection con = null;
        Questao questao = new Questao();

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO WHERE ID_QUESTAO = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet res = ps.executeQuery();

            if (res.next()){
                questao.setId(res.getInt("id"));
                questao.setPergunta(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                questao.setTrilha(Trilha.valueOf(res.getInt("trilha")));
                questao.setStatus(Status.valueOf(res.getInt("status")));
            }
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

    public Questao findByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException {
        Connection con = null;
        Questao questao = new Questao();

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO WHERE DIFICULDADE = ? AND TRILHA = ?  AND STATUS = ? ORDER BY DBMS_RANDOM.VALUE";

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


    public List<Questao> findAllByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException {
        List<Questao> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO WHERE DIFICULDADE = ? AND TRILHA = ?  AND STATUS = 1 ORDER BY DIFICULDADE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trilha);
            ps.setInt(2, dificuldade);

            ResultSet res = ps.executeQuery();

            while (res.next()){
                Questao questao = new Questao();
                questao.setId(res.getInt("id"));
                questao.setPergunta(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                questao.setTrilha(Trilha.valueOf(res.getInt("trilha")));
                questoes.add(questao);
            }
            return questoes;
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


    public List<Questao> findAllByTrail(Integer trilha) throws BancoDeDadosException {
        List<Questao> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO WHERE TRILHA = ? AND STATUS = 1 ORDER BY DIFICULDADE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trilha);

            ResultSet res = ps.executeQuery();

            while (res.next()){
                Questao questao = new Questao();
                questao.setId(res.getInt("id"));
                questao.setPergunta(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                questao.setTrilha(Trilha.valueOf(res.getInt("trilha")));
                questoes.add(questao);
            }
            return questoes;
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

    public List<Questao> findAll() throws BancoDeDadosException {
        List<Questao> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.QUESTAO WHERE STATUS = 1";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            while (res.next()){
                Questao questao = new Questao();
                questao.setId(res.getInt("id"));
                questao.setPergunta(res.getString("pergunta"));
                questao.setPontos(res.getInt("pontos"));
                questao.setOpcaoCerta(res.getString("opcao_correta"));
                questao.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                questao.setTrilha(Trilha.valueOf(res.getInt("trilha")));
                questoes.add(questao);
            }
            return questoes;
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

    public void delete(Integer id) throws BancoDeDadosException {
        //Não haverá função de delete para questões.
    }








}

