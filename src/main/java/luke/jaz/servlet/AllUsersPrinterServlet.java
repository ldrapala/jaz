package luke.jaz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

@WebServlet("/AllUsersPrinterServlet")
public class AllUsersPrinterServlet extends HttpServlet {

    private IEntityBuilder<User> builder;
    private IUserRepository repository;

    @Override
    public void init() throws ServletException {
        this.builder = new UserBuilder();
        this.repository = new DummyUserRepository(builder);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = repository.getAll();
        try (PrintWriter writer = resp.getWriter()) {
            for (User user : users) {
                writer.print(user);
            }
        }
    }

}
