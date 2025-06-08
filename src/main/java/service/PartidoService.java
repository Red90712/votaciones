package service;

import model.Partido;
import java.util.List;

public interface PartidoService {
    List<Partido> obtenerTodos();
    Partido obtenerPorId(int id);
}
