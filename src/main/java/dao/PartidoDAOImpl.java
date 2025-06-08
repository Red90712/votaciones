package dao;

import model.Partido;
import util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAOImpl implements PartidoDAO {

    @Override
    public List<Partido> obtenerTodos() {
        List<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partidos";

        try (Connection con = conexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                partidos.add(new Partido(
                    rs.getInt("id"),
                    rs.getString("nombre")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }

    @Override
    public Partido obtenerPorId(int id) {
        Partido partido = null;
        String sql = "SELECT * FROM partidos WHERE id = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                partido = new Partido(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partido;
    }
}
