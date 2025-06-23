package dao;

import model.Vereda;
import util.conexionBD;

import java.sql.*;
import java.util.*;

public class VeredaDAOImpl implements VeredaDAO {

    @Override
    public List<Vereda> obtenerTodas() {
        List<Vereda> veredas = new ArrayList<>();
        String sql = "SELECT * FROM vereda";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vereda v = new Vereda();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                veredas.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veredas;
    }

    @Override
    public Vereda obtenerPorId(int id) {
        String sql = "SELECT * FROM vereda WHERE id = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vereda v = new Vereda();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                return v;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
