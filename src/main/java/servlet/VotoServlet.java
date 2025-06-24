package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Votante;
import service.VotanteService;
import service.VotanteServiceImpl;
import service.VotoService;
import service.VotoServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/votos")
public class VotoServlet extends HttpServlet {

    private final VotanteService votanteService = new VotanteServiceImpl();
    private final VotoService votoService = new VotoServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            BufferedReader reader = request.getReader();
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            String nombreVotante = json.get("nombreVotante").getAsString();
            int idVereda = Integer.parseInt(json.get("veredaId").getAsString());
            int idCandidato = Integer.parseInt(json.get("candidatoId").getAsString());

            // Validar si ya existe un voto con ese votante
            if (votoService.votoExiste(nombreVotante, idVereda)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
                response.getWriter().write("{\"mensaje\":\"Este votante ya ha votado en esta vereda\"}");
                return;
            }

            int idVotante;
            Votante votante = votanteService.obtenerPorNombreYVereda(nombreVotante, idVereda);

            if (votante == null) {
                votante = new Votante();
                votante.setNombre(nombreVotante);
                votante.setIdVereda(idVereda);

                idVotante = votanteService.insertar(votante);  // debe retornar el ID generado
            } else {
                idVotante = votante.getId();
            }

            if (idVotante <= 0) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"mensaje\":\"Error al registrar votante\"}");
                return;
            }

            boolean exito = votoService.insertar(idVotante, idCandidato);

            if (exito) {
                response.getWriter().write("{\"mensaje\":\"Voto registrado correctamente\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"mensaje\":\"Error al registrar el voto\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"mensaje\":\"Error interno en el servidor\"}");
        }
    }
}
