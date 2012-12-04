package view.communication;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import model.Resources;
import org.apache.http.NameValuePair;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import view.communication.protocol.GameSaveInfo;
import view.utils.HttpUtil;

@RunWith( PowerMockRunner.class)
@PrepareForTest( HttpUtil.class)
public class ClientHandlerTest {

    private final static Logger log = Logger.getLogger(ClientHandlerTest.class.getName());

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testListAllGameBases() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            String[] values = {"Game1", "Game2", "Game3", "Game4"};
            Gson g = new Gson();
            String gameListJson = g.toJson(values);
            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(gameListJson);

            String[] value = ClientHandler.listAllGameBases("fake:123", "/123");
            log.info("first game name=" + value[0]);
            assertEquals(value[0], "Game1");
            assertEquals(value[1], "Game2");
            assertEquals(value[2], "Game3");

        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListAllMultiPlayerGameBases() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new String[]{"Game1", "Game2", "Game3", "Game4"});
            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            String[] value = ClientHandler.listAllGameBases("fake:123", "/123");
            assertEquals(value[0], "Game1");
            assertEquals(value[2], "Game3");

        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testLoadGameBase() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {

            String gameData = "This is the game data being mocked";
            XStream xStream = new XStream();
            String gameDataXML = xStream.toXML(gameData);
            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(gameDataXML);

            String actualResult = ClientHandler.loadGameBase("testgame", "fake:123", "/123");
            assertEquals(gameDataXML, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testSaveGameBase() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            boolean returnData = true;

            Gson gson = new Gson();
            PowerMockito.when(HttpUtil.class, HttpUtil.httpPost((URI) (Matchers.any()), (List<NameValuePair>) (Matchers.any()))).thenReturn(gson.toJson(returnData));

            boolean actualResult = ClientHandler.saveGameBase("test", "test", "test", false, "fake:123", "/123");
            assertEquals(returnData, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListAllGamePlays() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new GameSaveInfo[]{new GameSaveInfo("test", "test", "test", "test")});

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            GameSaveInfo[] actualResult = ClientHandler.listAllGamePlays("test", "fake:8080", "/123");
            assertEquals("test", actualResult[0].getGameName());
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testLoadGamePlay() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            String returnData = "game data";

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            String actualResult = ClientHandler.loadGamePlay(1, "fake:8080", "/123");
            assertEquals(returnData, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testSaveGamePlay() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            boolean returnData = true;

            Gson gson = new Gson();
            PowerMockito.when(HttpUtil.class, HttpUtil.httpPost((URI) (Matchers.any()), (List<NameValuePair>) (Matchers.any()))).thenReturn(gson.toJson(returnData));

            boolean actualResult = ClientHandler.saveGamePlay(new GameSaveInfo("test", "test", "test", "test"), "fake:123", "/123");
            assertEquals(returnData, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListTopScores() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new GameSaveInfo[]{new GameSaveInfo("test", "test", "test", 1, 100)});

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            GameSaveInfo[] actualResult = ClientHandler.listTopScores("test", "fake:123", "/123");
            assertEquals(100, actualResult[0].getGameScore());
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testUserLogin() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            boolean returnData = true;
            Gson gson = new Gson();
            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(gson.toJson(returnData));

            boolean actualResult = ClientHandler.userLogin("test", "test", "fake:123", "123");
            assertEquals(returnData, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testUserRegister() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            boolean returnData = true;
            Gson gson = new Gson();
            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(gson.toJson(returnData));

            boolean actualResult = ClientHandler.userRegister("test", "test", "fake:123", "123");
            assertEquals(returnData, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListPageResources() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new Resources[]{new Resources("test", "test", new byte[]{1}, "test")});

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            Resources[] actualResult = ClientHandler.listPageResources("1", "5", "test", "fake:123", "/123");
            assertEquals("test", actualResult[0].getResourceType());
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testSaveResource() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            boolean returnData = true;

            Gson gson = new Gson();
            PowerMockito.when(HttpUtil.class, HttpUtil.httpPost((URI) (Matchers.any()), (List<NameValuePair>) (Matchers.any()))).thenReturn(gson.toJson(returnData));

            boolean actualResult = ClientHandler.saveResource(new Resources(), "fake:123", "/123");
            assertEquals(returnData, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testLoadResource() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new Resources("test", "test", new byte[]{1}, "test"));

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            Resources actualResult = ClientHandler.loadResource("1", "fake:123", "/123");
            assertEquals("test", actualResult.getResourceType());
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testCountTag() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(5);

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            int actualResult = ClientHandler.countTag("test", "fake:123", "/123");
            assertEquals(5, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListTags() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new String[]{"tag1", "tag2", "tag3"});

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            String[] actualResult = ClientHandler.listTags("fake:123", "/123");
            assertEquals("tag2", actualResult[1]);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testDeleteHostedGameBase() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(true);

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            boolean actualResult = ClientHandler.deleteHostedGameBase("test", "fake:123", "/123");
            assertEquals(true, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testGetHostedGameBaseId() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(25);

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            int actualResult = ClientHandler.getHostedGameBaseId("test", "test", "test", "fake:123", "/123");
            assertEquals(25, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testInsertHostedGame() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(false);

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            boolean actualResult = ClientHandler.insertHostedGame("test", "test", "test", "fake:123", "/123");
            assertEquals(false, actualResult);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testLoadHostGames() {
        PowerMockito.mockStatic(HttpUtil.class);

        try {
            Gson gson = new Gson();
            String returnData = gson.toJson(new String[]{"game1", "game2"});

            PowerMockito.when(HttpUtil.class, HttpUtil.httpGet((URI) (Matchers.any()))).thenReturn(returnData);

            String[] actualResult = ClientHandler.loadHostGames("fake:123", "/123");
            assertEquals("game2", actualResult[1]);
        } catch (Exception e) {
            log.info(e.toString());
            Assert.fail(e.toString());
        }
    }
}