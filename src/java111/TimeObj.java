package java111;
/**
 * @author Evan L
 * This object manages time
 */
public class TimeObj{
    public int minutes;
    public int seconds;
    public TimeObj(int minutes, int seconds) 
    {
    	
    	if (seconds < 0) {
    		seconds = seconds * -1;
    		//throw new IllegalArgumentException("Seconds cannot be less than 0");
    	}
    	if (minutes < 0) {
    		minutes = minutes * -1;
    		//throw new IllegalArgumentException("Minutes cannot be less than 0");
    	}
    	
    	if (seconds > 59){
    		minutes ++;
    		seconds = seconds - 60;
    	}
    	
        this.minutes = minutes;
        this.seconds = seconds;
     
    }
    /**
     * @return timeStr 
     */
    public String toString(){
    	
    	if (seconds < 10) {
    		return minutes + ":0" + seconds;
    	}
    	
    	else
        return minutes + ":" + seconds;
    
    	}
    
}

