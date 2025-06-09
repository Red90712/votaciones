package servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.VotoService;
import service.VotoServiceImpl;
import model.ResumenVoto;

import java.io.IOException;
import java.util.List;

/**
 * Servlet que expone una API para obtener el resumen de votos por vereda.
 * 
 */
@WebServlet("/api/resumen-votos-vereda")
public class ResumenVeredaServlet extends HttpServlet {

    // Instancia del servicio de votos, encargado de la lógica de negocio
    private VotoService votoService = new VotoServiceImpl();

    /* Maneja las peticiones GET para retornar el resumen de votos agrupado por vereda.
      El resultado se entrega como JSON
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ResumenVoto> resumen = votoService.obtenerResumenPorVereda();

        // Llama al servicio para obtener los datos agrupados por vereda
        response.setContentType("application/json");

        // Configura la respuesta como JSON
        response.setCharacterEncoding("UTF-8");

        // Convierte el resultado a JSON y lo envía en la respuesta
        response.getWriter().write(new Gson().toJson(resumen));
    }
}
