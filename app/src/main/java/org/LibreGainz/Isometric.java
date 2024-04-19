package org.LibreGainz;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import java.io.*;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/** @author Remllez * This class stores an ArrayList<Time> and a weight object.  */
public class Isometric extends Workout{
    private static String csvPath = "data//Isometric.csv";
    public static HashMap<Long, Isometric> map = new HashMap<Long, Isometric>();
    public Isometric(int templateId, long workoutId){
        super(templateId, workoutId);
        map.putIfAbsent(workoutId, this);
    }

    public Isometric(int templateId){
    super(templateId);
    map.putIfAbsent(workoutId, this);
    }

    public Isometric(){
        super();
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

    private WeightObj weight;
    private ArrayList<Time> set = new ArrayList<Time>();

    /**
     * Add a time to the time ArrayList
     * @param newTime
     */
    public void addTime(String newTime){
        set.add(Time.valueOf(newTime));
    }
    /**
     * Delete at time from the time ArrayList
     * @param timeIndex
     */
    public void delTime(int timeIndex){
        set.remove(timeIndex);
    }
    /**
     * Edit a time in the time ArrayList
     * @param timeIndex
     * @param newTime
     */
    public void editTime(int timeIndex, String newTime){
        set.set(timeIndex, Time.valueOf(newTime));
    }

/**
 * Convert a String into an ArrayList<TimeOj>
 * @param commaList
 * @return
 */
public static ArrayList<Time> strToSet(String commaList){
    ArrayList<Time> retArr = new ArrayList<Time>();
    try{
    for (String str : commaList.split(",")){
        retArr.add(Time.valueOf(str.trim()));
    }
    return retArr;
    } catch(Exception e) {
    e.printStackTrace();
    return null;
    }
}




    /**
     * Replace the time ArrayList with new ArrayList
     * @param newSet
     */
    public void setSet(ArrayList<Time> newSet){
        set = newSet;
    }
    /**
     * Get the Object's time ArrayList
     * @return ArrayList<Time>
     */
    public ArrayList<Time> getSet(){
        return set;
    }


/**
 * Set the weight for this workout
 * @param newWeight
 */
public void setWeight(WeightObj newWeight){
    weight = newWeight;
}
/**
 * Get the weight for this workout session
 * @return
 */
public WeightObj getWeight(){
    return weight;
}
    /**
     * Remove this object from maps
     */
    public void deMap(){
        map.remove(workoutId);
        Workout.map.remove(workoutId);
    }
/**
 * This coverts a single row from a CSV file into a Isometric object
 * @param line
 * @return
 */
public static Isometric csvParse(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(CsvHandler.csvParse(csvStr).toArray(new String[0]));
    Isometric iso = new Isometric(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    iso.setWeight(WeightObj.strToWeight(read.get(2)));
    iso.setSet(strToSet(read.get(3)));
    return iso;
    }


 /**
 * Opens a csv file and turns it's contents into isometric objects
 * @param path
 */
public static void csvLoad()
{
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(csvPath));
        while((line = reader.readLine())!= null){
            Isometric iso = csvParse(line);
            Workout wo = map.get(iso.workoutId);
            iso.setDate(wo.getDate());
            iso.setAnnotation(wo.getAnnotation());
        }
    }
    catch(Exception e){

    }
    finally {
    }
}

 

    /**
     * Return a string for use in CSV files
     * @return String
     */
    public String toString(){
        return super.templateId +
        "," + super.workoutId +
        "," + weight.toString() +
        ",\"" + set.toString().substring(1, set.toString().length() - 1) + "\"";
    }


 /* get a CSV friendly string representing this object's superclass
 * @return csvStr
 */
    public String superToString(){
        return super.toString();
    }
   
    /**
     * GET all objects in the database
     * @return
     */
    public static List<Isometric> getRequestAll(){
        try {
            String urlString = Device.getBaseUrl() + "/isometric";
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all objects belonging to this user
     * @param user
     * @return isometricList
     */
    public static List<Isometric> getRequestUser(User user){
        try {
            String urlString = Device.getBaseUrl() + "/" + user.getId() + "/isometric";
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Get request to find objects using workoutId
     * @param workoutId
     * @return
     */
    public static List<Isometric> getRequestId(int workoutId){
        try {
            String urlString = Device.getBaseUrl() + "/isometric/" + String.valueOf(workoutId);
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Isometric> getRequest(String urlString){
        try {

            String jsonString = API.get(urlString);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Isometric> list = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Isometric.class));
            return list ; 
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
/**
 * POST the entire Isometric map
 * @return boolean
 */
public static boolean postRequestAll(){
    String urlString = Device.getBaseUrl() + "/isometric";
    ObjectMapper objectMapper = new ObjectMapper();
        try {
        API.post(urlString, objectMapper
            .writeValueAsString(new ArrayList<Isometric>(Isometric.map.values())));
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


/**
 * POST single object (this)
 * @return
 */
public boolean postRequest(){
    String urlString = Device.getBaseUrl() + "/" + userId + "/isometric";
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Isometric> list = new ArrayList<Isometric>();
    list.add(this);
        try {
        API.post(urlString, objectMapper
            .writeValueAsString(list));
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean patchRequestAll(){
        String urlString = Device.getBaseUrl() + "/isometric";
        ObjectMapper objectMapper = new ObjectMapper();
            try {
            API.patch(urlString, objectMapper
                .writeValueAsString(new ArrayList<Isometric>(Isometric.map.values())));
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    
    
    /**
     * POST single object (this)
     * @return
     */
    public boolean patchRequest(){
        String urlString = Device.getBaseUrl() + "/" + userId + "/isometric";
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Isometric> list = new ArrayList<Isometric>();
        list.add(this);
            try {
            API.patch(urlString, objectMapper
                .writeValueAsString(list));
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
            return true;
        }



    
}