package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.dummy.DummyDB;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.UnitOfWork;
import luke.jaz.servlet.parameter.UserParameter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private IEntityBuilder<User> builder;
    private IUserRepository repository;

    @Override
    public void init() throws ServletException {
        this.builder = new UserBuilder();
        this.repository = new DummyUserRepository(builder);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) req.getAttribute(UserParameter.NAME);
        String pwd = (String) req.getAttribute(UserParameter.NAME);
        User user = repository.get(login, pwd);
        if (user != null) {
            session.setAttribute(UserParameter.USER, user);
        }
    }

}
