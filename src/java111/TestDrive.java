package java111;
import javax.management.openmbean.CompositeDataInvocationHandler;
import java.util.ArrayList;
import java.util.*;


/**
 * @author Remllez
 * This class tests a few basic methods
 */
public class TestDrive{
public static void main(String[] args) throws Exception{

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
s1.setDate("eventually");
//System.out.println(s1.toString());
CsvHandler.csvAppend(s1);

for (String mystr : CsvHandler.getRow("data/Isometric.csv",18))
    System.out.println(mystr);

}
}