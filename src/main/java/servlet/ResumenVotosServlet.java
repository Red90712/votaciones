package servlet;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.VotoService;
import service.VotoServiceImpl;
import model.ResumenVoto;

import java.io.IOException;
import java.util.List;

/**
 * Servlet que expone una API REST para obtener el resumen total de votos,
 * agrupados por partido, candidato y vereda.
 */

@WebServlet("/api/resumen-votos")
public class ResumenVotosServlet extends HttpServlet {

    // Servicio que contiene la lógica para consultar los votos
    private VotoService votoService = new VotoServiceImpl();
    /**
     * Maneja las solicitudes GET a la ruta /api/resumen-votos.
     * Obtiene el resumen de votos desde el servicio y lo devuelve como JSON.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ResumenVoto> resumen = votoService.obtenerResumenVotos();

        // Llama al servicio para obtener el resumen de votos
        response.setContentType("application/json");
        
        // Configura el tipo de contenido como JSON con codificación UTF-8
        response.setCharacterEncoding("UTF-8");

        // Serializa la lista de resumen a JSON y la escribe en la respuesta
        response.getWriter().write(new Gson().toJson(resumen));
    }
}
