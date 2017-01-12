package be.vdab.servlets.docenten;

import be.vdab.entities.Docent;
import be.vdab.enums.Geslacht;
import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maarten Westelinck on 12/01/2017 for fietsacademy.
 *
 */
@WebServlet(name = "ToevoegenServlet", urlPatterns = "/docenten/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/JSP/docenten/toevoegen.jsp";
    private static final String REDIRECT_URL = "%s/docenten/zoeken.htm?id=%d";
    private final transient DocentService docentService = new DocentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> fouten = new HashMap<>();
        String voornaam = request.getParameter("voornaam");
        if ( ! Docent.isVoornaamValid(voornaam)) fouten.put("voornaam", "verplicht");
        String familienaam = request.getParameter("familienaam");
        if ( ! Docent.isFamilienaamValid(familienaam)) fouten.put("familienaam", "verplicht");
        BigDecimal wedde = null;
        try {
            wedde = new BigDecimal(request.getParameter("wedde"));
            if ( ! Docent.isWeddeValid(wedde)) {
                fouten.put("wedde", "tik een positief getal of 0");
            }
        } catch (NumberFormatException ex) {
            fouten.put("wedde", "tik een positief getal of 0");
        }
        String geslacht = request.getParameter("geslacht");
        if (geslacht == null) {
            fouten.put("geslacht", "verplicht");
        }
        long rijksRegisterNr = 0;
        try {
            rijksRegisterNr = Long.parseLong(request.getParameter("rijksregisternr"));
            if ( ! Docent.isRijksRegisterNrValid(rijksRegisterNr)) {
                fouten.put("rijksregisternr", "verkeerde cijfers");
            }
        } catch (NumberFormatException ex) {
            fouten.put("rijksregisternr", "verkeerde cijfers");
        }
        if (fouten.isEmpty()) {
            Docent docent =
                    new Docent(voornaam, familienaam, wedde, Geslacht.valueOf(geslacht),
                            rijksRegisterNr);
            docentService.create(docent);
            response.sendRedirect(response.encodeRedirectURL(String.format(
                    REDIRECT_URL, request.getContextPath(), docent.getId())));
        } else {
            request.setAttribute("fouten", fouten);
            request.getRequestDispatcher(VIEW).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
