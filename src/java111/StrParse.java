package java111;
import java.util.ArrayList;
/**
 * @author Remllez
 * This class converts Strings into useful objects and ArrayLists
 */



 // TODO: Parse Date strings into objects and csv/sql readable formats

public class StrParse{
/**
 * Create time objects using strings 
 * @param timeStr
 * @return TimeObj
 */
public static TimeObj toTime(String timeStr) {
    int[] tempArr = {0,0};
    int i = 0;
    for (String str : timeStr.split(":")) {
        int newInt = Integer.parseInt(str.trim());
        tempArr[i] = newInt;
        i += 1;
    }
    return new TimeObj(tempArr[0], tempArr[1]);
}

/**
 * Convert a string into a weight object
 * @param str
 * @return WeightObj
 */
public static WeightObj toWeight(String str){
    StringBuffer unitBuffer = new StringBuffer(), weightBuffer = new StringBuffer();
    for (int i=0; i<str.length();i++){
        if (Character.isDigit(str.charAt(i))) 
            weightBuffer.append(str.charAt(i));
        else if (Character.isAlphabetic(str.charAt(i)))
            unitBuffer.append(str.charAt(i));
    }
    Unit unit; 
    switch(unitBuffer.toString().toLowerCase()){
    case "kg":
    case "kgs":
    case "kilo":
    case "kilos":
        unit = Unit.KG; 
        break;
    case "lb":
    case "lbs":
    case "pounds":
        unit = Unit.LB;
        break;
    default:
        unit = Unit.LB;
        break;
    }
    WeightObj retObj = new WeightObj(Integer.valueOf(weightBuffer.toString()), unit);
    return retObj;
}

/**
 * Convert a String into an array of reps ArrayList<int> 
 * @param commaList
 * @return ArrayList<Integer>
 */
public static ArrayList<Integer> toStrengthSet(String commaList){
    ArrayList<Integer> retArr = new ArrayList<Integer>();
    for (String str : commaList.split(","))
        retArr.add(Integer.parseInt(str.trim()));
    return retArr;
}
/**
 * Convert a String into an ArrayList<TimeOj>
 * @param commaList
 * @return
 */
public static ArrayList<TimeObj> toIsometricSet(String commaList){
    ArrayList<TimeObj> retArr = new ArrayList<TimeObj>();
    for (String str : commaList.split(",")){
        retArr.add(toTime(str.trim()));
    }
    return retArr;
}



}