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

String sql = """
    SELECT p.nombre AS partido,
       c.nombre AS candidato,
       v2.nombre AS vereda,
       COUNT(v.id) AS total_votos
FROM voto v
INNER JOIN votante v2 ON v.votante_id = v2.id
INNER JOIN candidato c ON v.candidato_id = c.id
INNER JOIN partido p ON c.partido_id = p.id
GROUP BY p.nombre, c.nombre, v2.nombre
ORDER BY p.nombre, c.nombre, v2.nombre
    """;

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