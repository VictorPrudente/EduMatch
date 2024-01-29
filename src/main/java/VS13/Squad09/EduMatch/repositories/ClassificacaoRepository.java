package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Classificacao;
import VS13.Squad09.EduMatch.entities.enums.Status;
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
public class ClassificacaoRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;



    public List<Classificacao> listar() throws BancoDeDadosException {
        List<Classificacao> classificacaos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            Statement st = con.createStatement();

            String sql = """
                    SELECT * FROM CLASSIFICACAO WHERE STATUS = 1
                    """;


            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                classificacaos.add(classificacaoSQL(res));
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
        return classificacaos;
    }


    private Classificacao classificacaoSQL(ResultSet res) throws SQLException {
        Classificacao classificacao = new Classificacao();
        classificacao.setId(res.getInt("id_classificacao"));
        classificacao.setUrlImagem(res.getString("URL_IMAGEM"));
        classificacao.setTitulo(res.getString("TITULO"));
        classificacao.setDescricao(res.getString("DESCRICAO"));
        classificacao.setPontuacaoNecessaria(res.getInt("PONTUACAO_NECESSARIA"));
        classificacao.setStatus(Status.valueOf(res.getInt("STATUS")));
        return classificacao;
    }
}
