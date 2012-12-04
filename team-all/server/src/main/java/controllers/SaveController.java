package controllers;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import db.DatabaseHandler;
import db.Resources;

@Controller
public class SaveController {

	private static Logger log = Logger.getLogger(SaveController.class);
	
	@RequestMapping(value = "/saveResource", method = RequestMethod.POST)
	@ResponseBody
	public String saveResource(@RequestParam("resource") String json) {
		Gson gson = new Gson();
        try {
        	
            Resources resource = gson.fromJson(json, Resources.class);

            Session session = DatabaseHandler.getDatabaseHandlerInstance().getHibernateSession();
            Transaction t = session.beginTransaction();
            session.save(resource);
            t.commit();

            session.close();

            return gson.toJson(true);

        } catch (Exception e) {
        	return gson.toJson(false);
        } 
		
	
	}
	
	@RequestMapping(value = "/saveGameBase", method = RequestMethod.POST)
	@ResponseBody
	public String saveGameBase(@RequestParam("game_name") String game_name,@RequestParam("game_data") String game_data,@RequestParam("game_author") String game_author,@RequestParam("isMultiPlayer") String isMultiPlayer ) {
		int isMultiPlayerValue;
        Gson gson = new Gson();
        log.info("****************isMultiPlayer**********************"+isMultiPlayer);
        if(isMultiPlayer.equals("true")){
        	isMultiPlayerValue=1;
        }
        else{
        	isMultiPlayerValue=0;
        }
        log.info("**************isMultiPlayerValue************************"+isMultiPlayerValue);
        if (game_name != null && game_data != null && game_author != null) {

            String sql = "select count(*) FROM GameBase where game_name='" + game_name + "'";
            String sql2 = "insert into GameBase (game_name, game_author, game_data,isMultiPlayer) VALUES ('" + game_name + "', '" + game_author + "', '" + game_data + "', " + isMultiPlayerValue + ")";
            String sql3 = "update GameBase set game_author = '" + game_author + "', game_data = '" + game_data + "' where game_name='" + game_name + "'";

            List<BigInteger> count = DatabaseHandler.listQuery(sql);
            log.info(count);

            if (count.get(0).intValue() < 1) {
                DatabaseHandler.executeQuery(sql2);
            } else {
                DatabaseHandler.executeQuery(sql3);
            }

            log.info("added game: " + game_name);

            return gson.toJson(true);
        } else {
            return gson.toJson(false);
        }
        
	}
	
	@RequestMapping(value = "/saveGameProgress", method = RequestMethod.POST)
	@ResponseBody
	public String saveProgressGame(@RequestParam("game_name") String game_name,@RequestParam("game_save_name") String save_name,@RequestParam("game_data") String game_data,@RequestParam("game_player_name") String player_name,@RequestParam("game_score") String gameScore) {
        int game_score = Integer.parseInt(gameScore);

        Gson gson = new Gson();

        if (save_name != null && game_data != null && player_name != null && game_name != null) {
            String sql1 = "select count(*) FROM InProgressGame where game_name='" + game_name + "'and save_name='" + save_name + "' and player_name='" + player_name + "'";
            String sql2 = "insert into InProgressGame (game_name, player_name, score, game_data, save_name) VALUES ('" + game_name + "', '" + player_name + "'," + game_score + ",'" + game_data + "','" + save_name + "')";
            String sql3 = "update InProgressGame set game_data = '" + game_data + "',score = " + game_score + " where game_name='" + game_name + "' and save_name='" + save_name + "' and player_name='" + player_name + "'";

            List<BigInteger> count = DatabaseHandler.listQuery(sql1);


            if (count.get(0).intValue() < 1) {
                DatabaseHandler.executeQuery(sql2);
            } else {
                DatabaseHandler.executeQuery(sql3);
            }


            return gson.toJson(true);
        } else {
        	return gson.toJson(false);
        }
        
	}
}
