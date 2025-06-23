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
                    rs.getInt("id_vereda")
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
                    rs.getInt("id_vereda")
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
        String sql = "SELECT * FROM votante WHERE id_vereda = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, veredaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                votantes.add(new Votante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("id_vereda")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votantes;
    }

@Override
public Votante obtenerPorNombreYVereda(String nombre, int idVereda) {
    String sql = "SELECT * FROM votante WHERE nombre = ? AND id_vereda = ?";
    try (Connection con = conexionBD.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ps.setInt(2, idVereda);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Votante v = new Votante();
            v.setId(rs.getInt("id"));
            v.setNombre(rs.getString("nombre"));
            v.setIdVereda(rs.getInt("id_vereda"));
            return v;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

@Override
public int insertar(Votante votante) {
    String sql = "INSERT INTO votante (nombre, id_vereda) VALUES (?, ?)";
    try (Connection con = conexionBD.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, votante.getNombre());
        ps.setInt(2, votante.getIdVereda());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // ID generado
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // En caso de error
}

}
