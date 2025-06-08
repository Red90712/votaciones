package servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.VotoService;
import service.VotoServiceImpl;
import model.ResumenVoto;

import java.io.IOException;
import java.util.List;


@WebServlet("/api/resumen-votos-vereda")
public class ResumenVeredaServlet extends HttpServlet {
    private VotoService votoService = new VotoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ResumenVoto> resumen = votoService.obtenerResumenPorVereda();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(resumen));
    }
}
