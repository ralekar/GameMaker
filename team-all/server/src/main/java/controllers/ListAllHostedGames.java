package controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import db.DatabaseHandler;

@Controller
public class ListAllHostedGames {

	private static Logger log = Logger.getLogger(ListAllHostedGames.class);
	@RequestMapping(value = "/loadHostGames", method = RequestMethod.GET)
	@ResponseBody
	public String loadHostGames() {

		String sql = "select save_gamebasename from HostedGameBases";
		List<String> game = DatabaseHandler.listQuery(sql);
		log.debug("Inside loadhosted games" + game);

/*		List<String> result = new ArrayList<String>();
		result.add("hello");*/

		Gson gson = new Gson();
		String json = gson.toJson(game);            
		return json;		
	}
}

