package dao;

import model.Partido;
import java.util.List;

public interface PartidoDAO {
    List<Partido> obtenerTodos();
    Partido obtenerPorId(int id);
}
