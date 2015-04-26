package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.context.ContextParameter;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.UnitOfWork;

@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IEntityBuilder<User> builder= new UserBuilder();
        IUnitOfWork unitOfWork = new UnitOfWork();
        IUserRepository repository = new DummyUserRepository(builder, unitOfWork);
        
        ServletContext context = req.getServletContext();
        context.setAttribute(ContextParameter.UNIT_OF_WORK, unitOfWork);
        context.setAttribute(ContextParameter.USERS_REPOSITORY, repository);
        
        resp.sendRedirect(JspUrlBuilder.build(JspName.DEFAULT_OPTIONS_JSP));
    }

}
