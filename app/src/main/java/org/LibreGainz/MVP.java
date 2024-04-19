package org.LibreGainz;
import java.sql.Time;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


private static void MVPMenu(Scanner scnr, User user){
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
        case "i": isometricMenu(scnr, user); break;
        case "li": Isometric.map.forEach((k, v) -> System.out.println(v.toString())); break;
        case "s": strengthMenu(scnr, user); break;
        case "ls": Strength.map.forEach((k, v) -> System.out.println(v.toString())); break;
        case "c": cardioMenu(scnr, user); break;
        case "lc": Cardio.map.forEach((k,v) -> System.out.println(v.toString())); break;
        case "l":
        Isometric.map.forEach((k, v) -> System.out.println(v.toString())); 
        Cardio.map.forEach((k,v) -> System.out.println(v.toString()));
        Strength.map.forEach((k, v) -> System.out.println(v.toString()));
        break;


        case "q": System.exit(0); break;
    }
} catch(Exception e) {
    e.printStackTrace();
}

}

private static Cardio cardioMenu(Scanner scnr, User user){
 Template.map.forEach((k,v)-> {
        if (v.getWorkoutType().equals("Cardio"))
        System.out.println(k + " - " + v.getName());
});


   try{
    System.out.println("Enter a template number:");
    Cardio c = new Cardio(scnr.nextInt());
    scnr.nextLine();
    c.setUser(user);
    c.setUserId(user.getId());
    System.out.println("Enter a Time: ");
    c.setTime(Time.valueOf(scnr.nextLine()));
    System.out.println("Enter Distance: ");
    c.setDistanceStr(scnr.nextLine());
    c.csvAppend();
    Cardio.map.forEach((k, v) -> System.out.println(v.toString()));
    return c;
   } 
   catch(Exception e){
    e.printStackTrace();
    return null;
   }


}

private static Isometric isometricMenu(Scanner scnr, User user){
 Template.map.forEach((k,v)-> {
        if (v.getWorkoutType().equals("Isometric"))
        System.out.println(k + " - " + v.getName());
    });

   try{
    System.out.println("Enter a template number:");
    Isometric i = new Isometric(scnr.nextInt());
    scnr.nextLine();
    i.setUser(user);
    i.setUserId(user.getId());
    System.out.println("Enter a Weight:");
    i.setWeight(WeightObj.strToWeight(scnr.nextLine()));
    System.out.println("Enter Repetitions (comma separated):");
    i.setSet(Isometric.strToSet(scnr.nextLine()));
    return i;
   } 
   catch(Exception e){
    e.printStackTrace();
    return null;
   }


}

private static Strength strengthMenu(Scanner scnr, User user){
    Template.map.forEach((k,v)-> {
        if (v.getWorkoutType().equals("Strength"))
        System.out.println(k + " - " + v.getName());
    });
   try{
    System.out.println("Enter a template number:");
    Strength s = new Strength(scnr.nextInt());
    scnr.nextLine();
    s.setUser(user);
    s.setUserId(user.getId());
    System.out.println("Enter a Weight:");
    s.setWeight(WeightObj.strToWeight(scnr.nextLine()));
    System.out.println("Enter Repetitions (comma separated):");
    s.setSet(Strength.strToSet(scnr.nextLine()));
    return s;
   } 
   catch(Exception e){
    e.printStackTrace();
    return null;
   }

}

public static void main(String[] args){

User.csvLoad();
Template.csvLoad();
Workout.csvLoad();
Isometric.csvLoad();
Cardio.csvLoad();
Strength.csvLoad();

Scanner scnr = new Scanner(System.in);

while (true) {
try {
    MVPMenu(scnr, Device.getUser());
    CsvHandler.overWrite();
    }
    catch (Exception e){
        e.printStackTrace();
    }

}

    }
}
