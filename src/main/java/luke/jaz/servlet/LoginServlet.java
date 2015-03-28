package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.parameter.context.ContextParameter;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.UnitOfWork;
import luke.jaz.parameter.servlet.UserParameter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoGet login servlet");
        HttpSession session = req.getSession();
        String login = (String) req.getParameter(UserParameter.NAME);
        String pwd = (String) req.getParameter(UserParameter.PASSWORD);
        User user = repository.get(login, pwd);
        if (user != null) {
            session.setAttribute(UserParameter.USER, user);
            Integer onlineUserNumber = (Integer) req.getServletContext().getAttribute(ContextParameter.ONLINE_USERS);
            onlineUserNumber = onlineUserNumber != null ? onlineUserNumber : 0;
            req.getServletContext().setAttribute(ContextParameter.ONLINE_USERS, onlineUserNumber);
            RequestDispatcher rd = req.getRequestDispatcher("/ProfileUserPrinterServlet");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("./errors/loginError.jsp");
        }
    }

}
