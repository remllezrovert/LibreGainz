package org.LibreGainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.sql.Date;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.event.interaction.ButtonClickEvent;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionChoice;
import org.javacord.api.interaction.SlashCommandOptionType;
import org.javacord.api.interaction.SlashCommandUpdater;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;

import okhttp3.internal.ws.RealWebSocket.Message;

import org.javacord.api.interaction.ButtonInteraction;
import org.javacord.api.interaction.Interaction;
import org.javacord.api.interaction.SlashCommand;
import java.sql.Time;

/* TODO: Fix the listall command
 * 
 */
public class Discord {

    public static void main(String[] args) {
        String token = "";  // Insert your bot's token here
    
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        Template.getRequestAll().forEach((t) -> Template.map.putIfAbsent(t.getId(), t));






        //this is information for the demolist command, and all of the autofill options in discord
        SlashCommand.with("liststrength", "lists strength workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();

        SlashCommand.with("listcardio", "lists cardio workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))

            
        )).createGlobal(api).join();

        SlashCommand.with("listisometric", "lists isometric workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))

            
        )).createGlobal(api).join();

        SlashCommand.with("listall", "lists all workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();








       

        //this is information for the demolist command, and all of the autofill options in discord
        SlashCommand.with("demolist", "lists strength workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();





















    //This is an arraylist of template options. These are the autofill suggestions on discord for strenth templateid
    ArrayList<SlashCommandOptionChoice> optionList = new ArrayList<SlashCommandOptionChoice>();
    for (Template t : Template.map.values()){
        if (t.getWorkoutType().equals("Strength"))
            optionList.add(SlashCommandOptionChoice.create(t.getName(),String.valueOf(t.getId())));
    }


    //This is the strenth command
    SlashCommand.with("strength", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true, 
            optionList
            ),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "weight", "weight", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "unit", true,
                Arrays.asList(
                        SlashCommandOptionChoice.create("KG", "KG"),
                        SlashCommandOptionChoice.create("LB", "LB")
            )),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "set", "the set of reps (1,2,3,4,)", true)
        )).createGlobal(api).join();



        //isometric
        ArrayList<SlashCommandOptionChoice> optionList2 = new ArrayList<SlashCommandOptionChoice>();
    for (Template t : Template.map.values()){
        if (t.getWorkoutType().equals("Isometric"))
            optionList2.add(SlashCommandOptionChoice.create(t.getName(),String.valueOf(t.getId())));
    }
        SlashCommand.with("isometric", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true, 
            optionList2
            ),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "weight", "weight", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "unit", true,
                Arrays.asList(
                        SlashCommandOptionChoice.create("KG", "KG"),
                        SlashCommandOptionChoice.create("LB", "LB")
            )),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "Time", "Time 2h 5m 3s, 2h 5m 3s", true)
        )).createGlobal(api).join();















        //Cardio
        ArrayList<SlashCommandOptionChoice> optionList1 = new ArrayList<SlashCommandOptionChoice>();
    for (Template t : Template.map.values()){
        if (t.getWorkoutType().equals("Cardio"))
            optionList1.add(SlashCommandOptionChoice.create(t.getName(),String.valueOf(t.getId())));
    }


    //This is the strenth command
    SlashCommand.with("cardio", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true, 
            optionList1
            ),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.DECIMAL, "distance", "distance", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "unit", true,
                Arrays.asList(
                        SlashCommandOptionChoice.create("MI", "MI"),
                        SlashCommandOptionChoice.create("KM", "KM")
            )),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "Time", "Time (2h 5m 3s)", false)
        )).createGlobal(api).join();















        SlashCommand.with("excercise", "Testing for to see if I can get args.", 
        Arrays.asList(
                SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "type", "type", true, 
                Arrays.asList(
                    SlashCommandOptionChoice.create("Strength", "Strength"),
                    SlashCommandOptionChoice.create("Cardio", "Cardio"),
                    SlashCommandOptionChoice.create("Isometric", "Isometric")
                    )),
                SlashCommandOption.create(SlashCommandOptionType.STRING, "name", "name of excercise", true)

                
            )).createGlobal(api).join();
    












  
        api.addButtonClickListener(event -> {
    // Check if the button clicked is the one you are interested in
    System.out.println(event.getButtonInteraction().getCustomId());
        int index = Integer.parseInt(
            event.getButtonInteraction()
            .getCustomId()
            .split(",")[0]
            .replaceAll("[^0-9]", "")
        );
        String btnStr = event.
        getButtonInteraction()
            .getCustomId()
            .split(",")[1]
            .replaceAll("[0-9]","");
 
        int workoutId = Integer.parseInt(
            event.getButtonInteraction()
            .getCustomId()
            .split(",")[2]
            .replaceAll("[^0-9]", "")
        );



   

        if (btnStr.equals("d")){
        Workout.deleteRequest(workoutId);

        try {
        event.getButtonInteraction()
        .getMessage()
        .delete();
        }

        catch (Exception e){
            e.printStackTrace();
        }



        event.getButtonInteraction()
            .createImmediateResponder()
            .setContent("Deleting Workout!")
            .setFlags(MessageFlag.EPHEMERAL) // Ensure this is visible only to the user
            .respond();

                 // Cast the interaction to ButtonInteraction
            //ButtonInteraction buttonInteraction = (ButtonInteraction) event;
            // Get the component object
                }


    }

);




        //This switch case listens and handles all of our commands
        api.addSlashCommandCreateListener(event -> {
         SlashCommandInteraction sci = event.getSlashCommandInteraction();


        switch(sci.getCommandName()){
            case "strength":
            case "s":
                postUser(sci);
                postStrength(sci);
                break;
            case "cardio":     
            case "c":
                postUser(sci);
                postCardio(sci);
                break;
            case "isometric":
            case "i":;
                postIsometric(sci);
                break;
            case "liststrength":
            case "ls":
                buttonMenu(sci,getStrengthDate(sci));
                break;
            case "listcardio":
                buttonMenu(sci,getCardioDate(sci));
                break;
            case "listisometric":
                buttonMenu(sci,getIsometricDate(sci));
                break;
            case "listall":
                ArrayList<Workout> listAll = new ArrayList<>();   // THIS DOES NOT WORK
                listAll.addAll(getStrengthDate(sci));
                listAll.addAll(getIsometricDate(sci));
                listAll.addAll(getCardioDate(sci));
                buttonMenu(sci,listAll);
                break;
            case "excercise":
                postTemplate(sci);
                break;



        }
    });



}










    
    public static void postUser(SlashCommandInteraction sci){
    String name = sci.getUser().getName();
    User u = new User(name);
    u.postRequest(); //this automatically corrects the userId
    //respondPrivate(sci,String.valueOf(u.getId())); //respond with the new user's id
    }





    //Get user object by username. This could possibly break and cause problems for users with periods in their names like kyle.f1lthy
    public static User getUser(SlashCommandInteraction sci){
    String name = sci.getUser().getName();
    if (User.getRequestName(name) != null){
        return User.getRequestName(name);
    } else {
        //System.out.println("no such user found in the database");
        //respondPrivate(sci, "no such user found");
        postUser(sci);    //create the user if they do not exist
        return getUser(sci);
    }
    }






    /**
     * GET all of the strength objects that belong to this user
     * @param sci
     * @return
     */
    public static List<Strength> getStrengthUser(SlashCommandInteraction sci){
        postUser(sci); 
        User user = User.getRequestName(sci.getUser().getName()); //get the user, add them to the database if they are new
        try {
        List<Strength> list = Strength.getRequestUser(user);
        String ret = "";
        for (Strength s: list){
            ret += "\n" + s.toString();
        }
        respondPrivate(sci, ret);
        return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }








    /**
     * GET all of the strength objects that belong to this user
     * @param sci
     * @return
     */
    public static List<Strength> getStrengthDate(SlashCommandInteraction sci){
        postUser(sci); 
        User user = User.getRequestName(sci.getUser().getName()); //get the user, add them to the database if they are new
        String search = sci.getArgumentStringValueByName("search").get();

        java.util.Date date = new java.util.Date(); //today
        java.sql.Date endDate = new Date(date.getTime()); //get today
        java.sql.Date startDate;

        switch(search){
            case "month":
                startDate = (java.sql.Date)(new Date(System.currentTimeMillis()-730*60*60*1000));
                break;
            case "week": 
                startDate = (java.sql.Date)(new Date(System.currentTimeMillis()-168*60*60*1000));
                break;
            default:
                startDate = new Date(date.getTime());
                break;
        }
        try {
        List<Strength> list = Strength.getRequestDate(user,startDate,endDate,15);
        String ret = "";
        return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }













    public static List<Isometric> getIsometricDate(SlashCommandInteraction sci){
        postUser(sci); 
        User user = User.getRequestName(sci.getUser().getName()); //get the user, add them to the database if they are new
        String search = sci.getArgumentStringValueByName("search").get();

        java.util.Date date = new java.util.Date(); //today
        java.sql.Date endDate = new Date(date.getTime()); //get today
        java.sql.Date startDate;

        switch(search){
            case "month":
                startDate = (java.sql.Date)(new Date(System.currentTimeMillis()-730*60*60*1000));
                break;
            case "week": 
                startDate = (java.sql.Date)(new Date(System.currentTimeMillis()-168*60*60*1000));
                break;
            default:
                startDate = new Date(date.getTime());
                break;
        }
        try {
        List<Isometric> list = Isometric.getRequestDate(user,startDate,endDate,15);
        return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }










































    public static List<Cardio> getCardioDate(SlashCommandInteraction sci){
        postUser(sci); 
        User user = User.getRequestName(sci.getUser().getName()); //get the user, add them to the database if they are new
        String search = sci.getArgumentStringValueByName("search").get();

        java.util.Date date = new java.util.Date(); //today
        java.sql.Date endDate = new Date(date.getTime()); //get today
        java.sql.Date startDate;

        switch(search){
            case "month":
                startDate = (java.sql.Date)(new Date(System.currentTimeMillis()-730*60*60*1000));
                break;
            case "week": 
                startDate = (java.sql.Date)(new Date(System.currentTimeMillis()-168*60*60*1000));
                break;
            default:
                startDate = new Date(date.getTime());
                break;
        }
        try {
        List<Cardio> list = Cardio.getRequestDate(user,startDate,endDate,15);
        return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




















    /**
     * POST a new strength object to the database
     * @param sci
     */
    private static void postStrength(SlashCommandInteraction sci){
        User user = getUser(sci);
        //User user = User.getRequestId(16);
        Strength s = new Strength(
            Integer.parseInt(sci.getArgumentStringValueByIndex(0).get())
        );
        s.setUser(user);
        s.setUserId(user.getId());
        s.setWeight(
        new WeightObj(
            Short.parseShort(sci.getArgumentStringValueByIndex(1).get()),
            Unit.valueOf(sci.getArgumentStringValueByIndex(2).get())
            )
        );
        s.setSet(
            Strength.strToSet(sci.getArgumentStringValueByIndex(3).get())
        );
        s.postRequest(); 
        respondPrivate(sci, s.toString());
    }









    private static void postCardio(SlashCommandInteraction sci){
        User user = getUser(sci);
        //User user = User.getRequestId(16);
        Cardio s = new Cardio(
            Integer.parseInt(sci.getArgumentStringValueByIndex(0).get())
        );
        s.setUser(user);
        s.setUserId(user.getId());
        s.setUnit(Unit.valueOf(sci.getArgumentStringValueByIndex(2).get()));
        try{
            String timeInput = sci.getArgumentStringValueByIndex(3).get();
            Time distanceTime = TimeConversion.convertToSqlTime(timeInput);
            s.setTime(distanceTime);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        s.setDistance(
            sci.getArgumentDecimalValueByIndex(1).get()
        );
        s.postRequest(); 
        respondPrivate(sci, s.toString());
    }





    private static void postIsometric(SlashCommandInteraction sci){
        User user = getUser(sci);
        //User user = User.getRequestId(16);
        Isometric s = new Isometric(
            Integer.parseInt(sci.getArgumentStringValueByIndex(0).get())
        );
        s.setUser(user);
        s.setUserId(user.getId());
        s.setWeight(
        new WeightObj(
            Short.parseShort(sci.getArgumentStringValueByIndex(1).get()),
            Unit.valueOf(sci.getArgumentStringValueByIndex(2).get())
            )
        );
        s.setSet(
            Isometric.strToSet(sci.getArgumentStringValueByIndex(3).get())
        );
        s.postRequest(); 
        respondPrivate(sci, s.toString());
    }


















    private static void postTemplate(SlashCommandInteraction sci){
        User user = getUser(sci);
        //User user = User.getRequestId(16);
        Template t = new Template();
        sci.getArgumentStringValueByIndex(0).get();
        t.setUserId(user.getId());
        
        t.setWorkoutType(sci.getArgumentStringValueByIndex(0).get());
        t.setName(sci.getArgumentStringValueByIndex(1).get());
        t.postRequest();
        respondPrivate(sci, t.toString());
    }












    







    /**
     * Respond to the discord user with a private message
     * @param sci
     * @param responseStr
     */
    public static void respondPrivate(SlashCommandInteraction sci, String responseStr){
        sci.createImmediateResponder()
                 .setContent(responseStr)
                 .setFlags(MessageFlag.EPHEMERAL) // Ensure this is visible only to the user
                 .respond();
    }







    /**
     * Respond to the discord user with a public message
     * @param sci
     * @param responseStr
     */
    public static void respondPublic(SlashCommandInteraction sci, String responseStr){
    sci.createImmediateResponder()
        .setContent(responseStr)
        .respond();
    }





    public static CompletableFuture<InteractionOriginalResponseUpdater> 
        buttonMenu(SlashCommandInteraction sci, List<? extends Workout> list){
        System.out.println(list.toString());
    InteractionImmediateResponseBuilder responder = sci.createImmediateResponder()
        .setContent("Workout List")
        .setFlags(MessageFlag.EPHEMERAL); // Ensure this is visible only to the user
    int index = 0;
    for (Workout workout : list) {
        String idStr = String.valueOf(workout.getId());
        responder
        .addComponents(
            ActionRow.of(Button.secondary(index + ",e," + idStr, workout.toString()),  //This edits the workout
                Button.danger(index + ",d," + idStr, "🗑️")                   //This deletes the workout
            ));
        index += 1;
        }
   return responder.respond();
   
    }
    















    //Evan Code
  

}  
