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
import luke.jaz.jsp.JspName;
import luke.jaz.jsp.JspUrlBuilder;
import luke.jaz.parameter.context.ContextParameter;
import luke.jaz.parameter.servlet.UserParameter;
import luke.jaz.repository.IAddressRepository;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;

@WebServlet("/AddAddressServlet")
public class AddAddressServlet extends HttpServlet {
    
    private IEntityBuilder<Address> builder;
    private IUnitOfWork unitOfWork;
    private IAddressRepository addressRepository;
    private IUserRepository userRepository;
    private int idAddress;
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoGet add address servlet");
        initVariablesFromContext(req);
        saveAddressToDB(req);
        updateUserInDB(req);
        this.unitOfWork.commit();
        resp.sendRedirect(JspUrlBuilder.build(JspName.SHOW_ALL_USER_ADDRESSES_JSP));
    }
    
    private void initVariablesFromContext(HttpServletRequest req) {
        ServletContext context = req.getServletContext();
        this.addressRepository = (IAddressRepository) context.getAttribute(ContextParameter.ADDRESS_REPOSITORY);
        this.userRepository = (IUserRepository) context.getAttribute(ContextParameter.USERS_REPOSITORY);
        this.unitOfWork = (IUnitOfWork) context.getAttribute(ContextParameter.UNIT_OF_WORK);
        this.builder = new AddressBuilder();
    }
    
    private void saveAddressToDB(HttpServletRequest req) {
        Address address = this.builder.build(req);
        this.idAddress = address.getId();
        this.addressRepository.save(address);
    }
    
    private void updateUserInDB(HttpServletRequest req){
        User user = (User)req.getSession().getAttribute(UserParameter.USER);
        user.addAddress(idAddress);
        this.userRepository.update(user);
    }

}
