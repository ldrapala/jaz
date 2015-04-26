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
import luke.jaz.entity.Role;
import luke.jaz.entity.User;
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.servlet.UserParameter;

@WebFilter(JspName.PREMIUM_JSP)
public class PremiumRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Do premium filter");
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute(UserParameter.USER);
        if (user != null && Role.PREMIUM == user.getRole()) {
            System.out.println("User premium login");
            chain.doFilter(request, response);
        } else {
            System.out.println("User has not premium role or not login");
            ((HttpServletResponse) response).sendRedirect(JspUrlBuilder.build(JspName.ACCESS_DENIED_JSP));
        }
    }

    @Override
    public void destroy() {
    }

}
