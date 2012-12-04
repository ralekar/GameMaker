package controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import db.DatabaseHandler;
import db.User;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.GET)
	@ResponseBody
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Gson gson = new Gson();
		List<?> loginQueryList = DatabaseHandler.loginQuery(username, password);
		if (loginQueryList.isEmpty()) {
			return gson.toJson(false);

		} else {
			return gson.toJson(true);
		}
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	@ResponseBody
	public String register(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Gson gson = new Gson();
		boolean ret = DatabaseHandler.save(new User(username, password, ""));
		return gson.toJson(ret);
	}
}
