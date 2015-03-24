package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.UnitOfWork;
import luke.jaz.servlet.parameter.UserParameter;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private IEntityBuilder<User> builder;
    private IUnitOfWork unitOfWork;
    private IUserRepository repository;

    @Override
    public void init() throws ServletException {
        this.builder = new UserBuilder();
        this.unitOfWork = UnitOfWork.getInstance();
        this.repository = new DummyUserRepository(builder, unitOfWork);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("DoGet registration servlet");
        if (isDataValid(req)) {
            System.out.println("Data valid");
            User user = this.builder.build(req);
            this.repository.save(user);
            this.unitOfWork.commit();
            resp.sendRedirect("./login.jsp");
        } else {
            System.out.println("Data not valid");
            resp.sendRedirect("./registrationError.jsp");
        }
    }

    private boolean isDataValid(HttpServletRequest req) {
        String password = req.getParameter(UserParameter.PASSWORD);
        String confirmPassword = req.getParameter(UserParameter.CONFIRM_PASSWORD);
        return password.equals(confirmPassword);
    }

}
