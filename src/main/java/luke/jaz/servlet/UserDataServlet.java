package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.entity.User;
import luke.jaz.parameter.servlet.UserParameter;

@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(UserParameter.USER);
        resp.getWriter().print(user);
    }

}
