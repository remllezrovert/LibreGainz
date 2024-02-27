package java111;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Remllez
 * This class tests a few basic methods
 */
public class TestDrive{
public static void main(String[] args) throws Exception{


Template benchPress = new Template("BenchPress");
benchPress.csvAppend();

Template deadLift = new Template("DeadLift");
deadLift.csvAppend();


Strength st1 = new Strength(1, 1);
ArrayList<Integer> reps = new ArrayList<Integer>();
reps.add(100);
reps.add(200);
reps.add(300);
reps.add(400);
st1.setWeight(StrParse.toWeight("100kg"));
//System.out.println(st1.toString());
CsvHandler.csvAppend(st1);


Isometric is1 = new Isometric(2, 2);
is1.setWeight(StrParse.toWeight("40lbs"));

is1.setSet(StrParse.toIsometricSet("2:30, 1:15 , 2:20, 8:30"));

CsvHandler.csvAppend(is1);
//System.out.println(is1.toString());


Strength s1 = new Strength(3, 9);
s1.setWeight(StrParse.toWeight("25lbs"));
s1.setAnnotation("I am in bed");
s1.addReps(19);
s1.addReps(16);
s1.setDate(StrParse.toDate("1/2/2023"));
//System.out.println(s1.toString());
//System.out.println(s1.superToString());
CsvHandler.csvAppend(s1);

//System.out.println(mystr);

HashMap<Integer, Workout> hMapThing = new HashMap<Integer, Workout>();

hMapThing.putIfAbsent(1, is1);
hMapThing.putIfAbsent(2, s1);


System.out.println(hMapThing.get(1).toString());
System.out.println(hMapThing.get(1).superToString());
System.out.println(hMapThing.get(2).toString());
System.out.println(hMapThing.get(2).superToString());
}
}