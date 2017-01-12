package be.vdab.servlets.docenten;

import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maarten Westelinck on 12/01/2017 for fietsacademy.
 */
@WebServlet(name = "DocentenVerwijderenServlet", urlPatterns = "/docenten/verwijderen.htm")
public class DocentenVerwijderenServlet extends HttpServlet {
    private final transient DocentService docentService = new DocentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        docentService.delete(Long.parseLong(request.getParameter("id")));
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
