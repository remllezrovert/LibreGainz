package org.LibreGainz; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
@JsonIgnoreProperties(ignoreUnknown = true)


/**
 * @author Remllez
 * This class is for users, which have a name and unqiue id.
 * Possible plans to impliment multi-user mode
 */

public class User{
    private String dateFormatStr = "MM/dd/yyyy";
    //Remove this value becaues it is causing an error on the backend
    //public SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
    public Unit weightUnit = Unit.LB;
    public Unit longDistanceUnit = Unit.MI;
    private int userId = 0;
    private String name;
    private static String csvPath = "data//User.csv";
     @JsonProperty("timestamp")
    private Timestamp timeStamp;
    
    public static HashMap<Integer,User> map = new HashMap<Integer,User>();


    public User(String newName){
        name = newName;
        int newKey = map.size();
        while(map.containsKey(newKey)){
            newKey++;
        }
        userId = newKey;
 
        map.putIfAbsent(userId, this);
    }

    public User(){

    }
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
   
    public Unit getWeightUnit() {
    	return weightUnit;
    }
    
    public Unit getLongDistanceUnit() {
    	return longDistanceUnit;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

 /**
     * Get the path where the csv file for the object is saved
     * @return
     */
    public static String getCsvPath(){
        return csvPath;
    }
    /**
     * Set the path where this csv file is saved
     * @return
     */
    public static void setCsvPath(String newCsvPath){
        csvPath = newCsvPath;
    }



    public void csvAppend(){
        CsvHandler.csvAppendStr(csvPath, this.toString());
    }


    
    public boolean postRequest(){
    ObjectMapper objectMapper = new ObjectMapper();
        try {
        String str = API.post(Device.getBaseUrl() + "/user",
        objectMapper.writeValueAsString(this));
        //System.out.println("new user id for " + name + " is " + str);
        userId = Integer.parseInt(str);
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Fix this later. change backend too. This wont work for people named things like kyle.f1lthy because of the period in the url
    //find a way to send parameters in get requests without putting them on the url path
    public static User getRequestName(String name){
        try{
        String jsonString = API.get(Device.getBaseUrl() + "/user?name=" + name);
         ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonString, User.class);
            return user; 
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static User getRequestId(int id){
        try{
         String jsonString = API.get(Device.getBaseUrl() + "/user/" + id);
         ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonString, User.class);
            return user; 
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String getDateFormatStr() {
        return dateFormatStr;
    }

    public void setDateFormatStr(String dateFormatStr) {
        this.dateFormatStr = dateFormatStr;
    }

/**
 * This coverts a single row from a CSV file into a User object
 * @param line
 * @return StrengthObject
 */
public static User csvParse(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(CsvHandler.csvParse(csvStr).toArray(new String[0]));
    User u = new User(read.get(0));
    u.setId(Integer.valueOf(read.get(1)));
    u.setDateFormatStr(read.get(2));
    u.setLongDistanceUnit(Unit.valueOf(read.get(3)));
    u.setWeightUnit(Unit.valueOf(read.get(4)));
    return u;
}


/**
 * Opens csv file and turns it's contents into strength objects
 * @param path
 */
public static void csvLoad()
{
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(csvPath));
        while((line = reader.readLine())!= null){
            User u = csvParse(line);
        }
    }
    catch(Exception e){

    }
    finally {

    }
}

 public static void csvOverwrite(){
        CsvHandler.csvWipe(csvPath);
        map.forEach((k,v) -> v.csvAppend());
    }



    public String toString(){
    return 
    name + "," +
    userId + "," + 
    dateFormatStr + "," +
    longDistanceUnit.toString() + "," +
    weightUnit.toString() + ",";
    }

}
    

    
    
    
    
    
