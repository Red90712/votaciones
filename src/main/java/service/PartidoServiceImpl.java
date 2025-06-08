package service;

import dao.PartidoDAO;
import dao.PartidoDAOImpl;
import model.Partido;

import java.util.List;

public class PartidoServiceImpl implements PartidoService {

    private final PartidoDAO partidoDAO;

    public PartidoServiceImpl() {
        this.partidoDAO = new PartidoDAOImpl();
    }

    @Override
    public List<Partido> obtenerTodos() {
        return partidoDAO.obtenerTodos();
    }

    @Override
    public Partido obtenerPorId(int id) {
        return partidoDAO.obtenerPorId(id);
    }
}
