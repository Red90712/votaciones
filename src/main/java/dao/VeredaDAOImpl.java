package dao;

import model.Vereda;
import java.sql.*;
import java.util.*;

public class VeredaDAOImpl implements VeredaDAO {

    public VeredaDAOImpl() {}

    private Connection conn;

    public VeredaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Vereda> obtenerTodas() {
        List<Vereda> lista = new ArrayList<>();
        String sql = "SELECT * FROM vereda";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vereda v = new Vereda();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Vereda obtenerPorId(int id) {
        String sql = "SELECT * FROM vereda WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
