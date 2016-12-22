package be.vdab.servlets.docenten;

import be.vdab.repositories.DocentRepository;

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
    private final transient DocentRepository docentRepository = new DocentRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getQueryString() != null) {
            try {
                docentRepository.read(Long.parseLong(request.getParameter("id")))
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
}
