package service;

import dao.VeredaDAO;
import dao.VeredaDAOImpl;
import model.Vereda;

import java.util.List;

public class VeredaServiceImpl implements VeredaService {

    private VeredaDAO veredaDAO;

    public VeredaServiceImpl() {
        this.veredaDAO = new VeredaDAOImpl();
    }

    @Override
    public List<Vereda> obtenerTodas() {
        return veredaDAO.obtenerTodas();
    }

    @Override
    public Vereda obtenerPorId(int id) {
        return veredaDAO.obtenerPorId(id);
    }
}
