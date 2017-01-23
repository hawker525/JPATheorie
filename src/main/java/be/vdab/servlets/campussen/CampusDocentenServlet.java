package be.vdab.servlets.campussen;

import be.vdab.services.CampusService;
import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
@WebServlet(name = "CampusDocentenServlet", urlPatterns = "/campussen/docenten.htm")
public class CampusDocentenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/campussen/docenten.jsp";
    private final transient CampusService campusService = new CampusService();
    private final transient DocentService docentService = new DocentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("campussen", campusService.findAll());
        String id = request.getParameter("id");
        if (request.getParameter("bestbetaalde") != null) {
            request.setAttribute("docenten",
                    docentService.findBestBetaaldeVanEenCampus(Long.parseLong(id)));
        }
        if (id != null) {
            campusService.read(Long.parseLong(id))
                    .ifPresent(campus -> request.setAttribute("campus", campus));
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
