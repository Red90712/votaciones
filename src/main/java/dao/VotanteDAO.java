package dao;

import model.Votante;
import java.util.List;

public interface VotanteDAO {
    List<Votante> obtenerTodos();
    Votante obtenerPorId(int id);
    List<Votante> obtenerPorVereda(int veredaId);
}
