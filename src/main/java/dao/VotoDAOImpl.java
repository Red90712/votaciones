package dao;

import model.Voto;
import util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotoDAOImpl implements VotoDAO {

    @Override
    public void registrarVoto(Voto voto) {
        String sql = "INSERT INTO voto (id_votante, id_candidato) VALUES (?, ?)";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, voto.getIdVotante());
            stmt.setInt(2, voto.getIdCandidato());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Voto> obtenerTodos() {
        List<Voto> votos = new ArrayList<>();
        String sql = "SELECT * FROM voto";

        try (Connection con = conexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                votos.add(new Voto(
                    rs.getInt("id"),
                    rs.getInt("id_votante"),
                    rs.getInt("id_candidato")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votos;
    }

    @Override
    public int contarVotosPorCandidato(int candidatoId) {
        String sql = "SELECT COUNT(*) FROM voto WHERE id_candidato = ?";
        int total = 0;

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, candidatoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public boolean yaVoto(int votanteId) {
        String sql = "SELECT COUNT(*) FROM voto WHERE id_votante = ?";
        boolean yaVoto = false;

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, votanteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                yaVoto = rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return yaVoto;
    }

    @Override
    public boolean insertar(int idVotante, int idCandidato) {
        String sql = "INSERT INTO voto (id_votante, id_candidato) VALUES (?, ?)";
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVotante);
            ps.setInt(2, idCandidato);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
