package controllers;

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
import db.HostedGameBaseRecord;

@Controller
public class InsertDeleteRecordsController {

	private static Logger log = Logger.getLogger(InsertDeleteRecordsController.class);

//	@RequestMapping(value = "/deleteHostedGameBaseRecord", method = RequestMethod.GET)
//	@ResponseBody
//	public String deleteHostedGameBaseRecord(@RequestParam("gameId") String gameId) {
//
//		Gson gson = new Gson();
//
//		boolean deleteOK = false;
//
//		if (gameId != null) {
//
//			String sql = "select count(*) FROM HostedGameBases where id=" + gameId;
//			String sql1 = "delete from HostedGameBases where id="+gameId;
//
//			List<BigInteger> count = DatabaseHandler.Query(sql);
//			log.info(count);
//
//			if (count.get(0).intValue() < 1) {
//				deleteOK = false;
//			} else {
//				DatabaseHandler.ExecuteQuery(sql1);
//				deleteOK = true;
//			}
//
//			log.info("deleted entry id: " + gameId);
//			return gson.toJson(deleteOK);
//		} else {
//			return gson.toJson(false);
//		}
//
//	}
	@RequestMapping(value = "/deleteHostedGameBaseRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteHostedGameBaseRecord(@RequestParam("hostname") String hostName) {

		Gson gson = new Gson();

		boolean deleteOK = false;

		if (hostName != null) {

			//String sql = "select count(*) FROM HostedGameBases where id=" + gameId;
			String sql1 = "delete from HostedGameBases where hostname='" + hostName + "'";

			DatabaseHandler.executeQuery(sql1);
			
			log.info("deleted entry name: " + hostName);
			return gson.toJson(deleteOK);
		} else {
			return gson.toJson(false);
		}

	}

	@RequestMapping(value = "/insertHostedGameBaseRecord", method = RequestMethod.GET)
	@ResponseBody
	public String insertHostedGameBaseRecord(@RequestParam("hostname") String hostName,
			@RequestParam("gamebasename") String gameBaseName,
			@RequestParam("save_gamebasename") String saveGameBaseName){
		Gson gson = new Gson();
		Session session = DatabaseHandler.getDatabaseHandlerInstance().getHibernateSession();
		Transaction t = session.beginTransaction();
		HostedGameBaseRecord hostedGameBaseRecord = 
				new HostedGameBaseRecord(hostName, gameBaseName, saveGameBaseName);
		session.save(hostedGameBaseRecord);
		t.commit();
		session.close();
		gson.toJson(true);
		return gson.toJson(true);

	}
}