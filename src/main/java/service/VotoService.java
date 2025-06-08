package service;

import model.ResumenVoto;
import model.Voto;
import java.util.List;

public interface VotoService {
    void registrarVoto(Voto voto);
    List<Voto> obtenerTodos();
    int contarVotosPorCandidato(int candidatoId);
    boolean yaVoto(int votanteId);
    List<ResumenVoto> obtenerResumenVotos();
    List<ResumenVoto> obtenerResumenPorVereda();
}

