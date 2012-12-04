package chat;

import java.util.List;

public class ChatUsersMonitor {
	
	private ChatUsersSender chatUsersSender;
	private ChatUsersReceiver chatUsersReceiver;
	private static ChatUsersMonitor instance;

	private ChatUsersMonitor(){
		chatUsersReceiver = new ChatUsersReceiver();
		chatUsersSender = new ChatUsersSender(chatUsersReceiver);
		Thread chatSenderThread = new Thread(chatUsersSender);
		chatSenderThread.start();
		Thread chatReceiverThread = new Thread(chatUsersReceiver);
		chatReceiverThread.start();
	}
	
	public static ChatUsersMonitor getInstance(){
		if(instance == null){
			instance = new ChatUsersMonitor();
		}
		return instance;
	}
	
	public List<String> getActiveUsers(){
		return chatUsersReceiver.getActiveUsers();
	}

	public ChatUsersSender getChatUsersSender() {
		return chatUsersSender;
	}

	public void setChatUsersSender(ChatUsersSender chatUsersSender) {
		this.chatUsersSender = chatUsersSender;
	}

	public ChatUsersReceiver getChatUsersReceiver() {
		return chatUsersReceiver;
	}

	public void setChatUsersReceiver(ChatUsersReceiver chatUsersReceiver) {
		this.chatUsersReceiver = chatUsersReceiver;
	}

}
