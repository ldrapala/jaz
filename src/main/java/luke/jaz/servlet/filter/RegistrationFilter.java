package luke.jaz.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import luke.jaz.entity.User;
import luke.jaz.parameter.servlet.UserParameter;

@WebFilter("/functions/registration.jsp")
public class RegistrationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Do registration filter");
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute(UserParameter.USER);
        if (user != null) {
            System.out.println("User has just already login");
            PrintWriter writer = response.getWriter();
            writer.print(user);
        } else {
            System.out.println("User has not login yet");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
