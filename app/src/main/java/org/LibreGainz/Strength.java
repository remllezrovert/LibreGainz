package org.LibreGainz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * This class contains an ArrayList<Integer> of reps, and a weight
 */

public class Strength extends Workout{
    private static String csvPath = "data//Strength.csv";
    public static HashMap<Long, Strength> map = new HashMap<Long, Strength>();
    private ArrayList<Short> set = new ArrayList<Short>();
    private WeightObj weight;

    public Strength(int templateId, long workoutId){
        super(templateId, workoutId);
        this.workoutId = super.workoutId;
        map.putIfAbsent(workoutId, this);
    }

    Strength(int templateId){
        super(templateId);
        this.workoutId = super.workoutId;
        map.putIfAbsent(workoutId, this);
    }

    Strength(){
        super(0);
        map.putIfAbsent(workoutId, this);
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
    


public static ArrayList<Short> strToSet(String commaList){
    ArrayList<Short> retArr = new ArrayList<Short>();

    try{
    for (String str : commaList.split(",")) {
        str.replaceAll("[^0-9]", "");
        retArr.add(Short.parseShort(str.trim()));
    }
    }
    catch (NumberFormatException nfe){
        nfe.printStackTrace();
        return retArr;
    }

    return retArr;

    }



    
    /**
     * Add reps to the ArrayList<int>
     * @param newReps
     */
    public void addReps(short newReps){
        set.add(newReps);
    }
    /**
     * Delete reps from the ArrayList<int>
     * @param setIndex
     */
    public void delReps(short setIndex){
        set.remove(setIndex);
    }
    /**
     * Edit the ArrayList<int>
     * @param setIndex
     * @param newReps
     */
    public void editReps(int setIndex, short newReps){
        set.set(setIndex, newReps);
    }
    /**
     * Replace the ArrayList<int> with a new ArrayList<int>
     * @param newSet
     */
    public void setSet(ArrayList<Short> newSet){
        set = newSet;
    }
    /**
     * Get the rep ArrayList<int>
     * @return
     */
    public ArrayList<Short> getSet(){
        return set;
    }
    
    /**
     * Set the weight for this workout
     * @param newWeight
     */
    public void setWeight(WeightObj newWeight){
        this.weight = newWeight;
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
 * This coverts a single row from a CSV file into a Strength object
 * @param line
 * @return StrengthObject
 */
public static Strength csvParse(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(CsvHandler.csvParse(csvStr).toArray(new String[0]));
    Strength st = new Strength(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    st.setWeight(WeightObj.strToWeight(read.get(2)));
    st.setSet(strToSet(read.get(3)));
    return st;
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
            Strength st = csvParse(line);
            Workout wo = Workout.map.get(st.workoutId);
            st.setDate(wo.getDate());
            st.setAnnotation(wo.getAnnotation());
        }
    }
    catch(Exception e){

    }
    finally {

    }
}




    /**
     * Get the CSV friendly String that represents this object 
     * @return String 
     */
    public String toString(){

        return super.templateId+
        "," + super.workoutId +
        "," + weight.toString() +
        ",\"" + set.toString().substring(1, set.toString().length() - 1) + "\"";
    }

    public String toString2() {
        String dateString = "0";
        String weightString;
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(user.getDateFormatStr()); 
                dateString = dateFormat.format(date);
        }
        else {
             dateString = "null";
        }
        if (weight != null) {
            weightString = weight.toString();
              
        }
        else {
            weightString = "null";
        }
        return dateString + 
        ",  " + Template.map.get(getTemplateId()).getName() + 
        ",  " + weightString + "  " + set.toString().substring(1, set.toString().length() - 1).replaceAll(",", ", ");
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
    public static List<Strength> getRequestAll(){
        try {
            String urlString = Device.getBaseUrl() + "/strength";
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all objects belonging to this user
     * @param user
     * @return strengthList
     */
    public static List<Strength> getRequestUser(User user){
        try {
            String urlString = Device.getBaseUrl() + "/" + user.getId() + "/strength";
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static List<Strength> getRequestDate(User user, int templateId, Date startDate, Date endDate, int limit){
        try {
            String urlString = Device.getBaseUrl() + 
            "/" + user.getId() + 
            "/strength/date/template?templateId=" + templateId +
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



    public static List<Strength> getRequestDate(User user, Date startDate, Date endDate, int limit){
        try {
            String urlString = Device.getBaseUrl() + 
            "/" + user.getId() +
            "/strength/date?startDate=" + startDate +
            "&endDate=" + endDate +
            "&limit=" + limit;
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
    public static List<Strength> getRequestId(Long workoutId){
        try {
            String urlString = Device.getBaseUrl() + "/strength/" + String.valueOf(workoutId);
            return getRequest(urlString); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Strength> getRequest(String urlString){
        try {

            String jsonString = API.get(urlString);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Strength> list = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Strength.class));
            return list ; 
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





/**
 * POST the entire Strength map
 * @return boolean
 */
public static boolean postRequestAll(){
    String urlString = Device.getBaseUrl() + "/strength";
    ObjectMapper objectMapper = new ObjectMapper();
        try {
        API.post(urlString, objectMapper
            .writeValueAsString(new ArrayList<Strength>(Strength.map.values())));
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
    String urlString = Device.getBaseUrl() + "/" + userId + "/strength";
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Strength> list = new ArrayList<Strength>();
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
        String urlString = Device.getBaseUrl() + "/strength";
        ObjectMapper objectMapper = new ObjectMapper();
            try {
            API.patch(urlString, objectMapper
                .writeValueAsString(new ArrayList<Strength>(Strength.map.values())));
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    
    
    /**
     * PATCH single object (this)
     * @return
     */
    public boolean patchRequest(){
        String urlString = Device.getBaseUrl() + "/strength/" + workoutId;
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Strength> list = new ArrayList<Strength>();
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


