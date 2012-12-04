package utility;

import java.awt.Color;

public class Constants 
{
	
	//TO-DO - To remove constants which are never used.
	
	public static final int FRAME_WIDTH = 1150;
	public static final int FRAME_HEIGHT = 700;
	public static final int BG_CONTENT_R = 205;
	public static final int BG_CONTENT_G = 205;
	public static final int BG_CONTENT_B = 195;
	public static final int BOARD_X = 120;
	public static final int BOARD_Y = 140;
	public static final int BOARD_HEIGHT = 620;
	public static final int BOARD_WIDTH = 600;
	public static  int CONTROL_PANEL_WIDTH = 500;
	public static  int CONTROL_PANEL_LENGTH = 700;
	public static final int PADDLE_WIDTH = 20;
	public static final int PADDLE_HEIGHT = 100;
	public static final int BRICK_COUNT = 6;
	public static final int BRICK_WIDTH = 40;
	public static final int BRICK_HEIGHT = 40;
	public static final int BALL_WIDTH = 20;
	public static final int BALL_HEIGHT = 20;
	public static final int CLOCK_WIDTH = 100;
	public static final int CLOCK_HEIGHT = 50;
	public static final int CLOCK_X=820;
	public static final int CLOCK_Y=140;
	public static final int CLOCK_BG_R=160;
	public static final int CLOCK_BG_G=180;
	public static final int CLOCK_BG_B=140;
	public static final Color CLOCK_FG_COLOR=Color.blue;
	public static final int STOPPED_FONT_SIZE=20;
	public static final Color STOPPED_COLOR = Color.BLACK;
	public static final int BUTTON_WIDTH = 80;
	public static final int BUTTON_HEIGHT = 40;
	public static final int START_X = 850;
	public static final int START_Y = 150;
	public static final int UNDO_X = 850;
	public static final int UNDO_Y = 250;
	public static final int REPLAY_X = 850;
	public static final int REPLAY_Y = 350;
	public static final int RESET_X = 850;
	public static final int RESET_Y = 400;
	public static final int SAVE_X = 850;
	public static final int SAVE_Y = 200;
	public static final int LOAD_X = 850;
	public static final int LOAD_Y = 300;
	public static final int BG_BOARD_R = 210;
	public static final int BG_BOARD_G = 180;
	public static final int BG_BOARD_B = 140;
	public static final int BALL_R = 142;
	public static final int BALL_G = 35;
	public static final int BALL_B = 35;
	public static final int PADDLE_R = 93;
	public static final int PADDLE_G = 71;
	public static final int PADDLE_B = 139;
	public static final int BRICK_R = 238;
	public static final int BRICK_G = 99;
	public static final int BRICK_B = 99;
	
	
	public static final int PADDLE_OFFSET = 25;
	public static final int PADDLE_INTIAL_XVALUE = 145;
	public static final int PADDLE_INTIAL_YVALUE = 575;
	public static final int BALL_INTIAL_XVALUE = 185;
	public static final int BALL_INTIAL_YVALUE = 553;
	public static final int WINDOW_LENGTH = 500;
	public static final int WINDOW_WIDTH = 600;
	public static final int BALL_SIZE = 20;
	public static final int BALL_INTIAL_XVELOCITY = 3;
	public static final int BALL_INITIAL_YVELOCITY = -3;
	public static final int PADDLE_PRECISION = 30;
	
	public static final int BOARD_OFFSET = 0;
	
	public static final int TIMER_DELAY = 10;
	
	
	public static final String DISAPPEAR = "Disappear";
	public static final String MOVE_UP = "MoveUp";
	public static final String MOVE_DOWN = "MoveDown";
	public static final String MOVE_LEFT = "MoveLeft";
	public static final String MOVE_RIGHT = "MoveRight";
	public static final String TOGGLE_X = "ToggleX";
	public static final String TOGGLE_Y = "ToggleY";
	public static final String SOUND = "Sound";
	public static final String KEY_PRESS_UP = "KeyPressUp";
	public static final String KEY_PRESS_DOWN = "KeyPressDown";
	public static final String KEY_PRESS_LEFT = "KeyPressLeft";
	public static final String KEY_PRESS_RIGHT = "KeyPressRight";
	public static final String TIMER_EVENT = "TimerEvent";
	public static final String COLLIDE = "Collide";
	public static final String ActiveMQConnect = "tcp://129.79.247.5:61616?wireFormat.maxInactivityDuration=0";
	public static final double HEADING_AMOUNT = 10;
	public static final int MINIMUM_FRAMEWIDTH = 700;
	public static final int MINIMUM_FRAMEHEIGHT = 600;
	public static final int PROPERTY_PANEL_WIDTH = 300;
	public static final int IMAGE_PANEL_WIDTH = 180;
	public static final double SPRITE_X = 200;
	public static final double SPRITE_Y = 200;
	

        public static String DefaultAvatar = "http://fluency.knownspace.org/student-files/fall2012/a10/team-all/server/facebook/default_user.jpg";
        public static String FacebookServer = "http://mayurmasrani.com/facebook"; // to be changed to fluency server below. 
       	//public static String FacebookServer = "http://fluency.knownspace.org/student-files/fall2012/a10/team-all/server/facebook";
	public static String NEW_LAYER = "New Layer";
	public static String ALL_LAYERS = "All Layers";
	public static String SELECT_LAYER="Please select the layer";
	
	public static boolean isMultiplayer = false;
	public static boolean isGameMaker = false;
	public static boolean isGamePlayer = false;
	public static boolean isHost = false;
	public static boolean isHosted = false;
	

	
	public static final String spriteAddedText1 = "You just added a sprite here.";
	public static final String spriteAddedText2 = "You can drag it around.";
	public static final String spriteAddedText3 = "Scroll the object resize.";
	public static final String spriteAddedText4 = "Right click on the object to get more options.";
	public static final String propertyText = "Change the properties of the selected object here.";
	public static final String eventActionText = "Add event and actions to the object here.";


	public static final String HOST =  "tintin.cs.indiana.edu:8097";
	public static final String PATH = "/finalproject";
	
//	public static final String HOST =  "localhost:8097";
//	public static final String PATH = "/finalproject";
    
	public static final double PIXEL_TO_CENTIMETER_PARAM = 0.026458333 ;
	public static final double CENTIMETER_TO_PIXEL_PARAM = 37.795275591 ;
}
