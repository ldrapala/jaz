package luke.jaz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import luke.jaz.entity.User;
import luke.jaz.parameter.servlet.UserParameter;

@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(UserParameter.USER);
        PrintWriter writer = resp.getWriter();
        writer.print(user);
    }

}
