
package org.LibreGainz; 
import java.text.SimpleDateFormat;
/**
 * @author Remllez
 * This class is for users, which have a name and unqiue id.
 * Possible plans to impliment multi-user mode
 */

public class User{
    private static String dateFormatStr = "MM/dd/yyyy";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
    public static Unit weightUnit = Unit.LB;
    public static Unit longDistanceUnit = Unit.MI;
    public static Unit shortDistanceUnit = Unit.M;
    private static String baseUrl = "http://remllez.com:8080";
    private static int userId = 0;
    private static String name;

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

    public static String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String newBaseUrl) {
        baseUrl = newBaseUrl;
    }

    /**
     * Get the id of the user
     * @return userId
     */
    public static int getId(){
        return userId;
    }
    public static void setId(int id){
        userId = id;
    }
    
    public static void setWeightUnit(Unit newWeightUnit) {
    	weightUnit = newWeightUnit;
    }
    
    public static void setLongDistanceUnit (Unit newLongDistanceUnit) {
    	longDistanceUnit = newLongDistanceUnit;
    }
    public static void setShortDistanceUnit (Unit newShortDistanceUnit) {
    	shortDistanceUnit = newShortDistanceUnit;
    }
    
    public static Unit getWeightUnit() {
    	return weightUnit;
    }
    
    public static Unit getLongDistanceUnit() {
    	return longDistanceUnit;
    }
    
    public static Unit getShortDistanceUnit() {
    	return shortDistanceUnit;
    }
    
    
    
    
}
