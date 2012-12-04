package utility;

public class enums {

	public enum Layouts {
	    FLOW, BOX
    }
	public enum Sounds {
		PaddleHit, BrickBlow, Win
	}
	
	public enum GameState{
		INITIAL,RUNNING,PAUSED,STOPPED,REPLAY,UNDOING
	}
	
	public enum Direction {
		LEFT, RIGHT, TOP, BOTTOM
	}
	
	public enum ImageSource{
		PRIMARY,SECONDARY
	}
	public enum playerModes{
		PLAYER1,PLAYER2
	}
	
	public enum PropertyField{
		SPRITE_NAME("Sprite Name"),GROUP_NAME("Group Name"),VELOCITY_X("Velocity X"),
		VELOCITY_Y("Velocity Y"),WIDTH("Width"),HEIGHT("Height");
		
		private final String text;
		private PropertyField(final String text) {
	        this.text = text;
	    }
	    @Override
	    public String toString() {
	        // TODO Auto-generated method stub
	        return text;
	    }
	}
}

