package java111;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*; 


//TODO: all toStrength/toIsometric etc. need error handling for null values

/** 
 * @author Remllez
 * This is tools for handling CSV files
 */
public class CsvHandler{


public static void workoutLoad(String path)
{
    String file = path;
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine())!= null){
            Workout wo = strToWorkout(line);
            Main.workoutMap.putIfAbsent(wo.workoutId, wo);
        }
    }
    catch(Exception e){

    }
    finally {

    }
}

/**
 * Opens csv file and turns it's contents into strength objects
 * @param path
 */
public static void strengthLoad(String path)
{
    String file = path;
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine())!= null){
            Strength st = strToStrength(line);
            Workout wo = Main.workoutMap.get(st.workoutId);
            Main.strengthMap.putIfAbsent(st.workoutId, st);
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
 * Opens a csv file and turns it's contents into isometric objects
 * @param path
 */
public static void isometricLoad(String path)
{
    String file = path;
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine())!= null){
            Isometric iso = strToIsometric(line);
            Workout wo = Main.workoutMap.get(iso.workoutId);
            Main.isometricMap.putIfAbsent(iso.workoutId, iso);
            iso.setDate(wo.getDate());
            iso.setAnnotation(wo.getAnnotation());
        }
    }
    catch(Exception e){

    }
    finally {

    }
}


/**
 * Opens a csv file and turns it's contents into cardio objects
 * @param path
 */
public static void cardioLoad(String path)
{
    String file = path;
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine())!= null){
            Cardio cdo = strToCardio(line);
            Workout wo = Main.workoutMap.get(cdo.workoutId);
            Main.cardioMap.putIfAbsent(cdo.workoutId, cdo);
            cdo.setDate(wo.getDate());
            cdo.setAnnotation(wo.getAnnotation());
        }
    }
    catch(Exception e){
    }
    finally {
    }
}







final static Pattern quote = Pattern.compile("^\\s*\"((?:[^\"]|(?:\"\"))*?)\"\\s*,");

/**
 * This turns one line of a CSV file into a ArrayList
 * @param csvLine
 */
public static List<String> csvParse(String line) throws Exception
{       
    List<String> list = new ArrayList<String>();
    line += ",";
    for (int x = 0; x < line.length(); x++){
        String s = line.substring(x);
        if (s.trim().startsWith("\"")){
            Matcher m = quote.matcher(s);
            if (!m.find())
                throw new Exception("CSV is malformed");
            list.add(m.group(1).replace("\"\"", "\""));
            x += m.end() - 1;
        }
        else{
            int y = s.indexOf(",");
            if (y == -1)
                throw new Exception("CSV is malformed");
            list.add(s.substring(0, y));
            x += y;
        }
    } return list;
}

/**
 * This prints every line in the CSV file in human readable format
 * @param path
 */
public static void csvPrint(String path)
{
    String file = path;
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine())!= null){
            String[] row = csvParse(line).toArray(new String[0]);
            for(String index : row){
                System.out.printf("%-30s", index);
            }
            System.out.println();
        }
    }
    catch(Exception e){

    }
    finally {

    }
}


/**
 * get ArrayList<String> for a specific csv file line (aka 'lineNum') from file at 'path'
 * @param path
 * @param lineNum
 * @return rowArrayList
 */

public static ArrayList<String> getRow(String path, int lineNum)
{
try{
    Stream<String> lines = Files.lines(Paths.get(path));
    String ret =  lines.skip(lineNum).findFirst().get();
    ArrayList<String> retList = new ArrayList<String>();
for ( String str : csvParse(ret))
        retList.add(str);

    lines.close();
    return retList;
    }
catch(NullPointerException np) {System.out.println("Row does not exist");return null;}
catch(IOException io) {System.out.println("File does not exist");return null;}
catch(Exception e) {return null;}
}

public static String getRowStr(String path, int lineNum){
List<String> li = getRow(path,lineNum);
return li.stream().collect(Collectors.joining(","));

}

/**
 * This returns every value in the given column of the given CSV file
 * @param path
 * @param col
 * @return ArrayList<String>
 */
