package service;

import model.Votante;
import java.util.List;

public interface VotanteService {
    List<Votante> obtenerTodos();
    Votante obtenerPorId(int id);
    List<Votante> obtenerPorVereda(int veredaId);
    Votante obtenerPorNombreYVereda(String nombre, int idVereda);
    int insertar(Votante votante);

}
