package org.LibreGainz;
import java.util.*;
import java.io.*;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Remllez
 * TODO: add GET request that uses templateId
 */ 
public class Template{
private static String csvPath = "data//Template.csv";
public static HashMap<Integer, Template> map = new HashMap<Integer, Template>();
private int templateId;
private String name;
private String desc;
private String workoutType = "Workout";
private int userId;

public Template(){
    int newKey = map.size();
    while(map.containsKey(newKey)){
        newKey++;
    }
    templateId = newKey;
    map.putIfAbsent(templateId, this);
}

public Template(int templateId){
    this.templateId = templateId;
    map.putIfAbsent(templateId, this);
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
     * Get the userId
     * @return
     */
    public int getUserId(){
        return userId;
    }
    /**
     * Set the userID
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * get the name of the workout class associated with this 
     * @return workoutType
     */
    public String getWorkoutType() {
        return workoutType;
    }
    /**
     * set the name of the workout class associated with this
     * @param workoutType
     */
    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
/**
 * Get the id of this template
 * @return templateId
 */
public int getId(){
    return templateId;
}
/**
 * set the templateId
 * @param templateId
 */
public void setId(int templateId){
    this.templateId = templateId;
}
/**
 * Gets the display name for the template
 * @return
 */
public String getName(){
    return name;
}
/**
 * Sets the display name for the template
 * @param newName
 */
public void setName(String newName){
    name = newName;
}


/**
 * Get a string describing the workout
 * @return
 */
public String getDesc(){
return desc;
}
/**
 * Set the description for this object
 * @param newDesc
 */
public void setDesc(String newDesc){
this.desc = newDesc;
}


/**
 * Remove this from HashMap. 
 */
public void deMap(){
    //Need to impliment a cascading delete
    //All workouts using this template should be deleted if this template is
    map.remove(templateId);
}

/**
 * Convert a csvString into a template object
 * @param csvStr
 * @return
 * @throws Exception
 */
public static Template csvParse(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    try{
    read = Arrays.asList(CsvHandler.csvParse(csvStr).toArray(new String[0]));
    Template t1 = new Template(Integer.parseInt(read.get(0)));
    t1.setName(read.get(1));
    t1.setWorkoutType(read.get(2));
    t1.setDesc(read.get(3));
    //t1.setTags(Workout.strToTags(read.get(3)));
    return t1;
    }
    catch(Exception e){}
    return null;
}


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
 * Get a String representing this object for use in CSV files
 */
public String toString(){
return templateId +
    ",\"" + name + "\"" + 
    ",\"" + workoutType + "\"" + 
    ",\"" + desc + "\"";
}



public String toString2(){
return name + ", "+
workoutType + ", " +
desc;

}
/*
 * Append the toString() for this object onto a CSV file.
 */
public void csvAppend(){
    try(FileWriter writer = new FileWriter("data//Template.csv", true)){
        writer.write(this.toString()+"\n");
    } catch(Exception e){

    }
}
    /**
     * This will get every template in the server database
     * @return
     */
    public static List<Template> getRequestAll(){
        try {
            String urlString = Device.getBaseUrl() + "/template";
            String jsonString = API.get(urlString);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Template> list = objectMapper.readValue(jsonString, 
                objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Template.class));
            return list ; 
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * This will POST every template in the map. Probably unnecessary
     * @return boolean
     */
    public static boolean postRequestAll(){
    String urlString = Device.getBaseUrl() + "/" + Device.getUser().getId() + "/template";
    ObjectMapper objectMapper = new ObjectMapper();
        try {
        API.post(urlString, objectMapper.writeValueAsString(new ArrayList<Template>(map.values())));
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
    String urlString = Device.getBaseUrl() + "/" + userId + "/template";
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Template> list = new ArrayList<Template>();
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

public void deleteRequest(){
    API.delete(Device.getBaseUrl() + "/" + userId +"/template/" + templateId);
}

public static void deleteRequest(Long templateId){
    API.delete(Device.getBaseUrl() + "/template/" + templateId);
}






}
