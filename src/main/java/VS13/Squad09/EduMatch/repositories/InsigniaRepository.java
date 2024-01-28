package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class InsigniaRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {

        try {
            String sql = "SELECT SEQ_INSIGNIA.nextval AS mysequence FROM DUAL";

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

    public Insignia adicionar(Insignia insignia) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = conexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            log.info(String.valueOf(proximoId));
            insignia.setId(proximoId);
            log.info(insignia.toString());

            String sql = """
                    INSERT INTO INSIGNIA
                    (id_insignia, url_imagem, titulo, descricao, pontuacao, trilha, dificuldade, status, data_emitida, id_usuario)
                    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, insignia.getId());
            stmt.setString(2, insignia.getUrlImagem());
            stmt.setString(3, insignia.getTitulo());
            stmt.setString(4, insignia.getTitulo());
            stmt.setInt(5, insignia.getPontuacao());
            stmt.setInt(6, insignia.getTrilha().ordinal());
            stmt.setInt(7, insignia.getDificuldade().ordinal());
            stmt.setInt(8, insignia.getStatus().ordinal());
            Timestamp ts = Timestamp.valueOf(insignia.getDataEmitida());
            stmt.setTimestamp(9, ts);
            stmt.setInt(10, insignia.getIdUsuario());


            stmt.executeUpdate();
            return insignia;

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
            con = conexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM INSIGNIA WHERE id_insignia = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();

            return res > 0 ? "Insignia deletado com sucesso" : "Insignia não deletado";
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

    public List<Insignia> listar() throws BancoDeDadosException {
        List<Insignia> insignias = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            Statement st = con.createStatement();

            String sql = """
                    SELECT * FROM INSIGNIA WHERE STATUS = 1
                    """;


            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                insignias.add(insigniaSQL(res));
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
        return insignias;
    }

    public List<Insignia> listarPorUsuario(Integer idUsuario) throws BancoDeDadosException {
        List<Insignia> insignias = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                SELECT i.trilha, i.difiiuldade, i.data_emitida, u.nome, u.sobrenome
                FROM INSIGNIA i
                INNER JOIN USUARIO u ON i.ID_USUARIO = u.ID_USUARIO
                WHERE i.ID_USUARIO = ?""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                insigniaSQL(res);

                Usuario usuario = new Usuario();
                usuario.setId(idUsuario);
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));

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
        return insignias;
    }

    public Insignia listarUltimo(Integer idUsuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """           
                    SELECT id_insignia, trilha, dificuldade, data_emitida, nome, sobrenome
                    FROM (
                        SELECT c.id_insignia, c.trilha, c.dificuldade, c.data_emitida, u.nome, u.sobrenome
                        FROM INSIGNIA c
                        INNER JOIN USUARIO u ON c.ID_USUARIO = u.ID_USUARIO
                        WHERE c.ID_USUARIO = ?
                        ORDER BY c.data_emitida DESC
                    )
                    WHERE ROWNUM <= 1""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Insignia insignia = new Insignia();
                insignia.setId(res.getInt("id_insignia"));
                insignia.setTrilha(Trilha.valueOf(res.getInt("trilha")));
                insignia.setDificuldade(Dificuldade.valueOf(res.getInt("dificuldade")));
                Timestamp ts = res.getTimestamp("data_emitida");

                Usuario usuario = new Usuario();
                usuario.setId(idUsuario);
                usuario.setNome(res.getString("nome"));
                usuario.setSobrenome(res.getString("sobrenome"));


                return insignia;
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


    private Insignia insigniaSQL(ResultSet res) throws SQLException {
        Insignia insignia = new Insignia();
        insignia.setId(res.getInt("id_insignia"));
        insignia.setUrlImagem(res.getString("URL_IMAGEM"));
        insignia.setTitulo(res.getString("TITULO"));
        insignia.setDescricao(res.getString("DESCRICAO"));
        insignia.setPontuacao(res.getInt("PONTUACAO"));
        insignia.setTrilha(Trilha.valueOf(res.getInt("TRILHA")));
        insignia.setDificuldade(Dificuldade.valueOf(res.getInt("DIFICULDADE")));
        insignia.setStatus(Status.valueOf(res.getInt("STATUS")));
        Timestamp ts = res.getTimestamp("DATA_EMITIDA");
        insignia.setIdUsuario(res.getInt("ID_USUARIO"));
        insignia.setDataEmitida(ts.toLocalDateTime());
        return insignia;
    }
}
