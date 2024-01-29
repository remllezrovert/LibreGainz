package java111;
/**
 * @author remllez
 * This object manages time
 */
public class TimeObj{
    public int minutes;
    public int seconds;
    public TimeObj(int minutes, int seconds){
        this.minutes = minutes;
        this.seconds = seconds;
    }
    /**
     * @return timeStr 
     */
    public String toString(){
        return minutes + ":" + seconds;
    }
}

