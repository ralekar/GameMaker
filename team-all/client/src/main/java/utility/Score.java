/**
 * 
 */
package utility;

/**
 * @author Nikhil
 *
 */
public class Score {

    private static Score instance;
    private int scoreValue = -1;
    
    private Score(){
	scoreValue = 0;
    }
    
    public static synchronized Score getInstance(){
	return((instance == null)? (instance = new Score()) : instance);
    }
    
    public int getScore(){
        return scoreValue;
    }        
    
    public synchronized void increament(){
	scoreValue++;
    }       
    
    public synchronized void decrease(){
	scoreValue--;
    }      
    
    public synchronized void modifyBy(int modificationValue){
	scoreValue += modificationValue;
    }
    
    public synchronized void resetScore(){
	scoreValue = 0;
    }

	public void setScore(int scoreValue) {
		this.scoreValue = scoreValue;
	}   
   
}

