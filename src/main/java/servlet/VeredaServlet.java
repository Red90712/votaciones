package servlet;

import com.google.gson.Gson;
import model.Vereda;
import service.VeredaService;
import service.VeredaServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/veredas")
public class VeredaServlet extends HttpServlet {

    private VeredaService veredaService;

    @Override
    public void init() throws ServletException {
        this.veredaService = new VeredaServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Vereda> veredas = veredaService.obtenerTodas();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(veredas);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
