<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="test" action="saveGameProgress" method="post">
            Save Name: <input type="text" name="game_save_name">
            Game Name: <input type="text" name="game_name">
            Player Name: <input type="text" name="game_player_name">
            Score: <input type="text" name="game_score">
            Game Data: <input type="text" name="game_data">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
