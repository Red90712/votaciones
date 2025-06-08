package dao;

import model.Voto;
import util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotoDAOImpl implements VotoDAO {

    @Override
    public void registrarVoto(Voto voto) {
        String sql = "INSERT INTO votos (votante_id, candidato_id) VALUES (?, ?)";

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
        String sql = "SELECT * FROM votos";

        try (Connection con = conexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                votos.add(new Voto(
                    rs.getInt("id"),
                    rs.getInt("votante_id"),
                    rs.getInt("candidato_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votos;
    }

    @Override
    public int contarVotosPorCandidato(int candidatoId) {
        String sql = "SELECT COUNT(*) FROM votos WHERE candidato_id = ?";
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
        String sql = "SELECT COUNT(*) FROM votos WHERE votante_id = ?";
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
}
