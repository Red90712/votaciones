package service;

import dao.CandidatoDAO;
import dao.CandidatoDAOImpl;
import model.Candidato;

import java.util.List;

/**
 * Implementación de la interfaz CandidatoService.
 * 
 * Esta clase contiene la lógica de negocio para operar con los datos de los candidatos,
 * utilizando la capa DAO para acceder a la base de datos.
 */

public class CandidatoServiceImpl implements CandidatoService {

    // Instancia del DAO que maneja el acceso a datos de candidatos
    private final CandidatoDAO candidatoDAO;

      
     //constructor que inicializa la implementación concreta del DAO.
     
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
