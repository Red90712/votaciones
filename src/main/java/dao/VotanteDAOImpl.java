package dao;

import model.Votante;
import util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotanteDAOImpl implements VotanteDAO {

    @Override
    public List<Votante> obtenerTodos() {
        List<Votante> votantes = new ArrayList<>();
        String sql = "SELECT * FROM votante";

        try (Connection con = conexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                votantes.add(new Votante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("vereda_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votantes;
    }

    @Override
    public Votante obtenerPorId(int id) {
        Votante votante = null;
        String sql = "SELECT * FROM votante WHERE id = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                votante = new Votante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("vereda_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votante;
    }

    @Override
    public List<Votante> obtenerPorVereda(int veredaId) {
        List<Votante> votantes = new ArrayList<>();
        String sql = "SELECT * FROM votante WHERE vereda_id = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, veredaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                votantes.add(new Votante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("vereda_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votantes;
    }
}
