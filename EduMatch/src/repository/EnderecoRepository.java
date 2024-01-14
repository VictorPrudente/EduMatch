package repository;

import entities.Endereco;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository implements Repositorio<Integer, Endereco> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_ENDERECO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Endereco adicionar(Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            Integer proximoId = this.getProximoId(con);
            endereco.setId(proximoId);

            String sql = "INSERT INTO ENDERECO\n" +
                    "(id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, endereco.getId());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getPais());

            int res = stmt.executeUpdate();
            System.out.println("adicionarEndereco.res=" + res);
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

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "DELETE FROM ENDERECO WHERE id_endereco = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerEnderecoPorId.res=" + res);

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

    @Override
    public boolean editar(Integer id, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ENDERECO SET ");
            sql.append(" logradouro = ?,");
            sql.append(" numero = ?,");
            sql.append(" complemento = ? ");
            sql.append(" cep = ? ");
            sql.append(" cidade = ? ");
            sql.append(" estado = ? ");
            sql.append(" pais = ? ");
            sql.append(" WHERE id_endereco = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setString(7, endereco.getPais());
            stmt.setInt(8, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarEndereco.res=" + res);

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

    @Override
    public List<Endereco> listar() throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ENDERECO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(res.getInt("id_endereco"));
                endereco.setLogradouro(res.getString("logradouro"));
                endereco.setNumero(res.getInt("numero"));
                endereco.setComplemento(res.getString("complemento"));
                endereco.setCep(res.getString("cep"));
                endereco.setCidade(res.getString("cidade"));
                endereco.setEstado(res.getString("estado"));
                endereco.setPais(res.getString("pais"));
                enderecos.add(endereco);
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
            con = ConexaoBancoDeDadosLocal.getConnection();
            String sql = """
                SELECT end.id_endereco, end.logradouro, end.numero, end.complemento, end.cep, end.cidade, end.estado, end.pais, end.id_usuario, end._id_escola, end.id_empresa
                FROM ENDERECO end
                LEFT JOIN USUARIO u ON u.ID_USUARIO = end.ID_USUARIO
                LEFT JOIN EMPRESA emp ON emp.ID_EMPRESA = end.ID_EMPRESA
                LEFT JOIN ESCOLA esc ON esc.ID_ESCOLA = end.ID_ESCOLA
                WHERE end.id_usuario = ? OR end.id_empresa = ? OR end.id_escola = ?""";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setInt(2, id);
                stmt.setInt(3, id);

                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Endereco endereco = new Endereco();
                        endereco.setId(res.getInt("id_endereco"));
                        endereco.setLogradouro(res.getString("logradouro"));
                        endereco.setNumero(res.getInt("numero"));
                        endereco.setComplemento(res.getString("complemento"));
                        endereco.setCep(res.getString("cep"));
                        endereco.setCidade(res.getString("cidade"));
                        endereco.setEstado(res.getString("estado"));
                        endereco.setPais(res.getString("pais"));

                        int idUsuario = res.getInt("id_usuario");
                        int idEmpresa = res.getInt("id_empresa");
                        int idEscola = res.getInt("id_escola");

                        if (!res.wasNull()) {
                            if (idUsuario != 0) {
                                endereco.setId_usuario(idUsuario);
                            } else if (idEmpresa != 0) {
                                endereco.setId_empresa(idEmpresa);
                            } else if (idEscola != 0) {
                                endereco.setId_escola(idEscola);
                            }

                            return endereco;
                        }
                    }
                }
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
