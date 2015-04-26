<%@page import="luke.jaz.entity.User"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="luke.jaz.parameter.context.ContextParameter"%>
<%@page import="luke.jaz.repository.IUserRepository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JAVA 4 US!</title>
    </head>
    <%
        IUserRepository repo = (IUserRepository) application.getAttribute(ContextParameter.USERS_REPOSITORY);
        PrintWriter writer = response.getWriter();
        for (User u : repo.getAll()) {
            writer.print(u.getName() + "\t" + u.getRole());
        }
    %>
    <body>
        <form action="GrantRoleServlet" method="get">
            User : <input type="text" name="name"  /><br />
            <select name="role">
                <option value="ADMIN">Admin</option>
                <option value="PREMIUM">Premium</option>
                <option value="USUAL">Usual</option>
            </select>
            <input type="submit" value=" OK ">
        </form>
    </body>
</html>
