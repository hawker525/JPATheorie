package be.vdab.filters;

import javax.persistence.EntityManager;
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
    private static final ThreadLocal<EntityManager> entityManagers = new ThreadLocal<>();


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        entityManagers.set(entityManagerFactory.createEntityManager());
        try{
            request.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        } finally {
            EntityManager entityManager = entityManagers.get();
            entityManager.close();
            entityManagers.remove();
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagers.get();
    }

    public void destroy() {
        entityManagerFactory.close();
    }
}
