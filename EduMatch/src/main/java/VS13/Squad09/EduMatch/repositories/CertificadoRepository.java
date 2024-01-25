package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Games;

import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CertificadoRepository {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {

        try {
            String sql = "SELECT VS_13_EQUIPE_9.seq_certificado.nextval AS mysequence FROM DUAL";

            Statement st = connection.createStatement();

            ResultSet res = st.executeQuery(sql);

            if (res.next()){
                return res.getInt("mysequence");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public Certificado adicionar(Certificado certificado) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            Integer proximoId = this.getProximoId(con);

            certificado.setId(proximoId);

            String sql = """
                    INSERT INTO VS_13_EQUIPE_9.CERTIFICADO
                    (id_certificado, trilha, dificuldade, data_emitida, id_usuario)
                    VALUES(?,?,?,?,?)
                    """;
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, certificado.getId());
            stmt.setInt(2, certificado.getTrilha().ordinal());
            stmt.setInt(3, certificado.getDificuldade().ordinal());
            Timestamp ts = Timestamp.valueOf(certificado.getConclusao());
            stmt.setTimestamp(4, ts);
            stmt.setInt(5, certificado.getUsuario().getId());

            stmt.executeUpdate();
            return certificado;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }finally {
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

    public String remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.CERTIFICADO WHERE id_certificado = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();

            return res > 0 ? "Certificado deletado com sucesso" : "Certificado não deletado";
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

    public List<Certificado> listar() throws BancoDeDadosException {
        List<Certificado> certificados = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.CERTIFICADO c\n" +
                    "RIGHT JOIN VS_13_EQUIPE_9.USUARIO u ON u.ID_USUARIO = c.ID_CERTIFICADO";


            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                Certificado certificado = new Certificado();
                certificado.setId(res.getInt("id_certificado"));
                certificado.setTrilha(Trilha.valueOf(res.getString("nome")));
                Timestamp ts = res.getTimestamp("data_emitida");
                certificado.setConclusao(ts.toLocalDateTime());
                certificados.add(certificado);
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
        return certificados;
    }

    public List<Certificado> listarPorUsuario(Integer idUsuario) throws BancoDeDadosException {
        List<Certificado> certificados = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = """
                SELECT c.trilha, c.dificuldade, c.data_emitida, u.nome, u.sobrenome
                FROM VS_13_EQUIPE_9.CERTIFICADO c
                INNER JOIN VS_13_EQUIPE_9.USUARIO u ON c.ID_USUARIO = u.ID_USUARIO
                WHERE c.ID_USUARIO = ?""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Certificado certificado = new Certificado();
                certificado.setId(res.getInt("id_certificado"));
                certificado.setTrilha(Trilha.valueOf(res.getInt("trilha")));
                certificado.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                Timestamp ts = res.getTimestamp("data_emitida");
                certificado.setConclusao(ts.toLocalDateTime());

                Usuario usuario = new Usuario();
                usuario.setId(idUsuario);
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));

                certificado.setUsuario(usuario);
                certificados.add(certificado);
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
        return certificados;
    }

    public Certificado listarUltimo(Integer idUsuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = """           
                    SELECT id_certificado, trilha, dificuldade, data_emitida, nome, sobrenome
                    FROM (
                        SELECT c.id_certificado, c.trilha, c.dificuldade, c.data_emitida, u.nome, u.sobrenome
                        FROM VS_13_EQUIPE_9.CERTIFICADO c
                        INNER JOIN VS_13_EQUIPE_9.USUARIO u ON c.ID_USUARIO = u.ID_USUARIO
                        WHERE c.ID_USUARIO = ?
                        ORDER BY c.data_emitida DESC
                    )
                    WHERE ROWNUM <= 1""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Certificado certificado = new Certificado();
                certificado.setId(res.getInt("id_certificado"));
                certificado.setTrilha(Games.valueOf(res.getInt("trilha")));
                certificado.setDificuldade(Dificuldades.valueOf(res.getInt("dificuldade")));
                Timestamp ts = res.getTimestamp("data_emitida");
                certificado.setConclusao(ts.toLocalDateTime());

                Usuario usuario = new Usuario();
                usuario.setId(idUsuario);
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));

                certificado.setUsuario(usuario);

                return certificado;
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
