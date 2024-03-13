package java111;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
/** This class has general workout information 
 * It's attributes are used to describe and search for workouts
 * @author remllez
 */
public class Workout{
private static String csvPath = "data//Workout.csv";
public static HashMap<Integer, Workout> map = new HashMap<Integer, Workout>();
protected int workoutId; 
protected int templateId; 
protected String annotation;
protected Date date = new Date();
protected ArrayList<String> tags = new ArrayList<String>();
/** Create a workout object
 * @param templateId
 * @param workoutId
 */
Workout(int templateId, int workoutId)
{
    if (Template.map.containsKey(templateId)){
        this.templateId = templateId;
        this.workoutId = workoutId;
        Workout.map.putIfAbsent(workoutId, this);
    } else {System.out.println("Invalid templateId or workoutId");}
    
}

Workout(int templateId)
{
    if (Template.map.containsKey(templateId)){
        this.templateId = templateId;
    } else {System.out.println("No such template");}
    int newKey = map.size();
    while(map.containsKey(newKey)){
        newKey++;
    }
    this.workoutId = newKey;
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



/**
 * Get the templateId for this workout
 * @return
 */
public int getTemplateId(){
    return templateId;
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
 * return a string summarizing this object for CSV files
 */
public String toString(){
return templateId +
    "," + workoutId +
    ",\"" + User.dateFormat.format(date) +
    ",\"" + this.annotation +
    "\",\"" + this.getTags().toString().substring(1, tags.toString().length() - 1) + "\"";
}

 /* get a CSV friendly string representing this object's superclass
 * @return csvStr
 */
public String superToString(){
        return this.toString();
    }

//public void csvAppend(){
//    CsvHandler.csvAppendStr(csvPath, this.toString());
//}
}