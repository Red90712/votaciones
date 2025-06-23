package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import dao.VeredaDAO;
import dao.VeredaDAOImpl;
import dao.CandidatoDAO;
import dao.CandidatoDAOImpl;
import model.Vereda;
import model.Candidato;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegistrarVotoServlet", urlPatterns = {"/    "})
public class RegistrarVotoServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    VeredaDAO veredaDAO = new VeredaDAOImpl();
    CandidatoDAO candidatoDAO = new CandidatoDAOImpl();

    List<Vereda> veredas = veredaDAO.obtenerTodas();
    List<Candidato> candidatos = candidatoDAO.obtenerTodos();

    request.setAttribute("veredas", veredas);
    request.setAttribute("candidatos", candidatos);

    RequestDispatcher dispatcher = request.getRequestDispatcher("/registro-voto.jsp");
    dispatcher.forward(request, response);
}
}
