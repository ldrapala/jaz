<%@page contentType="text/html" pageEncoding="windows-1250"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>JAVA 4 US!</title>
    </head>
    <body>
        <form action="RegistrationServlet" method="get">
            Imi� : <input type="text" name="name"  /><br />
            Nazwisko : <input type="text" name="surname"  /><br />
            Has�o : <input type="text" name="password"  /><br />
            Potwierd� has�o : <input type="text" name="confirmPassword"  /><br />
            E-mail : <input type="text" name="mail"  /><br />
            Potwierd� e-mail : <input type="text" name="confirmMail"  /><br />
            Gdzie pracujesz : <input type="text" name="company"  /><br />
            Sk�d dowiedzia�e� si� o konferencji?<br>
            <input type="radio" name="source"  value="anoucementFromWork" checked />Og�oszenie w pracy <br>
            <input type="radio" name="source"  value="anoucementFromSchool" />Og�oszenie na uczelni <br>
            <input type="radio" name="source"  value="facebook" />Facebook <br>
            <input type="radio" name="source"  value="friends" />Znajomi <br>
            <input type="radio" name="source"  value="other" />Inne (jakie?) <br>
            Hobby : <input type="text" name="hobby"  /><br />
            <input type="submit" value=" OK ">
        </form>
    </body>
</html>
