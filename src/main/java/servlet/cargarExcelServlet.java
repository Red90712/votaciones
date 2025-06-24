package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import util.conexionBD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/api/cargar-excel")
public class cargarExcelServlet extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part filePart = req.getPart("file");
        try (InputStream fileContent = filePart.getInputStream();
             Workbook workbook = new XSSFWorkbook(fileContent)) {

            Sheet sheet = workbook.getSheetAt(0); // Primer hoja
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezado

                String nombre = row.getCell(0).getStringCellValue();
                int veredaId = (int) row.getCell(1).getNumericCellValue();

                // Insertar en la BD (ejemplo: insertar votante)
                insertarVotante("Votante " + nombre, veredaId);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void insertarVotante(String nombre, int veredaId) throws SQLException {
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "INSERT INTO votante (nombre, id_vereda) VALUES (?, ?)")) {
            stmt.setString(1, nombre);
            stmt.setInt(2, veredaId);
            stmt.executeUpdate();
        }
    }
    
}
