package dao;

import model.Voto;
import java.util.List;

public interface VotoDAO {
    void registrarVoto(Voto voto);
    List<Voto> obtenerTodos();
    int contarVotosPorCandidato(int candidatoId);
    boolean yaVoto(int votanteId);
    boolean insertar(int idVotante, int idCandidato);

}
