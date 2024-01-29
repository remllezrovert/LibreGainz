package java111;

/**
 * @author remllez
 */

enum Unit{
    KG,
    LB;
}
public class WeightObj{
int weight;
Unit unit;
WeightObj(int weight, Unit unit){
    this.weight = weight;
    this.unit = unit;
}
/**
 * Converts pounds to kilograms
 * @param lb
 * @return
 */
public static int lb2Kg(int lb){
    return (int)Math.round(lb * 2.20462);
}
/**
 * Converts kilograms to pounds
 * @param kg
 * @return
 */
public static int kg2Lb(int kg){
    return (int)Math.round(kg * 0.453592);
}
public String toString(){
    return weight + this.unit.toString();
}
public int getWeight(){
    return weight;
}
public void setWeight(){

}
public Unit getUnit(){
    return this.unit;
}
/**
 * Convert this WeightObj from kg to lb, or vise-versa.
 */
public void convert(){
    switch(unit){
    case KG:
        this.weight = kg2Lb(weight);
        this.unit = Unit.LB;
        break;
    case LB:
        this.weight = lb2Kg(weight);
        this.unit = Unit.KG;
        break;
    }
}
}
