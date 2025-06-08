package service;

import dao.CandidatoDAO;
import dao.CandidatoDAOImpl;
import model.Candidato;

import java.util.List;

public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoDAO candidatoDAO;

    public CandidatoServiceImpl() {
        this.candidatoDAO = new CandidatoDAOImpl();
    }

    @Override
    public List<Candidato> obtenerTodos() {
        return candidatoDAO.obtenerTodos();
    }

    @Override
    public Candidato obtenerPorId(int id) {
        return candidatoDAO.obtenerPorId(id);
    }

    @Override
    public List<Candidato> obtenerPorPartido(int partidoId) {
        return candidatoDAO.obtenerPorPartido(partidoId);
    }
}
