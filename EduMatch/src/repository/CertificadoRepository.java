package repository;

import entities.Certificado;
import entities.Usuario;
import entities.enums.Games;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificadoRepository implements Repositorio <Integer, Certificado>{

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

    @Override
    public Certificado adicionar(Certificado certificado) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDadosLocal.getConnection();

            Integer proximoId = this.getProximoId(con);

            certificado.setId(proximoId);

            String sql = """
                    INSERT INTO VS_13_EQUIPE_9.CERTIFICADO
                    (id_certificado, trilha, data_emitida, id_usuario)
                    VALUES(?,?,?,?)
                    """ ;
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, certificado.getId());
            stmt.setInt(2,certificado.getGame().ordinal());
            Timestamp ts = Timestamp.valueOf(certificado.getConclusao());
            stmt.setTimestamp(3, ts);
            stmt.setInt(4, certificado.getUsuario().getId());

            int res = stmt.executeUpdate();
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
    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = "DELETE FROM VS_13_EQUIPE_9.CERTIFICADO WHERE id_certificado = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerCertificadoPorId.res=" + res);

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
    public boolean editar(Integer id, Certificado certificado) throws BancoDeDadosException {
        //certificado não deve ser alterado depois de emitido.
        return false;
        }

    @Override
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
                certificado.setGame(Games.valueOf(res.getString("nome")));
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

    public List<Certificado> listarPorUsuario(Usuario usuario) throws BancoDeDadosException {
            List<Certificado> certificados = new ArrayList<>();
            Connection con = null;
            try {
                con = ConexaoBancoDeDadosLocal.getConnection();

                String sql = """
                        SELECT c.id_certificado, c.trilha, c.data_emitida
                        FROM VS_13_EQUIPE_9.CERTIFICADO c
                        WHERE ID_USUARIO = ?""";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, usuario.getId());

                ResultSet res = stmt.executeQuery();

                while (res.next()) {
                    Certificado certificado = new Certificado();
                    certificado.setId(res.getInt("id_certificado"));
                    certificado.setGame(Games.valueOf(res.getInt("trilha")));
                    Timestamp ts = res.getTimestamp("data_emitida");
                    certificado.setConclusao(ts.toLocalDateTime());
                    certificados.add(certificado);
                    certificado.setUsuario(usuario);
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

    public Certificado listarUltimo(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDadosLocal.getConnection();

            String sql = """
                    SELECT c.id_certificado, c.trilha, c.data_emitida
                    FROM VS_13_EQUIPE_9.CERTIFICADO c
                    WHERE ID_USUARIO = ?
                    ORDER BY c.data_emitida DESC""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Certificado certificado = new Certificado();
                certificado.setId(res.getInt("id_certificado"));
                certificado.setGame(Games.valueOf(res.getInt("trilha")));
                Timestamp ts = res.getTimestamp("data_emitida");
                certificado.setConclusao(ts.toLocalDateTime());
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
