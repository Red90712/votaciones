package dao;
/**
Interfaz DAO (Data Access Object) para la entidad Candidato.
Define las operaciones básicas de acceso a la base de datos relacionadas con los candidatos.

Obtiene la lista completa de candidatos registrados en la base de datos.

Busca y retorna un candidato específico a partir de su ID.

*/
import model.Candidato;
import java.util.List;

public interface CandidatoDAO {
    List<Candidato> obtenerTodos();
    Candidato obtenerPorId(int id);
    List<Candidato> obtenerPorPartido(int partidoId);
}
