package java111;
import java.util.Scanner; 

/**
 * @author remllez
 * This is an experimental unfinished launcher for the app.
 */
public class LibGainzCLI{



/* main menu
*   - user menu
*   - view previous workouts 
*   - Create workout
*   - Edit workouts
*   - Delete workouts
*   - template menu 
*/

private void MainMenu(){
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

private void TemplateMenu(){ System.out.println(
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








}