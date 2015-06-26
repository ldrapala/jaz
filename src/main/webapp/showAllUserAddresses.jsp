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
            User user = (User) session.getAttribute(UserParameter.USER);
            IAddressRepository repository = (IAddressRepository) session.getServletContext().getAttribute(ContextParameter.ADDRESS_REPOSITORY);
            for (Integer id : user.getAddresses()) {
                Address address = repository.get(id);
                if (address != null) {
                    out.print(address);
                    out.println("<a href=\"./editAddress.jsp?id=" + address.getId() + "\">Edytuj</a>");
                    out.println("<a href=\"./RemoveAddressServlet?id=" + address.getId() + "\">Usuń</a>");
                    out.println("<br>");
                }
            }
        %>
        <a href="./addAddress.jsp">Dodaj</a>
    </body>
</html>
