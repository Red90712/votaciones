package servlet;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Partido;
import service.PartidoService;
import service.PartidoServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/partidos")
public class PartidoServlet extends HttpServlet {

    private PartidoService partidoService = new PartidoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Partido> partidos = partidoService.obtenerTodos();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(partidos));
    }
}
