<%@page import="luke.jaz.parameter.context.ContextParameter"%>
<%@page import="luke.jaz.repository.IAddressRepository"%>
<%@page import="luke.jaz.entity.Address"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="luke.jaz.entity.User"%>
<%@page import="luke.jaz.parameter.servlet.UserParameter"%>
<%@page import="luke.jaz.parameter.session.SessionParameter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JAVA 4 US!</title>
    </head>
    <body>
        <%
            User user = (User) request.getAttribute(UserParameter.USER);
            IAddressRepository repository = (IAddressRepository) request.getServletContext().getAttribute(ContextParameter.ADDRESS_REPOSITORY);
            for (Integer id : user.getAddresses()) {
                Address address = repository.get(id);
                out.print(address);
                out.println("<a href=\"./editAddress.jsp?id=" + address.getId() + "\">Edytuj</a>");
                out.println("<a href=\"./RemoveAddressServlet?id=" + address.getId() + "\">Usuń</a>");
            }
        %>
        <a href="./addAddress.jsp">Dodaj</a>
    </body>
</html>
