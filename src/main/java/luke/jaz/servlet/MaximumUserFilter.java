package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.parameter.context.ContextParameter;

@WebFilter("/functions/*")
public class MaximumUserFilter implements Filter {

    private static final int MAX_USERS_ONLINE = 5;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Maximum user filter");
        ServletContext context = ((HttpServletRequest) request).getServletContext();
        Integer onlineUserNumber = (Integer) context.getAttribute(ContextParameter.ONLINE_USERS);
        onlineUserNumber = onlineUserNumber != null ? onlineUserNumber : 0;
        System.out.println("Users online: "+onlineUserNumber);
        if (isServerOverloaded(onlineUserNumber)) {
            System.out.println("Server overloaded");
            ((HttpServletResponse)response).sendRedirect("./serverOverloaded.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
    
    private boolean isServerOverloaded(Integer onlineUserNumber){
        return onlineUserNumber > MAX_USERS_ONLINE;
    }

    @Override
    public void destroy() {
    }

}
