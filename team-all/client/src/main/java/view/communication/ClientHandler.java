package view.communication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.Resources;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import view.communication.protocol.GameSaveInfo;
import view.utils.HttpUtil;

public class ClientHandler {

    private final static Logger log = Logger.getLogger(ClientHandler.class);

    public static String[] listAllGameBases(String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();

        String jsonGameList = HttpUtil.httpGet(uri);

        Gson gson = new Gson();

        String[] gameNames = gson.fromJson(jsonGameList, String[].class);

        return gameNames;

    }

    public static String[] listAllMultiPlayerGameBases(String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();

        String jsonGameList = HttpUtil.httpGet(uri);

        Gson gson = new Gson();

        String[] gameNames = gson.fromJson(jsonGameList, String[].class);

        return gameNames;
    }

    public static String loadGameBase(String gameName, String host, String path) throws Exception {
        if (gameName.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("game_name", gameName);
        URI uri = ub.build();

        String gameDataXml = HttpUtil.httpGet(uri);

        return gameDataXml;
    }

    public static boolean saveGameBase(String gameName, String gameAuthor, String gameData, Boolean isMultiPlayer, String host, String path) throws Exception {

        if (gameName.isEmpty() || gameAuthor.isEmpty()
                || gameData.isEmpty()) {
            throw new Exception("Required information is empty");
        }
        String isMultiPlayerString = isMultiPlayer.toString();
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();

        List<NameValuePair> nvps = new ArrayList<NameValuePair>(4);
        nvps.add(new BasicNameValuePair("game_name", gameName));
        nvps.add(new BasicNameValuePair("game_author", gameAuthor));
        nvps.add(new BasicNameValuePair("game_data", gameData));
        nvps.add(new BasicNameValuePair("isMultiPlayer", isMultiPlayerString));
        String json = HttpUtil.httpPost(uri, nvps);

        Gson gson = new Gson();
        Boolean saveOK = gson.fromJson(json, Boolean.class);
        log.info("Save check " + saveOK);
        return saveOK.booleanValue();


    }

    public static GameSaveInfo[] listAllGamePlays(String playerName, String host, String path) throws Exception {

        if (playerName.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("player_name", playerName);
        URI uri = ub.build();

        String json = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        GameSaveInfo[] gameSaves = gson.fromJson(json, GameSaveInfo[].class);

        return gameSaves;
    }

    public static String loadGamePlay(int gameSaveId, String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http")
                .setHost(host)
                .setPath(path)
                .setParameter("game_save_id",
                new Integer(gameSaveId).toString());
        URI uri = ub.build();

        String gameDataXml = HttpUtil.httpGet(uri);

        return gameDataXml;

    }

    public static boolean saveGamePlay(GameSaveInfo gameSave, String host, String path) throws Exception {
        if (gameSave.getSaveName().isEmpty()
                || gameSave.getGameName().isEmpty()
                || gameSave.getGamePlayer().isEmpty()
                || gameSave.getGameData().isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();

        List<NameValuePair> nvps = new ArrayList<NameValuePair>(4);
        nvps.add(new BasicNameValuePair("game_save_name", gameSave
                .getSaveName()));
        nvps.add(new BasicNameValuePair("game_name", gameSave.getGameName()));
        nvps.add(new BasicNameValuePair("game_player_name", gameSave
                .getGamePlayer()));
        nvps.add(new BasicNameValuePair("game_score", new Integer(gameSave
                .getGameScore()).toString()));
        nvps.add(new BasicNameValuePair("game_data", gameSave.getGameData()));

        String json = HttpUtil.httpPost(uri, nvps);

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);

        Boolean saveOK = gson.fromJson(json, Boolean.class);
        reader.close();
        return saveOK.booleanValue();
    }

    public static GameSaveInfo[] listTopScores(String gameName, String host, String path) throws Exception {
        if (gameName.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("game_name", gameName);
        URI uri = ub.build();

        String json = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        GameSaveInfo[] gameSaves = gson
                .fromJson(json, GameSaveInfo[].class);

        return gameSaves;
    }

    public static boolean userLogin(String username, String password, String host, String path) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("username", username)
                .setParameter("password", password);
        URI uri = ub.build();

        String json = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        Boolean loginOK = gson.fromJson(json, Boolean.class);

        return loginOK.booleanValue();
    }

    public static boolean userRegister(String username, String password, String host, String path) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("username", username)
                .setParameter("password", password);
        URI uri = ub.build();

        String json = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        Boolean loginOK = gson.fromJson(json, Boolean.class);

        return loginOK.booleanValue();
    }

    public static Resources[] listPageResources(String pageNumber, String pageLength, String resourceName, String host, String path) throws Exception {
        if (pageNumber.isEmpty() || pageLength.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        try {
            Integer.parseInt(pageNumber);
            Integer.parseInt(pageLength);
        } catch (Exception ex) {
            throw ex;
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("page_number", pageNumber)
                .setParameter("page_length", pageLength);
        if (resourceName != null) {
            ub.setParameter("resource_name", resourceName);
        }
        URI uri = ub.build();

        String json = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        Resources[] resources = gson.fromJson(json, Resources[].class);

        return resources;
    }

    public static boolean saveResource(Resources resource, String host, String path) throws Exception {
        if (resource == null) {
            throw new Exception("Required information is empty");
        }

        Gson gson = new Gson();
        String r = gson.toJson(resource);

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();

        List<NameValuePair> nvps = new ArrayList<NameValuePair>(1);
        nvps.add(new BasicNameValuePair("resource", r));

        String json = HttpUtil.httpPost(uri, nvps);
        Boolean saveOK = gson.fromJson(json, Boolean.class);

        return saveOK.booleanValue();
    }

    public static Resources loadResource(String resourceId, String host, String path) throws Exception {
        if (resourceId.isEmpty()) {
            throw new Exception("Required information is empty");
        }

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path)
                .setParameter("resource_id", resourceId);
        URI uri = ub.build();

        String json = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        Resources resources = gson.fromJson(json, Resources.class);

        return resources;
    }

    public static int countTag(String resourceName, String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        if (resourceName != null) {
            ub.setParameter("tag", resourceName);
        }
        URI uri = ub.build();
        String data = HttpUtil.httpGet(uri);
        int count = Integer.parseInt(data);
        return count;
    }
    
    public static List<String> getActiveUsers(String host, String path) throws Exception{
    	URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();
        String json = HttpUtil.httpGet(uri);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> users = gson.fromJson(json, type);
        if(!users.contains(Player.getInstance().getUsername())){
			users.add(Player.getInstance().getUsername());
		}
        return users;
    }

    public static String[] listTags(String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();
        String json = HttpUtil.httpGet(uri);
        Gson gson = new Gson();
        String[] tags = gson.fromJson(json, String[].class);
        return tags;
    }

    private static boolean deleteHostedGameBase(int gameId, String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path).setParameter("gameId", new Integer(gameId).toString());
        URI uri = ub.build();

        //should be using deleteHostedGameBaseRecord
        String response = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        Boolean deleteOK = gson.fromJson(response, Boolean.class);

        return deleteOK.booleanValue();
    }

    public static boolean deleteHostedGameBase(String hostName, String host, String path) throws Exception {
        //TODO: figure out an id based on hostname, gamebasename, savegamebasename
        //parameters: (null, Constants.HOST, Constants.PATH+"/countTag", new Exception[1])
        //int id = 2;
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path).setParameter("hostname", hostName);
        URI uri = ub.build();

        //should be using deleteHostedGameBaseRecord
        String response = HttpUtil.httpGet(uri);

        Gson gson = new Gson();
        Boolean deleteOK = gson.fromJson(response, Boolean.class);

        return deleteOK.booleanValue();
    }

