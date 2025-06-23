package servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Candidato;
import service.CandidatoService;
import service.CandidatoServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/candidatos")
public class CandidatoServlet extends HttpServlet {

    private final CandidatoService candidatoService = new CandidatoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Verifica si hay un parámetro "partidoId"
        String partidoIdParam = request.getParameter("partidoId");
        List<Candidato> candidatos;

        if (partidoIdParam != null) {
            try {
                int partidoId = Integer.parseInt(partidoIdParam);
                candidatos = candidatoService.obtenerPorPartido(partidoId);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de partido no válido");
                return;
            }
        } else {
            candidatos = candidatoService.obtenerTodos();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(candidatos));
    }
}
