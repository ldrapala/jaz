<%@page import="java.io.PrintWriter"%>
<%@page import="luke.jaz.parameter.servlet.UserParameter"%>
<%@page import="luke.jaz.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        User user = (User)session.getAttribute(UserParameter.USER);
        PrintWriter writer = response.getWriter();
        writer.print(user);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JAVA 4 US!</title>
    </head>
    <body>
    </body>
</html>
