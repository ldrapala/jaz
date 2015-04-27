package luke.jaz.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luke.jaz.entity.Address;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.AddressBuilder;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.context.ContextParameter;
import luke.jaz.repository.IAddressRepository;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.dummy.DummyAddressRepository;
import luke.jaz.repository.dummy.DummyUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.UnitOfWork;

@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IEntityBuilder<User> userBuilder= new UserBuilder();
        IUnitOfWork unitOfWork = new UnitOfWork();
        IUserRepository userRepository = new DummyUserRepository(userBuilder, unitOfWork);
        IEntityBuilder<Address> addressBuilder = new AddressBuilder();
        IAddressRepository addressRepository = new DummyAddressRepository(addressBuilder, unitOfWork);
        
        ServletContext context = req.getServletContext();
        context.setAttribute(ContextParameter.UNIT_OF_WORK, unitOfWork);
        context.setAttribute(ContextParameter.USERS_REPOSITORY, userRepository);
        context.setAttribute(ContextParameter.ADDRESS_REPOSITORY, addressRepository);
        
        resp.sendRedirect(JspUrlBuilder.build(JspName.DEFAULT_OPTIONS_JSP));
    }

}
