package dao;

import model.Candidato;
import java.util.List;

public interface CandidatoDAO {
    List<Candidato> obtenerTodos();
    Candidato obtenerPorId(int id);
    List<Candidato> obtenerPorPartido(int partidoId);
}
