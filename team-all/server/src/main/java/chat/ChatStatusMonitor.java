package chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatStatusMonitor {
	
	private static ChatStatusMonitor instance;
	private CompletionService<String> pool;
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ChatStatusMonitor.class);
	
	private ChatStatusMonitor(){
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		pool = new ExecutorCompletionService<String>(threadPool);
		
		
	}
	
	public static ChatStatusMonitor getInstance(){
		if(instance == null){
			instance = new ChatStatusMonitor();
		}
		return instance;
	}
	
	public List<String> getActiveUsers(){
		SenderTask senderTask = new SenderTask();
		ReceiverTask receiverTask = new ReceiverTask();
		pool.submit(senderTask);
		pool.submit(receiverTask);
		
		try {
			String senderResult = pool.take().get();
			String receiverResult = pool.take().get();
			return receiverTask.getActiveUsers();
		} catch (InterruptedException e) {
			LOG.debug(e.getMessage());
			return new ArrayList<String>(); //returning an empty list in case of an exception
			
		} catch (ExecutionException e) {
			LOG.debug(e.getMessage());
			return new ArrayList<String>(); //returning an empty list in case of an exception
		}
	}

}
