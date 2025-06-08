package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.conexionBD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/probarConexion")
public class servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            Connection conn = conexionBD.obtenerConexion();
            if (conn != null) {
                out.println("Conexión exitosa a la base de datos PostgreSQL.");
                conn.close();
            } else {
                out.println("Error en la conexión.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
