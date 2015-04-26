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
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.parameter.servlet.UserParameter;
import luke.jaz.parameter.session.SessionParameter;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private IEntityBuilder<User> builder;
    private IUnitOfWork unitOfWork;
    private IUserRepository repository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("DoGet registration servlet");
        initVariablesFromContext(req);
        String url;
        if (isDataValid(req)) {
            System.out.println("Data valid");
            saveDataRegistrationToDB(req);
            req.getSession().setAttribute(SessionParameter.ALREADY_REGISTERED, true);
            url = JspUrlBuilder.build(JspName.LOGIN_JSP);
        } else {
            System.out.println("Data not valid");
            url = JspUrlBuilder.build(JspName.REGISTRATION_ERROR_JSP);
        }
        resp.sendRedirect(url);
    }

    private void initVariablesFromContext(HttpServletRequest req) {
        ServletContext context = req.getServletContext();
        this.repository = (IUserRepository) context.getAttribute(ContextParameter.USERS_REPOSITORY);
        this.unitOfWork = (IUnitOfWork) context.getAttribute(ContextParameter.UNIT_OF_WORK);
        this.builder = new UserBuilder();
    }

    private boolean isDataValid(HttpServletRequest req) {
        String password = req.getParameter(UserParameter.PASSWORD);
        String confirmPassword = req.getParameter(UserParameter.CONFIRM_PASSWORD);
        return password.equals(confirmPassword);
    }

    private void saveDataRegistrationToDB(HttpServletRequest req) {
        User user = this.builder.build(req);
        this.repository.save(user);
        this.unitOfWork.commit();
    }

}
