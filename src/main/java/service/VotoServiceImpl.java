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
    public boolean insertar(int idVotante, int idCandidato) {
        return votoDAO.insertar(idVotante, idCandidato);
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
           CONCAT(vrd.nombre, ' (', vt.nombre, ')') AS vereda,
           COUNT(v.id) AS total_votos
    FROM voto v
    JOIN votante vt ON v.id_votante = vt.id
    JOIN vereda vrd ON vt.id_vereda = vrd.id
    JOIN candidato c ON v.id_candidato = c.id
    JOIN partido p ON c.id_partido = p.id
    GROUP BY p.nombre, c.nombre, vrd.nombre, vt.nombre
    ORDER BY p.nombre, c.nombre, vrd.nombre
    """;

    try (Connection con = conexionBD.obtenerConexion();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            resumen.add(new ResumenVoto(
                rs.getString("partido"),
                rs.getString("candidato"),
                rs.getString("vereda"),
                rs.getInt("total_votos")
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resumen;
}

@Override
public List<ResumenVoto> obtenerResumenPorVereda() {
    List<ResumenVoto> resumen = new ArrayList<>();

    String sql = """
        SELECT p.nombre AS partido,
               c.nombre AS candidato,
               vrd.nombre AS vereda,
               COUNT(v.id) AS total_votos
        FROM voto v
        JOIN votante vt ON v.id_votante = vt.id
        JOIN vereda vrd ON vt.id_vereda = vrd.id
        JOIN candidato c ON v.id_candidato = c.id
        JOIN partido p ON c.id_partido = p.id
        GROUP BY p.nombre, c.nombre, vrd.nombre
        ORDER BY vrd.nombre, p.nombre, c.nombre
    """;

    try (Connection con = conexionBD.obtenerConexion();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            resumen.add(new ResumenVoto(
                rs.getString("partido"),
                rs.getString("candidato"),
                rs.getString("vereda"),
                rs.getInt("total_votos")
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resumen;
}
}
