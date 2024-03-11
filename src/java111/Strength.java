package java111;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class contains an ArrayList<Integer> of reps, and a weight
 */
public class Strength extends Workout{
    private static String csvPath = "data//Strength.csv";
    public static HashMap<Integer, Strength> map = new HashMap<Integer, Strength>();
    private ArrayList<Integer> set = new ArrayList<Integer>();
    private WeightObj weight;

    Strength(int templateId, int workoutId){
        super(templateId, workoutId);
        this.workoutId = super.workoutId;
        map.putIfAbsent(workoutId, this);
    }

    Strength(int templateId){
        super(templateId);
        this.workoutId = super.workoutId;
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
     * Add reps to the ArrayList<int>
     * @param newReps
     */
    public void addReps(int newReps){
        set.add(newReps);
    }
    /**
     * Delete reps from the ArrayList<int>
     * @param setIndex
     */
    public void delReps(int setIndex){
        set.remove(setIndex);
    }
    /**
     * Edit the ArrayList<int>
     * @param setIndex
     * @param newReps
     */
    public void editReps(int setIndex, int newReps){
        set.set(setIndex, newReps);
    }
    /**
     * Replace the ArrayList<int> with a new ArrayList<int>
     * @param newSet
     */
    public void setSet(ArrayList<Integer> newSet){
        set = newSet;
    }
    /**
     * Get the rep ArrayList<int>
     * @return
     */
    public ArrayList<Integer> getSet(){
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
     * Get the CSV friendly String that represents this object 
     * @return String 
     */
    public String toString(){

        return super.templateId+
        "," + super.workoutId +
        "," + weight.toString() +
        ",\"" + set.toString().substring(1, set.toString().length() - 1) + "\"";
    }
    /**
     * get a CSV friendly string representing this object's superclass
     * @return csvStr
     */
    public String superToString(){
        return super.toString();
    }

    public void csvAppend(){
        CsvHandler.csvAppendStr(csvPath, this.toString());
        CsvHandler.csvAppendStr(super.getCsvPath(), super.toString());
    }

}