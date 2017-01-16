package be.vdab.servlets.docenten;

import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maarten Westelinck on 16/01/2017 for fietsacademy.
 */
@WebServlet(name = "VoornamenServlet", urlPatterns = "/docenten/voornamen.htm")
public class VoornamenServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/JSP/docenten/voornamen.jsp";
    private final transient DocentService docentService = new DocentService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("voornamen", docentService.findVoornaamEnId());
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
