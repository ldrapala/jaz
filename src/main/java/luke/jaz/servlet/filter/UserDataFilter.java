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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import luke.jaz.entity.User;
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.servlet.UserParameter;

@WebFilter(JspName.USER_DATA_JSP)
public class UserDataFilter implements Filter {

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
            chain.doFilter(request, response);
        } else {
            System.out.println("User has not login yet");
            ((HttpServletResponse)response).sendRedirect(JspUrlBuilder.build(JspName.ACCESS_DENIED_JSP));
            response.getWriter().print(user);
        }
    }

    @Override
    public void destroy() {
    }

}
