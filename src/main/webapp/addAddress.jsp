<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JAVA 4 US!</title>
    </head>
    <body>
        <form action="" method="get">
            <select name="addressType">
                <option value="HOME">Domowy</option>
                <option value="POST">Korespondencyjny</option>
                <option value="WORK">Pracy</option>
            </select>
            <select name="province">
                <option value="POMORSKIE">Pomorskie</option>
                <option value="PODKARPACKIE">Podkarpackie</option>
                <option value="MAZOWIECKIE">Mazowieckie</option>
                <option value="LUBUSKIE">Lubuskie</option>
                <option value="LUBELSKIE">Lubelskie</option>
                <option value="DOLNOSLASKIE">Dolnośląskie</option>
                <option value="ZACHODNIO_POMORSKIE">Zachodnio-pomorskie</option>
                <option value="WARMINSKO_MAZURSKIE">Warmińsko-Mazurskie</option>
                <option value="KUJAWSKO_POMORSKIE">Kujawsko-Pomorskie</option>
                <option value="SWIETOKRZYSKIE">Świętokrzyskie</option>
                <option value="SLASKIE">Śląskie</option>
                <option value="LODZKIE">Łódzkie</option>
                <option value="WIELKOPOLSKIE">Wielkopolskie</option>
                <option value="PODLASKIE">Podlaskie</option>
                <option value="MALOPOLSKIE">Małopolskie</option>
                <option value="OPOLSKIE">Opolskie</option>
            </select>
            Miasto : <input type="text" name="city"  /><br />
            Kod pocztowy : <input type="text" name="postCode"  /><br />
            Ulica : <input type="text" name="street"  /><br />
            Numer domu : <input type="text" name="numberOfApartament"  /><br />
            <input type="submit" value=" OK ">
        </form>
    </body>
</html>