public static ArrayList<String> getCol(String path, int col)
{
    ArrayList<String> retList = new ArrayList<String>();
    BufferedReader reader = null;
    String line = "";
    try{
        reader = new BufferedReader(new FileReader(path));
        while((line = reader.readLine())!= null){
            retList.add(csvParse(line).toArray(new String[0])[col]);
        }
        reader.close();
        return retList;
    }
    catch(Exception e){
    }
        return null;
}

/**
 * This gets every values of the given column and checks if it matches an input String
 * @param path
 * @param col
 * @param checkString
 * @return boolean
 */
public static boolean checkUnique(String path, int col, String checkString){
    return getCol(path, col).contains(checkString)? false : true;
}

//public static int getMax(String path, int col){
    //return Collections.max(getCol(path, col));
//}


/**
 * This coverts a single row from a CSV file into a Workout object
 * @param line
 * @return WorkoutObject
 */
public static Workout strToWorkout(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    try{
    read = Arrays.asList(csvParse(csvStr).toArray(new String[0]));
    Workout wo = new Workout(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    wo.setDate(StrParse.toDate(read.get(2)));
    wo.setAnnotation(read.get(3));
    return wo;
    }
    catch(Exception e){}
    return null;
}
/**
 * This coverts a single row from a CSV file into a Strength object
 * @param line
 * @return StrengthObject
 */
public static Strength strToStrength(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(csvParse(csvStr).toArray(new String[0]));
    Strength st = new Strength(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    st.setWeight(StrParse.toWeight(read.get(2)));
    st.setSet(StrParse.toStrengthSet(read.get(3)));
    return st;
}

/**
 * This coverts a single row from a CSV file into a Isometric object
 * @param line
 * @return
 */
public static Isometric strToIsometric(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(csvParse(csvStr).toArray(new String[0]));
    Isometric iso = new Isometric(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    iso.setWeight(StrParse.toWeight(read.get(2)));
    iso.setSet(StrParse.toIsometricSet(read.get(3)));
    return iso;
    }

/**
 * This converts a csv string into a Cardio object
 * @param csvStr
 * @return
 * @throws Exception
 */
public static Cardio strToCardio(String csvStr) throws Exception
    {
    List<String> read = new ArrayList<String>();
    read = Arrays.asList(csvParse(csvStr).toArray(new String[0]));
    Cardio cdo = new Cardio(Integer.valueOf(read.get(0)),Integer.valueOf(read.get(1)));
    //cdo.setUnit(StrParse.toIsometricSet(read.get(3)));
    //String alphaStr = read.get(3).replaceAll("[^A-Za-z]+", "");
    cdo.setDistance(Double.parseDouble(read.get(2).replaceAll("[^\\d.]", "")));
    cdo.setTime(StrParse.toTime(read.get(3)));
    Unit unit; 
    switch(read.get(3).replaceAll("[^A-Za-z]+", "").toUpperCase()){
    case "KM":
    case "KILOMETER":
    case "K":
        unit = Unit.KM; 
        break;
    case "MI":
    case "MILES":
    case "MILE":
        unit = Unit.MI;
        break;
    default:
        unit = Unit.MI;
        break;
    }
    cdo.setUnit(unit);
    return cdo;
    }




/**
 * This appends a string onto the end of a CSV file
 * @param path
 * @param str
 */
public static void csvAppendStr(String path, String str){
    try(FileWriter writer = new FileWriter(path, true)){
        writer.write(str + "\n");
    } catch(Exception e){
    }
    }


/**
 * This appends .toString() data from Workouts onto pre-set csv files
 * @param workoutToAppend
 */
public static void csvAppend(Workout wo){
switch (wo.getClass().getSimpleName()) {
    case "Workout":
        csvAppendStr("data//Workout.csv",wo.toString());
        break;
    case "Strength":
        csvAppendStr("data//Strength.csv",wo.toString());
        csvAppendStr("data//Workout.csv",wo.superToString());
        break;
    case "Isometric":
        csvAppendStr("data//Isometric.csv",wo.toString());
        csvAppendStr("data//Workout.csv",wo.superToString());
        break;
    case "Cardio":
        csvAppendStr("data//Cardio.csv",wo.toString());
        csvAppendStr("data//Workout.csv",wo.superToString());
    }
}
}



