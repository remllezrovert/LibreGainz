
package org.LibreGainz; 
import java.text.SimpleDateFormat;
/**
 * @author Remllez
 * This class is for users, which have a name and unqiue id.
 * Possible plans to impliment multi-user mode
 */

public class User{
    private String dateFormatStr = "MM/dd/yyyy";
    public SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
    public Unit weightUnit = Unit.LB;
    public Unit longDistanceUnit = Unit.MI;
    public Unit shortDistanceUnit = Unit.M;
    private int userId = 0;
    private String name;

    public User(String newName){
        name = newName;
    }

    public User(){}
    /**
     * Get the name of the user
     * @return name
     */
    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

       /**
     * Get the id of the user
     * @return userId
     */
    public int getId(){
        return userId;
    }
    public void setId(int id){
        userId = id;
    }
    
    public void setWeightUnit(Unit newWeightUnit) {
    	weightUnit = newWeightUnit;
    }
   
    public void setLongDistanceUnit (Unit newLongDistanceUnit) {
    	longDistanceUnit = newLongDistanceUnit;
    }
    public void setShortDistanceUnit (Unit newShortDistanceUnit) {
    	shortDistanceUnit = newShortDistanceUnit;
    }
    
    public Unit getWeightUnit() {
    	return weightUnit;
    }
    
    public Unit getLongDistanceUnit() {
    	return longDistanceUnit;
    }
    
    public Unit getShortDistanceUnit() {
    	return shortDistanceUnit;
    }
    
    
    
    
}
