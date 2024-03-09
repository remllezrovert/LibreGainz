package java111;
import java.text.DecimalFormat;
public class Cardio extends Workout{
	protected double distance;
	private TimeObj time;
	Unit distanceUnit;

    Cardio(int templateId, int workoutId){
        super(templateId, workoutId);
        Main.cardioMap.putIfAbsent(workoutId, this);
    }
    Cardio(int templateId){
        super(templateId);
        Main.cardioMap.putIfAbsent(workoutId, this);
    }

   

    //set the units for the distance ran 
    public void setUnit(Unit newUnit) {
    	this.distanceUnit = newUnit; 
    }
    
    /** 
     * 
     * @return distanceUnit
     */
    public Unit getUnit() {
    	return distanceUnit; 
    }
    
    /**
     * sets the distance ran 
     * @param distance1
     */
  
 
    public void setDistance(double distance1) {
    	distance = distance1;
    }

    /**
     * get the distance ran
     * @return distance
     */
    public double getDistance() {
        return distance;
    }
    
    //Convert miles to kilos
    public static String Miles2Kilos(double miles){
    	DecimalFormat df = new DecimalFormat("0.000");
    	double kilometers = miles *1.609344;
        return df.format(kilometers);
    }
    
    public static String Kilos2Miles(double kilos){
    	DecimalFormat df = new DecimalFormat("0.000");
        double miles = kilos * .62137119;
        return df.format(miles);
    }

   
    /**
     * input minutes and seconds as parameters for time object
     * @param minutes
     * @param seconds
     */
    public void setTime(TimeObj newTime) {
        time = newTime;
    }

    /**
     * Get the time object corisponding to this Cardio object
     * @return
     */
    public TimeObj getTime(){
        return time;
    }
  
    
    //user inputs if needs to edit a run 
    public void editRun(int setDistance, int setMinutes, int setSeconds){
        distance = setDistance;
        TimeObj t1 = new TimeObj(setMinutes, setSeconds);
        time = t1; 
    }

   /**
     * Remove this object from maps
     */
    public void deMap(){
        Main.cardioMap.remove(workoutId);
        Main.workoutMap.remove(workoutId);
    }
  
   
    
    /**
     * Return a CSV friendly string representing this object
     */
    public String toString(){
        return super.templateId+
        "," + super.workoutId +
        "," + distance + distanceUnit + "," + time.toString();
    }
    /**
     * get a CSV friendly string representing this object's superclass
     * @return csvStr
     */
    public String superToString(){
    	
        return super.toString();
    }

}