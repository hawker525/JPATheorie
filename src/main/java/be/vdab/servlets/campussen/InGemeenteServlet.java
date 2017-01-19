package be.vdab.servlets.campussen;

import be.vdab.services.CampusService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
@WebServlet(name = "InGemeenteServlet", urlPatterns = "/campussen/ingemeente.htm")
public class InGemeenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/campussen/ingemeente.jsp";
    private final transient CampusService campusService = new CampusService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String gemeente = request.getParameter("gemeente");
        if (gemeente != null) {
            if (gemeente.isEmpty()) {
                request.setAttribute("fouten",
                        Collections.singletonMap("gemeente", "verplicht"));
            } else {
                request.setAttribute( "campussen",
                        campusService.findByGemeente(gemeente));
            }
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
