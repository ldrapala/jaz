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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import luke.jaz.entity.User;
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.servlet.UserParameter;
import luke.jaz.parameter.session.SessionParameter;

@WebFilter(JspName.REGISTRATION_JSP)
public class RegistrationFilter implements Filter {
    
    private User user;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Do registration filter");
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (isUserLoginAleady(session)) {
            System.out.println("User has just already login");
            printUser(response);
        } else if (isUserRegisteredAlready(session)) {
            System.out.println("User has just already registered");
            ((HttpServletResponse) response).sendRedirect(JspUrlBuilder.build(JspName.REGISTRATION_ERROR_JSP));
        } else {
            System.out.println("User has not login yet");
            chain.doFilter(request, response);
        }
    }
    
    private boolean isUserRegisteredAlready(HttpSession session) {
        Boolean alreadyRegistered = (Boolean) session.getAttribute(SessionParameter.ALREADY_REGISTERED);
        return alreadyRegistered != null;
    }
    
    private boolean isUserLoginAleady(HttpSession session) {
        user = (User) session.getAttribute(UserParameter.USER);
        return user != null;
    }
    
    private void printUser(ServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(user);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
    
}
