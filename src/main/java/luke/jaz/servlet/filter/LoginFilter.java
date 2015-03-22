package luke.jaz.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import luke.jaz.servlet.parameter.UserParameter;

@WebFilter("/login.jsp")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (isUserRegistered(session)) {
            chain.doFilter(req, response);
        } else {
//przekieruj
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isUserRegistered(HttpSession session) {
        return session.getAttribute(UserParameter.USER) != null;
    }

}
