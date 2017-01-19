package be.vdab.servlets.docenten;

import be.vdab.services.DocentService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Maarten Westelinck on 22/12/2016 for fietsacademy
 */
@WebServlet(name = "ZoekenServlet", urlPatterns = "/docenten/zoeken.htm")
public class ZoekenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/docenten/zoeken.jsp";
    private final transient DocentService docentService = new DocentService();
    private final static String REDIRECT_URL = "%s/docenten/zoeken.htm?id=%d";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getQueryString() != null) {
            try {
                docentService.read(Long.parseLong(request.getParameter("id")))
                        .ifPresent(docent-> request.setAttribute("docent",docent));
            } catch (NumberFormatException ex) {
                request.setAttribute("fouten",
                        Collections.singletonMap("id", "tik een getal"));
// singletonMap maakt intern een Map met één entry (key=id,
// value=tik een getal) en geeft die Map terug als returnwaarde
            }
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        if (request.getParameter("verwijderen") == null) {
            bijnamenToevoegen(request, response, id);
        } else {
            bijnamenVerwijderen(request, response, id);
        }
    }

    private void bijnamenToevoegen(HttpServletRequest request,
                                   HttpServletResponse response, long id) throws IOException, ServletException {
        String bijnaam = request.getParameter("bijnaam");
        if (bijnaam == null || bijnaam.isEmpty()) {
            request.setAttribute("fouten",
                    Collections.singletonMap("bijnaam", "verplicht"));
            docentService.read(id)
                    .ifPresent(docent -> request.setAttribute("docent",docent));
            request.getRequestDispatcher(VIEW).forward(request, response);
        } else {
            docentService.bijnaamToevoegen(id, bijnaam);
            response.sendRedirect(response.encodeRedirectURL(
                    String.format(REDIRECT_URL, request.getContextPath(), id)));
        }
    }

    private void bijnamenVerwijderen(HttpServletRequest request,
                                     HttpServletResponse response, long id) throws IOException {
        String[] bijnamen = request.getParameterValues("bijnaam");
        if (bijnamen != null) {
            docentService.bijnamenVerwijderen(id, bijnamen);
        }
        response.sendRedirect(response.encodeRedirectURL(
                String.format(REDIRECT_URL, request.getContextPath(), id)));
    }
}
