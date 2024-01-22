package repository;

import entities.Portugues;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortuguesRepository implements Repositorio<Integer, Portugues> {


    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_PORTUGUES.nextval AS mysequence from DUAL";
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
    public Portugues adicionar(Portugues questao) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer nextid = this.getProximoId(con);
            questao.setId(nextid);

            String sql = "INSERT INTO VS_13_EQUIPE_9.PORTUGUES\n" +
                    "(id_portugues, pergunta, pontos, opcao_correta, dificuldade)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, questao.getId());
            ps.setString(2, questao.getQuestao());
            ps.setInt(3, questao.getPontos());
            ps.setString(4, questao.getOpcaoCerta());
            ps.setInt(5, questao.getDificuldade().ordinal());

            int res = ps.executeUpdate();
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
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.PORTUGUES WHERE id_portugues = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int result = ps.executeUpdate();

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
    public boolean editar(Integer id, Portugues portugues) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE VS_13_EQUIPE_9.PORTUGUES SET \n");
            sql.append(" questao = ?,");
            sql.append(" pontos = ?,");
            sql.append(" opcao_correta = ?,");
            sql.append(" dificuldade = ? ");
            sql.append(" WHERE id_portugues = ? ");
            //id, questao, pontos, opcaoCerta, Dificuldade
            PreparedStatement ps = con.prepareStatement(sql.toString());

            ps.setString(1, portugues.getQuestao());
            ps.setInt(2, portugues.getPontos());
            ps.setString(3, portugues.getOpcaoCerta());
            ps.setInt(4, portugues.getDificuldade().ordinal());

            int res = ps.executeUpdate();
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
    public List<Portugues> listar() throws BancoDeDadosException {
        List<Portugues> questoes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement ps = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.PORTUGUES";

            ResultSet res = ps.executeQuery(sql);

            while (res.next()){
                Portugues questao = new Portugues();
                questao.setId(res.getInt("id_portugues"));
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

    public Portugues listarPorDificuldade(int i) throws BancoDeDadosException {
        Portugues questao = new Portugues();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.PORTUGUES WHERE DIFICULDADE = ? ORDER BY DBMS_RANDOM.VALUE";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, i);

            ResultSet res = ps.executeQuery();

            if (res.next()){
                questao.setId(res.getInt("id_portugues"));
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

