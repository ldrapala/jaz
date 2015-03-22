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
import luke.jaz.repository.Repository;
import luke.jaz.repository.dummy.DummyDB;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.UnitOfWork;
import luke.jaz.servlet.parameter.UserParameter;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    
    private IEntityBuilder<User> builder;
    private Repository<User> repository;

    @Override
    public void init() throws ServletException {
        this.builder = new UserBuilder();
        DummyDB dummyDB = DummyDB.getInstance();
        IUnitOfWork unitOfWork = new UnitOfWork();
        this.repository = new DummyUserRepository(dummyDB, builder, unitOfWork);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = builder.build(req);
        session.setAttribute(UserParameter.USER, user);
        this.repository.save(user);
    }

}
