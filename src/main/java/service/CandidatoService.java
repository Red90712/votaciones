package service;

import model.Candidato;
import java.util.List;

/* 
 Interfaz que define los métodos del servicio para manejar la lógica de negocio
 relacionada con los candidatos.
 
 Esta capa actúa como intermediaria entre la capa DAO y el controlador o servlet.
*/

public interface CandidatoService {
    List<Candidato> obtenerTodos();
    Candidato obtenerPorId(int id);
    List<Candidato> obtenerPorPartido(int partidoId);
}
