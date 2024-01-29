package java111;
import java.util.ArrayList;
import java.io.FileWriter;
/** This class has general workout information 
 * It's attributes are used to describe and search for workouts
 * @author remllez
 */
public class Workout{
protected int workoutId;
protected int templateId;
protected String annotation;
protected String date;
public static ArrayList<Workout> allWorkouts = new ArrayList<Workout>();
// make this change on every other class aswell
// do this so that it's easier to construct class from csv files.

/** Create a workout object
 * @param templateId
 * @param workoutId
 */
Workout(int templateId, int workoutId){
    this.templateId = templateId;
    this.workoutId = workoutId;
    allWorkouts.add(this);
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
public void setDate(String newDate){
    date = newDate;
}
/**
 * Get the date for this workout session
 * @return
 */
public String getDate(){
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

/**
 * return a string summarizing this object for CSV files
 */
public String toString(){
return templateId +
    "," + workoutId +
    ",\"" + date +
    ",\"" + annotation + "\"";
}

 /* get a CSV friendly string representing this object's superclass
 * @return csvStr
 */
public String superToString(){
        return super.toString();
    }
}