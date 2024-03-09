package java111;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Remllez
 * This class tests a few basic methods
 */
public class TestDrive{
public static void main(String[] args) throws Exception{



System.out.print(Cardio.Miles2Kilos(154));
System.out.print(Cardio.Kilos2Miles(154));
// Once the object is created, call it's toString() method to prove that it exists.
//System.out.println(c2.toString());

CsvHandler.templateLoad("data//Template.csv");
CsvHandler.workoutLoad("data//Workout.csv");
CsvHandler.isometricLoad("data//Isometric.csv");
CsvHandler.cardioLoad("data//Cardio.csv");
CsvHandler.strengthLoad("data//Strength.csv");


//Template benchPress = new Template();
//benchPress.setDesc("Bench Press");
////benchPress.csvAppend();

Template deadLift = new Template();
deadLift.setName("Deadlift");
deadLift.setDesc("Heavy asf");
deadLift.csvAppend();


Strength st1 = new Strength(3);
//ArrayList<Integer> reps = new ArrayList<Integer>();
st1.addReps(111);
st1.addReps(200);
st1.setWeight(StrParse.toWeight("199KG"));
st1.setAnnotation("good session");

Isometric is1 = new Isometric(0);
is1.setWeight(StrParse.toWeight("13lbs"));
is1.setSet(StrParse.toIsometricSet("2:30, 1:15 , 2:20, 8:30"));



//Strength s1 = new Strength(3, 9);
//s1.setWeight(StrParse.toWeight("25lbs"));
//s1.setAnnotation("I am in bed");
//s1.addReps(19);
//s1.addReps(16);
//s1.setDate(StrParse.toDate("1/2/2023"));
////System.out.println(s1.toString());
////System.out.println(s1.superToString());
////CsvHandler.csvAppend(s1);



//Main.templateMap.forEach((k, v) -> System.out.println(v.toString()));
//Main.cardioMap.forEach((k, v) -> System.out.println(v.toString()));
//Main.isometricMap.forEach((k, v) -> System.out.println(v.toString()));
//Main.strengthMap.forEach((k, v) -> System.out.println(v.toString()));



//Strength strength1 = Main.strengthMap.get(3);
//strength1.setSet(StrParse.toStrengthSet("9,9,9,9"));


CsvHandler.overWrite();









}
}