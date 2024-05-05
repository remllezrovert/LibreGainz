package org.LibreGainz;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/** This class has general workout information 
 * It's attributes are used to describe and search for workouts
 * @author remllez
 */
public class Workout{
private static String csvPath = "data//Workout.csv";
private Template template;
public static HashMap<Long, Workout> map = new HashMap<Long, Workout>();
protected int templateId; 
protected long workoutId; 

protected int userId;
protected User user = Device.getUser();


protected String annotation;
protected Date date = new Date();
protected ArrayList<String> tags = new ArrayList<String>();
/** Create a workout object
 * @param templateId
 * @param workoutId
 */
public Workout(int templateId, long workoutId)
{
    if (Template.map.containsKey(templateId)){
        this.templateId = templateId;
        this.workoutId = workoutId;
        Workout.map.putIfAbsent(workoutId, this);
        this.template = Template.map.get(templateId);
        template.setWorkoutType(this.getClass().getSimpleName());
    } else {System.out.println("Invalid templateId or workoutId");}
    
}

Workout(int templateId)
{
    if (Template.map.containsKey(templateId)){
        this.templateId = templateId;
    } else {System.out.println("No such template");}
    long newKey = map.size();
    while(map.containsKey(newKey)){
        newKey++;
    }
    this.workoutId = newKey;
    map.putIfAbsent(workoutId, this);
}
Workout(){

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

    /**
     * Get the id of the workout object
     * @return
     */
public long getId(){
    return this.workoutId;
}


public int getUserId() {
    return userId;
}
public void setUserId(int userId) {
    this.userId = userId;
}
public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
    this.userId = user.getId();
}




/**
 * Set the workoutId
 * @override
 * @param workoutId
 */
public void setId(long workoutId) {
    this.workoutId = workoutId;
}
/**
 * Get the templateId for this workout
 * @return
 */
public int getTemplateId(){
    return templateId;
}

public void setTemplateId(int templateId) {
    this.templateId = templateId;
}
/**
 * Set the date for this workout session
 * @param newDate
 */
public void setDate(Date newDate){
    date = newDate;
}
/**
 * Get the date for this workout session
 * @return
 */
public Date getDate(){
    return date;
}

/**
 * Add an annotation to this workout session
 * @param newAnnotation
 */
public void setAnnotation(String newAnnotation){
    annotation = newAnnotation;
}





/**
 * Get the annotation for this workout session
 * @return
 */
public String getAnnotation(){
    return annotation;
}
public ArrayList<String> getTags(){
    return tags;
}


/**
 * Convert comma separted list into an array of strings, or 'tags' for templates
 * @param commaList
 * @return
 */
public static ArrayList<String> strToTags(String commaList){
    ArrayList<String> retArr = new ArrayList<String>();
    for (String str : commaList.split(","))
        retArr.add(str.trim());
    return retArr;
}



/**
 * Replace the current tags array with a new one
 * @param newTags
 */
public void setTags(ArrayList<String> newTags){
tags = newTags;
}
/**
 * Add a new tag to the tag ArrayList<String>
 * @param newTag
 */
public void addTag(String newTag){
tags.add(newTag);
}
/**
 * Delete a tag from the ArrayList<String>
 * @param tagIndex
 */
public void delTag(int tagIndex){
tags.remove(tagIndex);
}


/**
     * Remove this object from maps
     */
    public void deMap(){
        map.remove(workoutId);
    }
  
/**
 * This coverts a single row from a CSV file into a Workout object
 * @param line
 * @return WorkoutObject
 */
public static Workout csvParse(String csvStr) throws Exception
    {

    List<String> read = new ArrayList<String>();
    try{
    read = Arrays.asList(CsvHandler.csvParse(csvStr).toArray(new String[0]));
    Workout wo = new Workout(Integer.valueOf(read.get(1)),Integer.valueOf(read.get(2)));
    wo.setUserId(Integer.valueOf(read.get(0)));
    wo.setUser(User.map.get(wo.getUserId()));
    SimpleDateFormat dateFormat = new SimpleDateFormat(wo.user.getDateFormatStr());
    wo.setDate(dateFormat.parse(read.get(3)));
    wo.setAnnotation(read.get(4));
    wo.setTags(Workout.strToTags(read.get(5)));
    return wo;
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return null;
}

/**
 * This loads a csv file
 * @param path
 */
public static void csvLoad()
{
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(csvPath));
        while((line = reader.readLine())!= null){
            csvParse(line);
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


    /**
     * GET all objects in the database
     * @return
     */
    public static <T extends Workout> List<T> getRequestAll(){
        try {
            String urlString = Device.getBaseUrl() + "/workout";
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Workout> List<T> getRequest(String urlString){
        try {

            String jsonString = API.get(urlString);
            ObjectMapper objectMapper = new ObjectMapper();
            List<T> list = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Workout.class));
            return list ; 
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Get request to find objects using workoutId
     * @param workoutId
     * @return
     */
    public static <T extends Workout> List<T> getRequestId(Long workoutId){
        try {
            String urlString = Device.getBaseUrl() + "/workout/" + String.valueOf(workoutId);
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * PATCH single object (this)
     * @return
     */
    public boolean patchRequest(){
        String urlString = Device.getBaseUrl() + "/workout/" + workoutId;
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Workout> list = new ArrayList<Workout>();
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







public void deleteRequest(){
    API.delete(Device.getBaseUrl() + "/" + userId +"/workout/" + workoutId);
}

public static void deleteRequest(Long workoutId){
    API.delete(Device.getBaseUrl() + "/workout/" + workoutId);
}





/**
 * return a string summarizing this object for CSV files
 */
public String toString(){
SimpleDateFormat dateFormat = new SimpleDateFormat(user.getDateFormatStr());
return 
    userId + "," +
    templateId + "," +
    "," + workoutId +
    ",\"" + dateFormat.format(date) +
    ",\"" + this.annotation +
    "\",\"" + this.getTags().toString().substring(1, tags.toString().length() - 1) + "\"";
}

public String toString2(){
    return "null";
}

public String buttonString(){
SimpleDateFormat dateFormat = new SimpleDateFormat(user.getDateFormatStr());
return 
    ",\"" + dateFormat.format(date);
}





 /* get a CSV friendly string representing this object's superclass
 * @return csvStr
 */
public String superToString(){
        return null; 
    }

public void csvAppend(){
    CsvHandler.csvAppendStr(csvPath, this.toString());
    CsvHandler.csvAppendStr(Workout.getCsvPath(), superToString());
}






}