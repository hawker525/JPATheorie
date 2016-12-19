package be.vdab.filters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Maarten Westelinck on 19/12/2016.
 *
 */
@WebFilter("*.htm")
public class JPAFilter implements Filter{
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("fietsacademy");


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       request.setCharacterEncoding("UTF-8");
       chain.doFilter(request, response);
    }

    public void destroy() {
        entityManagerFactory.close();
    }
}
