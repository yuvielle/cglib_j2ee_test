package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yuvielle
 * Date: 13.08.13
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public class SecurityFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //To change body of implemented methods use File | Settings | File Templates

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        String action = request.getParameter("action");
        String module = request.getParameter("module");
        // All other functionality requires authentication.
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        if ( uri.indexOf("/css") > 0){
            chain.doFilter(req, resp);
            return;
        }
        else if( uri.indexOf("/images") > 0){
            chain.doFilter(req, resp);
            return;
        }
        else if( uri.indexOf("/js") > 0){
            chain.doFilter(req, resp);
            return;
        }

        if (module == null || action == null){
            resp.sendRedirect(req.getContextPath() + "?module=gallery&action=list");
            return;
        }

        // Allow access to login functionality.
        if (action.equalsIgnoreCase("login") && module.equalsIgnoreCase("user")) {
           chain.doFilter(req, resp);
           return;
        }
        // Allow access to register action.
        if (action.equalsIgnoreCase("register") && module.equalsIgnoreCase("user")) {
            chain.doFilter(req, resp);
            return;
        }

        // Allow access to list action.
        if (module.equalsIgnoreCase("gallery")) {
            chain.doFilter(req, resp);
            return;
        }

        // Allow access to list action.
        if (module.equalsIgnoreCase("manager")) {
            chain.doFilter(req, resp);
            return;
        }


        if (session.getAttribute("user_id") != null)
        {
           // User is logged in.
           chain.doFilter(req, resp);
           return;
        }

        // Request is not authorized.
        resp.sendRedirect(req.getContextPath() + "?module=user&action=login");
        return;
    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
