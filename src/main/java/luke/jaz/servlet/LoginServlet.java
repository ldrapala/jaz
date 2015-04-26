package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.entity.User;
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.context.ContextParameter;
import luke.jaz.repository.IUserRepository;
import luke.jaz.parameter.servlet.UserParameter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoGet login servlet");
        getUserFromDB(req);
        if (user != null) {
            req.getSession().setAttribute(UserParameter.USER, user);
            increaseOnlineUserNumber(req);
            resp.sendRedirect(JspUrlBuilder.build(JspName.SHOW_ALL_USER_ADDRESSES_JSP));
//            req.getRequestDispatcher("/UserDataServlet").include(req, resp);
        } else {
            resp.sendRedirect(JspUrlBuilder.build(JspName.LOGIN_ERROR_JSP));
        }
    }

    private void getUserFromDB(HttpServletRequest req) {
        IUserRepository repository = 
                (IUserRepository) req.getServletContext().getAttribute(ContextParameter.USERS_REPOSITORY);
        String login = (String) req.getParameter(UserParameter.NAME);
        String pwd = (String) req.getParameter(UserParameter.PASSWORD);
        user = repository.get(login, pwd);
    }

    private void increaseOnlineUserNumber(HttpServletRequest req) {
        Integer onlineUserNumber = (Integer) req.getServletContext().getAttribute(ContextParameter.ONLINE_USERS);
        onlineUserNumber = onlineUserNumber != null ? onlineUserNumber : 0;
        req.getServletContext().setAttribute(ContextParameter.ONLINE_USERS, onlineUserNumber);
    }

}
