package repository;

import entities.SoftSkill;
import entities.Usuario;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoftSkillRepository implements Repositorio<Integer, SoftSkill> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try{
            String sql = "SELECT SEQ_ID_SOFT_SKILLS.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()){
                return res.getInt("ID_PERGUNTA");
            }

            return null;
        }
        catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public SoftSkill adicionar(SoftSkill pergunta) throws BancoDeDadosException {
        Connection con = null;

        try {
            con =ConexaoBancoDeDadosLocal.getConnection();

            Integer proximoId = this.getProximoId(con);
            pergunta.setId(proximoId);

            String sql = """
                            INSERT INTO SOFT_SKILLS 
                            (ID_SOFT_SKILLS, pergunta, pontos, opcao_correta, dificuldade)
                            VALUES(?,?,?,?,?)
                            """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, pergunta.getId());
            stmt.setString(2, pergunta.getQuestao());
            stmt.setInt(3,pergunta.getPontos());
            stmt.setString(4,pergunta.getOpcaoCerta());
            stmt.setString(5, pergunta.getDificuldade().getDescricao());

            int res = stmt.executeUpdate();
            System.out.println("adicionarPergunta.res= "+ res);
            return pergunta;

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
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM SOFT_SKILLS WHERE ID_SOFT_SKILLS = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerSOFT_SKILLSPorId.res=" + res);

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

    //(ID_SOFT_SKILLS, pergunta, pontos, opcao_correta, dificuldade)
    @Override
    public boolean editar(Integer id, SoftSkill softSkill) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SOFT_SKILLS SET ");
            sql.append(" pergunta = ?,");
            sql.append(" pontos = ?,");
            sql.append(" opcao_correta = ?, ");
            sql.append(" dificuldade = ? ");
            sql.append(" WHERE id_SOFT_SKILLS = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, softSkill.getQuestao());
            stmt.setInt(2, softSkill.getPontos());
            stmt.setString(3, softSkill.getOpcaoCerta());
            stmt.setInt(4, softSkill.getDificuldade().getNivel());
            stmt.setInt(5, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarSOFT_SKILLS.res=" + res);

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
    //(ID_SOFT_SKILLS, pergunta, pontos, opcao_correta, dificuldade)
    @Override
    public List<SoftSkill> listar() throws BancoDeDadosException {
        List<SoftSkill> perguntas = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM SOFT_SKILLS";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                SoftSkill softSkill = new SoftSkill();
                softSkill.setId(res.getInt("id_SOFT_SKILLS"));
                softSkill.setQuestao(res.getString("questao"));
                softSkill.setPontos(res.getInt("pontos"));
                softSkill.setOpcaoCerta(res.getString("opcao_correta"));
                softSkill.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                perguntas.add(softSkill);
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
        return perguntas;
    }
}
