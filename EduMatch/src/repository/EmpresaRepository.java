package repository;

import entities.Empresa;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository implements Repositorio<Integer, Empresa> {
        @Override
        public Integer getProximoId(Connection connection) throws BancoDeDadosException {
            try{
                String sql = "SELECT seq_empresa.nextval mysequence from DUAL";
                Statement stmt = connection.createStatement();
                ResultSet res = stmt.executeQuery(sql);

                if(res.next()){
                    return res.getInt("SEQ_EMPRESA");
                }

                return null;
            }
            catch (SQLException e){
                throw new BancoDeDadosException(e.getCause());
            }
        }

        @Override
        public Empresa adicionar(Empresa empresa) throws BancoDeDadosException {
            Connection con = null;

            try {
                con =ConexaoBancoDeDadosLocal.getConnection();

                Integer proximoId = this.getProximoId(con);
                empresa.setId(proximoId);

                String sql = """
                            INSERT INTO VS_13_EQUIPE_9.EMPRESA
                            (id_empresa, nome, cnpj, setor, area_de_atuacao, tipo)
                            VALUES(?,?,?,?,?,?)
                            """;

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, empresa.getId());
                stmt.setString(2,empresa.getNome());
                stmt.setString(3,empresa.getCnpj());
                stmt.setString(4,empresa.getSetor());
                stmt.setString(5,empresa.getAreaDeAtuacao());
                stmt.setInt(6,empresa.getTipo());

                int res = stmt.executeUpdate();
                System.out.println("adicionarEmpresa.res= "+ res);
                return empresa;

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
                con = ConexaoBancoDeDadosLocal.getConnection();

                String sql = "DELETE FROM EMPRESA WHERE id_empresa = ?";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, id);

                // Executa-se a consulta
                int res = stmt.executeUpdate();
                System.out.println("removerEmpresaPorId.res=" + res);

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
        public boolean editar(Integer id, Empresa empresa) throws BancoDeDadosException {
            Connection con = null;
            try {
                con = ConexaoBancoDeDadosLocal.getConnection();

                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE EMPRESA SET ");
                sql.append(" nome = ?,");
                sql.append(" cnpj = ?,");
                sql.append(" setor = ? ");
                sql.append(" areaDeAtuacao = ? ");
                sql.append(" tipo = ? ");
                sql.append(" WHERE id_empresa = ? ");

                PreparedStatement stmt = con.prepareStatement(sql.toString());

                stmt.setString(1, empresa.getNome());
                stmt.setString(2, empresa.getCnpj());
                stmt.setString(3, empresa.getSetor());
                stmt.setString(4, empresa.getAreaDeAtuacao());
                stmt.setInt(5, empresa.getTipo());
                stmt.setInt(6, id);

                // Executa-se a consulta
                int res = stmt.executeUpdate();
                System.out.println("editarEmpresa.res=" + res);

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
        public List<Empresa> listar() throws BancoDeDadosException {
            List<Empresa> empresas = new ArrayList<>();
            Connection con = null;
            try {
                con = ConexaoBancoDeDados.getConnection();
                Statement stmt = con.createStatement();

                String sql = "SELECT * FROM EMPRESA";

                // Executa-se a consulta
                ResultSet res = stmt.executeQuery(sql);

                while (res.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setId(res.getInt("id_empresa"));
                    empresa.setNome(res.getString("nome"));
                    empresa.setCnpj(res.getString("cnpj"));
                    empresa.setSetor(res.getString("setor"));
                    empresa.setAreaDeAtuacao(res.getString("area_de_atuacao"));
                    empresa.setTipo(res.getInt("tipo"));
                    empresas.add(empresa);
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
            return empresas;
        }
}