    public static int getHostedGameBaseId(String hostName, String gameBaseName, String saveGameBaseName, String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").
                setHost(host).
                setPath(path).
                setParameter("hostname", hostName).
                setParameter("gamebasename", gameBaseName).
                setParameter("save_gamebasename", saveGameBaseName);
        URI uri = ub.build();


        String jsonId = HttpUtil.httpGet(uri);

        Gson gson = new Gson();

        int gameId = gson.fromJson(jsonId, int.class);

        return gameId;
    }

    public static boolean insertHostedGame(String hostName, String gameBaseName, String saveGameBaseName,
            String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").
                setHost(host).
                setPath(path).
                setParameter("hostname", hostName).
                setParameter("gamebasename", gameBaseName).
                setParameter("save_gamebasename", saveGameBaseName);
        URI uri = ub.build();


        String jsonOK = HttpUtil.httpGet(uri);

        Gson gson = new Gson();

        boolean insertOK = gson.fromJson(jsonOK, boolean.class);

        return insertOK;
    }

    public static String[] loadHostGames(String host, String path) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost(host).setPath(path);
        URI uri = ub.build();

        String jsonGameList = HttpUtil.httpGet(uri);

        Gson gson = new Gson();

        String[] gameNames = gson.fromJson(jsonGameList, String[].class);

        return gameNames;
    }

    private ClientHandler() {
    }
}
