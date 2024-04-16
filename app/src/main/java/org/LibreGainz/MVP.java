package org.LibreGainz;
import java.sql.Time;
import java.util.Scanner; 

/**
 * @author Kyle
 * This is an experimental unfinished launcher for the app.
 */
public class MVP{



/* main menu
*   - user menu
*   - view previous workouts 
*   - Create workout
*   - Edit/delete workouts
*   - template menu 
*/

private void MainMenu(Scanner scnr){
    System.out.println(

    "\n==================== Menu ====================\n" +
    "q - Quit out of program\n" +
    "a - Add/Alter workout\n" +
    "t - Template menu\n\n"
    );
    String input = scnr.nextLine();
    switch(input){
        case "q":
        case "a":
        case "t":
    }
}




/* template menu
 * - add tags
 * - remove tags
 * - edit desc
 * - back to main menu
 *  - quit
 */

private void TemplateMenu(Scanner scnr){ System.out.println(
"\n================ Template Menu ===============\n" +
"b - Back to main menu\n" +
"a - Add tags to template\n" +
"r - Remove tags from template\n" +
"e - Edit template description\n" +
"q - Quit out of program\n\n"
);
    String input = scnr.nextLine();
    switch(input){
        case "b":
        case "a":
        case "r":
        case "e":
        case "q":
    }

}


private static void MVPMenu(Scanner scnr){
    System.out.println(
        """
    ------------ MVP Menu --------------
    i - isometric
    s - strength
    c - cardio
    l - list workouts
    q - quit
    ------------------------------------
                """
    );
    try {
    String input = scnr.nextLine();
    switch(input.toLowerCase()){
        case "i": isometricMenu(scnr); break;
        case "li": Isometric.map.forEach((k, v) -> System.out.println(v.toString())); break;
        case "s": strengthMenu(scnr); break;
        case "ls": Strength.map.forEach((k, v) -> System.out.println(v.toString())); break;
        case "c": cardioMenu(scnr); break;
        case "lc": Cardio.map.forEach((k,v) -> System.out.println(v.toString())); break;
        case "q": System.exit(0); break;
    }
} catch(Exception e) {
    e.printStackTrace();
}

}

private static void cardioMenu(Scanner scnr){
    Template.map.forEach((k,v)-> System.out.println(k + " - " + v.getName()));
   try{
    System.out.println("Enter a template number:");
    Cardio c = new Cardio(scnr.nextInt());
    scnr.nextLine();
    System.out.println("Enter a Time: ");
    c.setTime(Time.valueOf(scnr.nextLine()));
    System.out.println("Enter Distance: ");
    c.setDistance(scnr.nextLine());
    c.csvAppend();
    Cardio.map.forEach((k, v) -> System.out.println(v.toString()));
   } 
   catch(Exception e){
    //System.out.println(e);
    System.out.println("Invalid input");
   }


}

private static void isometricMenu(Scanner scnr){
    Template.map.forEach((k,v)-> System.out.println(k + " - " + v.getName()));
   try{
    System.out.println("Enter a template number:");
    Isometric i = new Isometric(scnr.nextInt());
    scnr.nextLine();
    System.out.println("Enter a Weight:");
    i.setWeight(WeightObj.strToWeight(scnr.nextLine()));
    System.out.println("Enter Repetitions (comma separated):");
    i.setSet(Isometric.strToSet(scnr.nextLine()));
    i.csvAppend();
   } 
   catch(Exception e){
    e.printStackTrace();
   }


}

private static void strengthMenu(Scanner scnr){
    Template.map.forEach((k,v)-> System.out.println(k + " - " + v.getName()));
   try{
    System.out.println("Enter a template number:");
    Strength s = new Strength(scnr.nextInt());
    scnr.nextLine();
    System.out.println("Enter a Weight:");
    s.setWeight(WeightObj.strToWeight(scnr.nextLine()));
    System.out.println("Enter Repetitions (comma separated):");
    s.setSet(Strength.strToSet(scnr.nextLine()));
    s.csvAppend();
   } 
   catch(Exception e){
    //System.out.println(e);
    System.out.println("Invalid input");
   }

}

public static void main(String[] args){


Template.csvLoad("data//Template.csv");
Workout.csvLoad("data//Workout.csv");
Isometric.csvLoad("data//Isometric.csv");
Cardio.csvLoad("data//Cardio.csv");
Strength.csvLoad("data//Strength.csv");
Scanner scnr = new Scanner(System.in);

while (true) {
try {
    MVPMenu(scnr);
    CsvHandler.overWrite();
    }
    catch (Exception e){
        e.printStackTrace();
    }

}

    }
}
