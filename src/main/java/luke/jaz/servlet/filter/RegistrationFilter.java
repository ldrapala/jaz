package luke.jaz.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.servlet.parameter.UserParameter;

@WebFilter("/registration.jsp")
public class RegistrationFilter implements Filter {

    private IEntityBuilder<User> builder;
    private IUserRepository repository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.builder = new UserBuilder();
        this.repository = new DummyUserRepository(builder);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = (String) request.getAttribute(UserParameter.NAME);
        if (repository.isUserNameFree(name)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("./registration.jsp");
        }
    }

    @Override
    public void destroy() {
    }

}
