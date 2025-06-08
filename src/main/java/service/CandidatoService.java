package service;

import model.Candidato;
import java.util.List;

public interface CandidatoService {
    List<Candidato> obtenerTodos();
    Candidato obtenerPorId(int id);
    List<Candidato> obtenerPorPartido(int partidoId);
}
