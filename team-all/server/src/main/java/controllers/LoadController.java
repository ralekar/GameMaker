package controllers;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import protocol.GameSaveInfo;

import com.google.gson.Gson;

import db.DatabaseHandler;
import db.Resources;

@Controller
public class LoadController {
	private static Logger log = Logger.getLogger(LoadController.class);

	@RequestMapping(value = "/loadGameBase", method = RequestMethod.GET)
	@ResponseBody
	public String loadGameBase(@RequestParam("game_name") String gameName) {

		String sql = "select game_data from GameBase where game_name='"
				+ gameName + "'";
		@SuppressWarnings("unchecked")
		List<String> game = DatabaseHandler.listQuery(sql);

		if (game.isEmpty()) {
			return "";
		}
		return game.get(0);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loadGamePlay", method = RequestMethod.GET)
	@ResponseBody
	public String loadGamePlay(@RequestParam("game_save_id") String gameSaveId) {

		if (gameSaveId.isEmpty()) {
			return " ";
		}

		String sql = "select game_data from InProgressGame where save_id="
				+ gameSaveId;
		List<String> gameData = DatabaseHandler.listQuery(sql);

		if (gameData.isEmpty()) {
			return " ";
		}

		return gameData.get(0);
	}

	@RequestMapping(value = "/loadResource", method = RequestMethod.GET)
	@ResponseBody
	public String loadResource(@RequestParam("resource_id") String id) {

		Integer rid = Integer.parseInt(id);

		Session session = DatabaseHandler.getDatabaseHandlerInstance()
				.getHibernateSession();

		Resources r = (Resources) session.get(Resources.class, rid);

		session.close();

		Gson gson = new Gson();

		return gson.toJson(r);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listAllGameBases", method = RequestMethod.GET)
	@ResponseBody	
	public String  listAllGameBases(){
        
            String sql = "select game_name from GameBase";
            List<String> gameNames = DatabaseHandler.listQuery(sql);
            Gson gson = new Gson();
            String json = gson.toJson(gameNames);            
            return json;
        

    }

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTopScores", method = RequestMethod.GET)
	@ResponseBody		
	 public String  listTopScores(@RequestParam("game_name") String gameName){
        		
	            if (gameName.isEmpty()) {
	                return " ";
	            }

	            String sql = "select game_name, player_name, score from InProgressGame where game_name='" + gameName + "' order by score desc";

	            List<GameSaveInfo> saves = new LinkedList<GameSaveInfo>();

	            List<Object[]> r = DatabaseHandler.listQuery(sql);

	            int rank = 1;
	            for (Object[] record : r) {
	                GameSaveInfo save = new GameSaveInfo(null, (String) record[0], (String) record[1], (Integer) record[2], rank);
	                saves.add(save);
	                rank++;
	            }

	            Gson gson = new Gson();
	            String json = gson.toJson(saves);

	            return json;

	        }
	
	@RequestMapping(value = "/listAllGamePlays", method = RequestMethod.GET)
	@ResponseBody	
	 public String listAllGamePlays(@RequestParam("player_name") String playerName)
	 {
	            if (playerName.isEmpty()) {
	                return " ";
	            }

	            String sql = "select save_name, game_name, player_name, save_id, score from InProgressGame where player_name='" + playerName + "'";

	            List<GameSaveInfo> saves = new LinkedList<GameSaveInfo>();

	            @SuppressWarnings("unchecked")
				List<Object[]> r = DatabaseHandler.listQuery(sql);

	            for (Object[] record : r) {
	                GameSaveInfo save = new GameSaveInfo((String) record[0], (String) record[1], (String) record[2], (Integer) record[3]);
	                save.setGameScore((Integer) record[4]);                
	                saves.add(save);
	            }

	            Gson gson = new Gson();
	            String json = gson.toJson(saves);

	 
	            return json;
	        }
	
	
		@RequestMapping(value = "/listMultiPlayerGameBases", method = RequestMethod.GET)
		@ResponseBody
		public String listMultiPlayerGameBases() {

				int isMultiplayer=1;

				String sql = "select game_name from GameBase where IsMultiplayer="+isMultiplayer;
				//String sql = "select game_name from GameBase";
				
				@SuppressWarnings("unchecked")
				List<String> names = DatabaseHandler.listQuery(sql);
				log.info("*****************NAMES*********"+names);
				
				Gson gson = new Gson();
	            String json = gson.toJson(names);
	            
	    		return json;

			
	}
		
		@RequestMapping(value = "/getHostedGameBaseId", method = RequestMethod.GET)
		@ResponseBody
		public String getHostedGameBaseId(@RequestParam("hostname") String hostName,
				@RequestParam("gamebasename") String gameBaseName,
				@RequestParam("save_gamebasename") String saveGameBaseName){

			Gson gson = new Gson();
			String result = gson.toJson(-1);

			if (hostName != null && gameBaseName != null && saveGameBaseName != null) {

				String sql = "select id FROM HostedGameBases where hostname=" + 
						hostName + " and gamebasename=" + gameBaseName + 
						" and save_gamebasename=" + saveGameBaseName;

				List<BigInteger> ids = DatabaseHandler.listQuery(sql);
				log.info("HERE"+ids.get(0));


				try {
					result = gson.toJson(ids.get(0));
				} catch (Exception e) {
					log.info(e);
					result = gson.toJson(-1);
				}
			}
			return result;
		}


}