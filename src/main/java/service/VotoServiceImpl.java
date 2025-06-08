package service;

import dao.VotoDAO;
import dao.VotoDAOImpl;
import model.ResumenVoto;
import model.Voto;
import util.conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotoServiceImpl implements VotoService {

    private final VotoDAO votoDAO;

    public VotoServiceImpl() {
        this.votoDAO = new VotoDAOImpl();
    }

    @Override
    public void registrarVoto(Voto voto) {
        votoDAO.registrarVoto(voto);
    }

    @Override
    public List<Voto> obtenerTodos() {
        return votoDAO.obtenerTodos();
    }

    @Override
    public int contarVotosPorCandidato(int candidatoId) {
        return votoDAO.contarVotosPorCandidato(candidatoId);
    }

    @Override
    public boolean yaVoto(int votanteId) {
        return votoDAO.yaVoto(votanteId);
    }

    @Override
    public List<ResumenVoto> obtenerResumenVotos() {
    List<ResumenVoto> resumen = new ArrayList<>();

    String sql = "SELECT p.nombre AS partido, c.nombre AS candidato, v.vereda_id AS vereda, COUNT(*) AS cantidad_votos " +
                 "FROM votos vo " +
                 "JOIN votantes v ON vo.votante_id = v.id " +
                 "JOIN candidatos c ON vo.candidato_id = c.id " +
                 "JOIN partidos p ON c.partido_id = p.id " +
                 "GROUP BY p.nombre, c.nombre, v.vereda_id " +
                 "ORDER BY cantidad_votos DESC";

    try (Connection con = conexionBD.obtenerConexion();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            resumen.add(new ResumenVoto(
                rs.getString("partido"),
                rs.getString("candidato"),
                rs.getInt("vereda"),
                rs.getInt("cantidad_votos")
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resumen;
}

}
