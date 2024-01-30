package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestaoRepository{

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Integer getNextId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT SEQ_QUESTAO.nextval AS mysequence from DUAL";
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
                    INSERT INTO QUESTAO
                    (id_questao, pontos, pergunta, opcao_correta, trilha, dificuldade, status)
                    VALUES(?, ?, ?, ?, ?, ?, ?)""";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, questao.getId());
            ps.setInt(2, questao.getPontos());
            ps.setString(3, questao.getPergunta());
            ps.setString(4, questao.getOpcaoCerta());
            ps.setInt(5, questao.getTrilha().ordinal());
            ps.setInt(6, questao.getDificuldade().getNivel().intValue());
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
            log.info("conexao com banco ok.");
            String sql = """
                    UPDATE QUESTAO
                        SET 
                            pontos = ?, 
                            pergunta = ?, 
                            opcao_correta = ?, 
                            trilha = ?,
                            dificuldade = ?,
                            status = ? 
                        WHERE
                            id_questao = ?""";

            PreparedStatement ps = con.prepareStatement(sql);
            log.info("preparando SQL.");
            ps.setInt(7, questao.getId());
            log.info("ok no id");

            ps.setInt(1, questao.getPontos());
            log.info("ok no ponto");

            ps.setString(2, questao.getPergunta());
            log.info("ok na pergunta");

            ps.setString(3, questao.getOpcaoCerta());
            log.info("ok na opcao certa");

            ps.setInt(4, questao.getTrilha().ordinal());
            log.info("ok na trilha");

            ps.setInt(5, questao.getDificuldade().ordinal());
            log.info("ok na dificuldade");

            ps.setInt(6, questao.getStatus().ordinal());
            log.info("ok no status");


            ps.executeUpdate();
            log.info("executando SQL e retornando questao.");
            return questao;
        } catch (SQLException e){
            log.error(e.getMessage());
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


    public Questao findById(Integer id) throws BancoDeDadosException, NaoEncontradoException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM QUESTAO WHERE ID_QUESTAO = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet res = ps.executeQuery();

            if (res.next()){
               return questaoSQL(res);
            } throw new NaoEncontradoException("Questão com ID " + id + " não encontrada.");
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

    public Questao findByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException, NaoEncontradoException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM QUESTAO WHERE TRILHA = ? AND DIFICULDADE = ? AND STATUS = 1 ORDER BY DBMS_RANDOM.VALUE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trilha);
            ps.setInt(2, dificuldade);

            ResultSet res = ps.executeQuery();

            if (res.next()){
                return questaoSQL(res);
            } throw new NaoEncontradoException("Nenhuma questao encontrada.");
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

            String sql = "SELECT * FROM QUESTAO WHERE DIFICULDADE = ? AND TRILHA = ?  AND STATUS = 1 ORDER BY DIFICULDADE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trilha);
            ps.setInt(2, dificuldade);

            ResultSet res = ps.executeQuery();

            while (res.next()){
                questoes.add(questaoSQL(res));
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

            String sql = "SELECT * FROM QUESTAO WHERE TRILHA = ? AND STATUS = 1 ORDER BY DIFICULDADE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trilha);

            ResultSet res = ps.executeQuery();

            while (res.next()){
                questoes.add(questaoSQL(res));
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
        log.info("Buscando questao no repositório.");
        List<Questao> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            log.info("conexão com banco ok.");
            String sql = "SELECT * FROM QUESTAO WHERE STATUS = 1";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet res = ps.executeQuery();
            log.info("Executando código, adicionando questões a seguir..");
            while (res.next()){
                questoes.add(questaoSQL(res));
                log.info("adicionada questao.");
            }
            log.info("retornando questao.");
            return questoes;
        } catch (SQLException e){
            log.info("Erro com SQL.");
                e.printStackTrace();
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


    public Questao questaoSQL(ResultSet res) throws SQLException {
        Questao questao = new Questao();
        questao.setId(res.getInt("id_questao"));
        questao.setPontos(res.getInt("pontos"));
        questao.setPergunta(res.getString("pergunta"));
        questao.setOpcaoCerta(res.getString("opcao_correta"));
        questao.setTrilha(Trilha.valueOf(res.getInt("trilha")));
        questao.setDificuldade(Dificuldade.valueOf(res.getInt("dificuldade")));
        questao.setStatus(Status.valueOf(res.getInt("status")));
        return questao;
    }
}

