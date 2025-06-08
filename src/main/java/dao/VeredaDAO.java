package dao;

import java.util.List;
import model.Vereda;

public interface VeredaDAO {
    List<Vereda> obtenerTodas();
    Vereda obtenerPorId(int id);
}
