package java111;

import java.util.ArrayList;

public class Cardio extends Workout{
	protected double distance;
	private TimeObj time;
	Unit distanceUnit;

    public Cardio(int templateId, int workoutId){
        super(templateId, workoutId);
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
     * deletes distance if needs to be edited 
     * @param newDistance
     */
    public void delDistance(int newDistance){
        distance -= newDistance;
    }
    /**
     * input minutes and seconds as parameters for time object
     * @param minutes
     * @param seconds
     */
    public void setTime(int minutes, int seconds) {
    	TimeObj t1 = new TimeObj(minutes, seconds);
    	time = t1;
    }
  
    
    //user inputs if needs to edit a run 
    public void editRun(int setDistance, int setMinutes, int setSeconds){
        distance = setDistance;
        TimeObj t1 = new TimeObj(setMinutes, setSeconds);
        time = t1; 
    }
   
  
   
    
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