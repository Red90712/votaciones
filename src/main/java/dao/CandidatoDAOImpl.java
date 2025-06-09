package dao;

import model.Candidato;
import util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz CandidatoDAO.
 * Esta clase contiene la lógica para acceder y manipular datos de la tabla "candidato" en la base de datos.
 * Obtiene todos los candidatos de la base de datos.

 */

public class CandidatoDAOImpl implements CandidatoDAO {

    @Override
    public List<Candidato> obtenerTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidato";

        try (Connection con = conexionBD.obtenerConexion(); // Se obtiene la conexión a la BD
             Statement stmt = con.createStatement();        // Se crea un statement simple
             ResultSet rs = stmt.executeQuery(sql)) {       // Se ejecuta la consulta y se obtiene el resultado

            // Iteración sobre los resultados y creación de objetos Candidato
            while (rs.next()) {
                candidatos.add(new Candidato(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("partido_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatos;
    }
     
    // Busca un candidato por su ID.
    @Override
    public Candidato obtenerPorId(int id) {
        Candidato candidato = null;
        String sql = "SELECT * FROM candidato WHERE id = ?";

        try (Connection con = conexionBD.obtenerConexion();         // Conexión a la BD
             PreparedStatement stmt = con.prepareStatement(sql)) {  // Statement preparado para evitar inyección SQL

            stmt.setInt(1, id); // Se asigna el parámetro
            ResultSet rs = stmt.executeQuery();// Se ejecuta la consulta

            if (rs.next()) {
                //Si se encuentra el registro, se crea el objeto Candidato
                candidato = new Candidato(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("partido_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidato;
    }
     
    //btiene todos los candidatos que pertenecen a un partido político específico.

    @Override
    public List<Candidato> obtenerPorPartido(int partidoId) {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidato WHERE partido_id = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, partidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                candidatos.add(new Candidato(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("partido_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatos;
    }
}
