<%@page contentType="text/html" pageEncoding="windows-1250"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>JAVA 4 US!</title>
    </head>
    <body>
        <form action="RegistrationServlet" method="get">
            Imię : <input type="text" name="name"  /><br />
            Hasło : <input type="text" name="password"  /><br />
            Potwierdź hasło : <input type="text" name="confirmPassword"  /><br />
            E-mail : <input type="text" name="mail"  /><br />
            <input type="submit" value=" OK ">
        </form>
    </body>
</html>
