package VS13.Squad09.EduMatch.repositories;

import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class EnderecoRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT VS_13_EQUIPE_9.SEQ_ENDERECO.nextval AS mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    public Endereco adicionar(Integer idUsuario, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            endereco.setId(proximoId);

            String sql = """
                    INSERT INTO VS_13_EQUIPE_9.ENDERECO
                    (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, tipo_endereco, id_usuario)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, endereco.getId());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getPais());
            stmt.setInt(9, endereco.getTipoDeEndereco().ordinal());
            stmt.setInt(10, idUsuario);


            stmt.executeUpdate();
            return endereco;
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

    public String remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.ENDERECO WHERE id_endereco = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();

            return (res > 0) ? "Endereço deletado com sucesso" : "Endereço não deletado";

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

    public Endereco editar(Integer id, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    UPDATE VS_13_EQUIPE_9.ENDERECO 
                        SET 
                            logradouro = ?, 
                            numero = ?, 
                            complemento = ?, 
                            cep = ?,
                            cidade = ?,
                            estado = ?,
                            pais = ?,
                            tipo_endereco = ?
                        WHERE ID_ENDERECO = ?""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setString(7, endereco.getPais());
            stmt.setInt(8, endereco.getTipoDeEndereco().ordinal());
            stmt.setInt(9, id);

            stmt.executeUpdate();

            return endereco;
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

    public List<Endereco> listar() throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_9.ENDERECO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                enderecos.add(querryEndereco(res));
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
        return enderecos;
    }


    public Endereco listarPorDono(int id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = """
                    SELECT end.id_endereco, end.logradouro, end.numero, end.complemento, end.cep, end.cidade, end.estado, end.pais, end.tipo_endereco, end.id_usuario
                    FROM VS_13_EQUIPE_9.ENDERECO end
                    WHERE end.id_usuario = ?""";

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);

                ResultSet res = stmt.executeQuery();
                if (res.next()) {
                    return querryEndereco(res);
                }
                return null;

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


    public Endereco listarPorId(Integer id) throws BancoDeDadosException, NaoEncontradoException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = """
                    SELECT end.id_endereco, end.logradouro, end.numero, end.complemento, end.cep, end.cidade, end.estado, end.pais, end.tipo_endereco, end.id_usuario
                    FROM VS_13_EQUIPE_9.ENDERECO end
                    WHERE end.id_endereco = ?""";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return querryEndereco(res);
            }
            throw new NaoEncontradoException("Nenhum endereço encontrado com este ID.");

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

    private Endereco querryEndereco(ResultSet res) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(res.getInt("id_endereco"));
        endereco.setLogradouro(res.getString("logradouro"));
        endereco.setNumero(res.getInt("numero"));
        endereco.setComplemento(res.getString("complemento"));
        endereco.setCep(res.getString("cep"));
        endereco.setCidade(res.getString("cidade"));
        endereco.setEstado(res.getString("estado"));
        endereco.setPais(res.getString("pais"));
        endereco.setTipoDeEndereco(TipoDeEndereco.valueOf(res.getInt("TIPO_ENDERECO")));
        endereco.setUsuarioId(res.getInt("id_usuario"));
        return endereco;
    }
}
