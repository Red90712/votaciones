package service;

import model.Vereda;
import java.util.List;

public interface VeredaService {
    List<Vereda> obtenerTodas();
    Vereda obtenerPorId(int id);
}
