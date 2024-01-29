package java111;
import java.util.ArrayList;
import java.io.FileWriter;


/** @author Remllez
 * This class stores an ArrayList<TimeObj> and a weight object.
 */
public class Isometric extends Workout{
    public static ArrayList<Isometric> allIsometric = new ArrayList<Isometric>();
    Isometric(int templateId, int workoutId){
        super(templateId, workoutId);
        allIsometric.add(this);
    }
    private WeightObj weight;
    private ArrayList<TimeObj> set = new ArrayList<TimeObj>();

    /**
     * Add a time to the time ArrayList
     * @param newTime
     */
    public void addTime(String newTime){
        set.add(StrParse.toTime(newTime));
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
        set.set(timeIndex, StrParse.toTime(newTime));
    }
    /**
     * Replace the time ArrayList with new ArrayList
     * @param newSet
     */
    public void setSet(ArrayList<TimeObj> newSet){
        set = newSet;
    }
    /**
     * Get the Object's time ArrayList
     * @return ArrayList<TimeObj>
     */
    public ArrayList<TimeObj> getSet(){
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
     * Return a string for use in CSV files
     * @return String
     */
    public String toString(){
        return super.getTemplateId() +
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

}