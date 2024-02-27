package java111;
import java.util.ArrayList;

/**
 * This class contains an ArrayList<Integer> of reps, and a weight
 */
public class Strength extends Workout{
    private ArrayList<Integer> set = new ArrayList<Integer>();
    private WeightObj weight;

    public Strength(int templateId, int workoutId){
        super(templateId, workoutId);
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

}