package servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Vereda;
import service.VeredaService;
import service.VeredaServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/veredas")
public class VeredaServlet extends HttpServlet {
    private VeredaService veredaService = new VeredaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Vereda> veredas = veredaService.obtenerTodas();
        String json = new Gson().toJson(veredas);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
