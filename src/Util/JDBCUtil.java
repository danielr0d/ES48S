package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilitário para gerenciar conexões com banco de dados
 */
public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/es48s_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar driver JDBC: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Obtém uma conexão com o banco de dados
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Fecha uma conexão com o banco de dados
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}

