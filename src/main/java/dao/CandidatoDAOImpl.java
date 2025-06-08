package dao;

import model.Candidato;
import util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAOImpl implements CandidatoDAO {

    @Override
    public List<Candidato> obtenerTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidatos";

        try (Connection con = conexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    @Override
    public Candidato obtenerPorId(int id) {
        Candidato candidato = null;
        String sql = "SELECT * FROM candidatos WHERE id = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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

    @Override
    public List<Candidato> obtenerPorPartido(int partidoId) {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidatos WHERE partido_id = ?";

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
