package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDadosLocal {
    private static final String SERVER = "localhost";
    private static final String PORT = "1521"; // Porta TCP padrão do Oracle
    private static final String DATABASE = "xe";

    // Configuração dos parâmetros de autenticação
    private static final String USER = "system";
    private static final String PASS = "oracle";
    private static final String SCHEMA = "VS_13_EQUIPE_9";

    public static Connection getConnection() {
            try{

        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
        // jdbc:oracle:thin:@localhost:1521:xe

        // Abre-se a conexão com o Banco de Dados
        Connection con = DriverManager.getConnection(url, USER, PASS);

        // sempre usar o schema vem_ser
        con.createStatement().execute("alter session set current_schema=" + SCHEMA);

        return con;
    } catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar-se ao banco de dados.", e);
            }
    }
}
