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
            PrintWriter writer = response.getWriter();
            for (Address a : user.getAddresses()) {
                writer.print(a);
        %>
        <a href="./login.jsp?id=<%= a.getId() %>">EDYTUJ</a>
        <%
            }
        %>
    </body>
</html>
