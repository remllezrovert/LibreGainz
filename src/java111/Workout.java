package java111;
import java.util.Date;

//TODO: Fix the constructor's workoutId system so that it only contains workout objects (no strength etc.)
/** This class has general workout information 
 * It's attributes are used to describe and search for workouts
 * @author remllez
 */
public class Workout{
protected int workoutId;
protected int templateId;
protected String annotation;
protected Date date = new Date();

/** Create a workout object
 * @param templateId
 * @param workoutId
 */
Workout(int templateId, int workoutId)
{
    if (Main.templateMap.containsKey(templateId)){
        this.templateId = templateId;
        this.workoutId = workoutId;
        Main.workoutMap.putIfAbsent(workoutId, this);
    } else {System.out.println("Invalid templateId or workoutId");}


    
}

Workout(int templateId)
{
    if (Main.templateMap.containsKey(templateId)){
        this.templateId = templateId;
    } else {System.out.println("No such template");}
    int newKey = Main.workoutMap.size();
    while(Main.workoutMap.containsKey(newKey)){
        newKey++;
    }
    this.workoutId = newKey;
    Main.workoutMap.putIfAbsent(workoutId, this);
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

/**
     * Remove this object from maps
     */
    public void deMap(){
        Main.workoutMap.remove(workoutId);
    }
  

/**
 * return a string summarizing this object for CSV files
 */
public String toString(){
return templateId +
    "," + workoutId +
    ",\"" + User.dateFormat.format(date) +
    "\",\"" + annotation + "\"";
}

 /* get a CSV friendly string representing this object's superclass
 * @return csvStr
 */
public String superToString(){
        return super.toString();
    }
}