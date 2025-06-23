package service;

import dao.VotanteDAO;
import dao.VotanteDAOImpl;
import model.Votante;

import java.util.List;

public class VotanteServiceImpl implements VotanteService {

    private final VotanteDAO votanteDAO;

    public VotanteServiceImpl() {
        this.votanteDAO = new VotanteDAOImpl();
    }

    @Override
    public List<Votante> obtenerTodos() {
        return votanteDAO.obtenerTodos();
    }

    @Override
    public Votante obtenerPorId(int id) {
        return votanteDAO.obtenerPorId(id);
    }

    @Override
    public List<Votante> obtenerPorVereda(int veredaId) {
        return votanteDAO.obtenerPorVereda(veredaId);
    }

    @Override
    public Votante obtenerPorNombreYVereda(String nombre, int idVereda) {
    return votanteDAO.obtenerPorNombreYVereda(nombre, idVereda);
}

    @Override
    public int insertar(Votante votante) {
    return votanteDAO.insertar(votante);
}

}
