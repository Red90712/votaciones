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

@WebServlet("/api/resumen-votos")
public class ResumenVotosServlet extends HttpServlet {
    private VotoService votoService = new VotoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ResumenVoto> resumen = votoService.obtenerResumenVotos();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(resumen));
    }
}
