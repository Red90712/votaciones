package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/votos?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "sapote89";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
