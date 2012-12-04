package controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import chat.ChatStatusMonitor;
import chat.ChatUsersMonitor;

@Controller
public class ActiveChatUsers {

	@RequestMapping(value = "/getActiveUsers", method = RequestMethod.GET)
	@ResponseBody
	public String getActiveUsers(){
		List<String> activeUsers = ChatUsersMonitor.getInstance().getActiveUsers();
		Gson gson = new Gson();
		String users = gson.toJson(activeUsers);
		return users;
	}
	
	@RequestMapping(value = "/getActiveChatUsers", method = RequestMethod.GET)
	@ResponseBody
	public String getActiveChatUsers(){
		List<String> activeUsers = ChatStatusMonitor.getInstance().getActiveUsers();
		Gson gson = new Gson();
		String users = gson.toJson(activeUsers);
		return users;
	}

}
