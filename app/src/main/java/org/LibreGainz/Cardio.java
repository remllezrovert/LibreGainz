package org.LibreGainz;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.io.*;
import java.net.http.HttpResponse;
import java.util.*;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
public class Cardio extends Workout{
    private static String csvPath = "data//Cardio.csv";
    public static HashMap<Long, Cardio> map = new HashMap<Long, Cardio>();
	protected double distance;
	private Time time;
	Unit distanceUnit;
    public Cardio(int templateId, long workoutId){
        super(templateId, workoutId);
        map.putIfAbsent(workoutId, this);
    }
    public Cardio(int templateId){
        super(templateId);
        map.putIfAbsent(workoutId, this);
    }
    public Cardio(){
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

public void setDistanceStr(String distance){
Unit unit;

switch(distance.replaceAll("[^A-Za-z]+", "").toUpperCase()){
    case "KM":
    case "KILOMETER":
    case "K":
        unit = Unit.KM; 
        break;
    case "MI":
    case "MILES":
    case "MILE":
        unit = Unit.MI;
        break;
    default:
        unit = Unit.MI;
        break;
    }
    setUnit(unit);
    setDistance(Double.parseDouble(distance.replaceAll("[^\\d.]", "")));
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
    public void setTime(Time newTime) {
        time = newTime;
    }

    /**
     * Get the time object corisponding to this Cardio object
     * @return
     */
    public Time getTime(){
        return time;
    }
  
    
    //user inputs if needs to edit a run 
    public void editRun(int setDistance, int setMinutes, int setSeconds){
        distance = setDistance;
        Time t1 = Time.valueOf(setMinutes + ":" + setSeconds);
        time = t1; 
    }

   /**
     * Remove this object from maps
     */
    public void deMap(){
        map.remove(workoutId);
        Workout.map.remove(workoutId);
    }
  
   /**
 * This converts a csv string into a Cardio object
 * @param csvStr
 * @return
 * @throws Exception
 */
public static Cardio csvParse(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(CsvHandler.csvParse(csvStr).toArray(new String[0]));
    Cardio cdo = new Cardio(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    cdo.setTime(Time.valueOf(read.get(3)));
    cdo.setDistanceStr(read.get(2));
    
    return cdo;
    }

/**
 * Opens a csv file and turns it's contents into cardio objects
 * @param path
 */
public static void csvLoad()
{
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(csvPath));
        while((line = reader.readLine())!= null){
            Cardio cdo = csvParse(line);
            Workout wo = Cardio.map.get(cdo.workoutId);
            cdo.setDate(wo.getDate());
            cdo.setAnnotation(wo.getAnnotation());
        }
    }
    catch(Exception e){
    }
    finally {
    }
}

   
    
    /**
     * Return a CSV friendly string representing this object
     */
    public String toString(){
        if (time != null)
        return super.templateId+
        "," + super.workoutId +
        "," + distance + distanceUnit + "," + time.toString();
        else

        return super.templateId+
        "," + super.workoutId +
        "," + distance + distanceUnit + "," + "null";
    }

    public String toString2() {
        String dateString = "0";
        String timeString; 
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(user.getDateFormatStr()); 
                dateString = dateFormat.format(date);
        }
        else {
             dateString = "null";
        }
        if (time != null) {
            timeString = time.toString();
              
        }
        else {
            timeString = "null";
        }
        return dateString + ",  " + Template.map.get(getTemplateId()).getName() +
        ",  " + distance + " " + distanceUnit + 
        ",  " + timeString;
    }
   
 public String buttonString() {
        String dateString = "0";
        String timeString; 
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(user.getDateFormatStr()); 
                dateString = dateFormat.format(date);
        }
        else {
             dateString = "null";
        }
        if (time != null) {
            timeString = time.toString();
              
        }
        else {
            timeString = "null";
        }
        return dateString + ",  " + Template.map.get(getTemplateId()).getName() +
        ",  " + distance + " " + distanceUnit;
    }
    
   

    /**
     * get a CSV friendly string representing this object's superclass
     * @return csvStr
     */
    public String superToString(){
    	
        return super.toString();
    }

    /**
     * GET all objects in the database
     * @return
     */
    public static List<Cardio> getRequestAll(){
        try {
            String urlString = Device.getBaseUrl() + "/cardio";
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all objects belonging to this user
     * @param user
     * @return cardioList
     */
    public static List<Cardio> getRequestUser(User user){
        try {
            String urlString = Device.getBaseUrl() + "/" + user.getId() + "/cardio";
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
    public static List<Cardio> getRequestId(Long workoutId){
        try {
            String urlString = Device.getBaseUrl() + "/cardio/" + String.valueOf(workoutId);
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Cardio> getRequest(String urlString){
        try {

            String jsonString = API.get(urlString);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Cardio> list = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Cardio.class));
            return list ; 
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

/**
 * POST the entire Cardio map
 * @return boolean
 */
public static boolean postRequestAll(){
    String urlString = Device.getBaseUrl() + "/cardio";
    ObjectMapper objectMapper = new ObjectMapper();
        try {
        API.post(urlString, objectMapper
            .writeValueAsString(new ArrayList<Cardio>(Cardio.map.values())));
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
    String urlString = Device.getBaseUrl() + "/" + userId + "/cardio";
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Cardio> list = new ArrayList<Cardio>();
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
        String urlString = Device.getBaseUrl() + "/cardio";
        ObjectMapper objectMapper = new ObjectMapper();
            try {
            API.patch(urlString, objectMapper
                .writeValueAsString(new ArrayList<Cardio>(Cardio.map.values())));
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
        String urlString = Device.getBaseUrl() + "/cardio/" + workoutId;
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Cardio> list = new ArrayList<Cardio>();
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










        public static List<Cardio> getRequestDate(User user, int templateId, Date startDate, Date endDate, int limit){
            try {
                String urlString = Device.getBaseUrl() + 
                "/" + user.getId() + 
                "/cardio/date/template?templateId=" + templateId +
                "&startDate=" + startDate +
                "&endDate=" + endDate +
                "&limit=" + limit;
                System.out.println(urlString);
                return getRequest(urlString); 
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static List<Cardio> getRequestDate(User user, Date startDate, Date endDate, int limit){
            try {
                String urlString = Device.getBaseUrl() + 
                "/" + user.getId() +
                "/cardio/date?startDate=" + startDate +
                "&endDate=" + endDate +
                "&limit=" + limit;
                return getRequest(urlString); 
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }










}