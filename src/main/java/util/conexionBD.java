package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    private static final String URL = "jdbc:postgresql://dpg-d0n48mre5dus73b05lag-a.oregon-postgres.render.com:5432/votos_bd_jr8a";
    private static final String USER = "red";
    private static final String PASSWORD = "C452UxftjlnmIvJU2w7lpaziYErhd4bO";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de PostgreSQL", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
