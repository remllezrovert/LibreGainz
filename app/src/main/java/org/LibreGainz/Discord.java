package org.LibreGainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.sql.Date;
import org.checkerframework.checker.units.qual.A;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.Component;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.entity.message.component.SelectMenu;
import org.javacord.api.entity.message.component.SelectMenuOption;
import org.javacord.api.entity.message.embed.EmbedBuilder;
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
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

import org.javacord.api.interaction.ButtonInteraction;
import org.javacord.api.interaction.Interaction;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandBuilder;

import java.sql.Time;
import java.text.SimpleDateFormat;

/* TODO: Fix the listall command
 * 
 */
public class Discord {


    public static ArrayList<SlashCommandOptionChoice> createTemplateChoiceList(String workoutType){
    Template.map.clear();
    Template.getRequestAll().forEach((t) -> Template.map.putIfAbsent(t.getId(), t));
    ArrayList<String> templateNameList = new ArrayList<String>();
    ArrayList<SlashCommandOptionChoice> templateList = new ArrayList<SlashCommandOptionChoice>();
    for (Template t : Template.map.values()){
        if (t.getWorkoutType().toLowerCase().equals(workoutType.toLowerCase())
            && !templateNameList.contains(t.getName())
        ){
            templateList.add(SlashCommandOptionChoice.create(t.getName(),String.valueOf(t.getId())));
        }
    }
    return templateList;
    }


    public static void main(String[] args) {
        String token = "";  // Insert your bot's token here
    
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        Template.map.clear();
        Template.getRequestAll().forEach((t) -> Template.map.putIfAbsent(t.getId(), t));


        //this is information for the demolist command, and all of the autofill options in discord
        SlashCommand liststrengthCommand = SlashCommand.with("liststrength", "lists strength workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();









        SlashCommand listcardioCommand = SlashCommand.with("listcardio", "lists cardio workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();

        //this is how we update! I think????
        //listcardio.createSlashCommandUpdater().updateGlobal(api);








        SlashCommand listisometricCommand = SlashCommand.with("listisometric", "lists isometric workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();




        SlashCommand listallCommand = SlashCommand.with("listall", "lists all workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();









        SlashCommand listexcerciseCommand = SlashCommand.with("listexcercise", "lists excercise workouts",
        Arrays.asList(
        SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "type", "type", true,
        Arrays.asList(
            SlashCommandOptionChoice.create("Strength", "Strength"),
            SlashCommandOptionChoice.create("Cardio", "Cardio"),
            SlashCommandOptionChoice.create("Isometric", "Isometric")
            ))
            
        )).createGlobal(api).join();







       

        //this is information for the demolist command, and all of the autofill options in discord
        SlashCommand demolistCommand = SlashCommand.with("demolist", "lists strength workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();





















    //This is the strenth command
    SlashCommand strengthCommand = SlashCommand.with("strength", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true, 
            createTemplateChoiceList("strength")
            ),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "weight", "weight", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "unit", true,
                Arrays.asList(
                        SlashCommandOptionChoice.create("KG", "KG"),
                        SlashCommandOptionChoice.create("LB", "LB")
            )),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "set", "the set of reps (1,2,3,4,)", true)
        )).createGlobal(api).join();











        SlashCommand isometricCommand = SlashCommand.with("isometric", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true, 
            createTemplateChoiceList("isometric")
            ),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "weight", "weight", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "unit", true,
                Arrays.asList(
                        SlashCommandOptionChoice.create("KG", "KG"),
                        SlashCommandOptionChoice.create("LB", "LB")
            )),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "Time", "Time 2h 5m 3s, 2h 5m 3s", true)
        )).createGlobal(api).join();
















    //This is the cardio command
    SlashCommand cardioCommand = SlashCommand.with("cardio", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true, 
            createTemplateChoiceList("cardio")
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
    //System.out.println(event.getButtonInteraction().getCustomId());
        ButtonInteraction b = event.getButtonInteraction();
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
            .replaceAll("[^a-zA-Z]", "");
 
        int workoutId = Integer.parseInt(
            event.getButtonInteraction()
            .getCustomId()
            .split(",")[2]
            .replaceAll("[^0-9]", "")
        );



        
        //System.out.println(btnStr);


        if (btnStr.equals("Strength")){

            Strength strength = Strength.getRequestId(workoutId).get(0);
            workoutEditMenu(b,                    
            strength,
            createStrengthActionRow(strength)
            );
        }
        if (btnStr.equals("Isometric")){

            Isometric isometric = Isometric.getRequestId(workoutId).get(0);
            workoutEditMenu(b,                    
            isometric,
            createIsometricActionRow(isometric)
            );
        }
        if (btnStr.equals("Cardio")){

            Cardio cardio = Cardio.getRequestId(workoutId).get(0);
            workoutEditMenu(b,                    
            cardio,
            createCardioActionRow(cardio)
            );
        }






        if (btnStr.equals("Delete")){
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
                //messagePackager(getStrengthDate(sci)).forEach((arr) -> buttonMenu(sci, arr));
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
            case "listexcercise":
            List<Template> valuesList = new ArrayList<>(Template.map.values());
            templateButtonMenu(sci, valuesList);




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
            ret += "\n" + s.toString2();
        }
        respondPrivate(sci, ret);
        return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


















    public static List<Template> getTemplate(SlashCommandInteraction sci){
        postUser(sci); 
        User user = User.getRequestName(sci.getUser().getName()); //get the user, add them to the database if they are new
        try {
        List<Template> list = Template.getRequestAll();
        String ret = "";
        for (Template s: list){
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
        respondPrivate(sci, s.toString2());
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
        respondPrivate(sci, s.toString2());
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
        respondPrivate(sci, s.toString2());
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



/*
    public static <T extends Workout> List<CompletableFuture<InteractionOriginalResponseUpdater>>
        buttonMenu(SlashCommandInteraction sci, List<T> list){

    List<CompletableFuture<InteractionOriginalResponseUpdater>> ret = new ArrayList<>();
    for (List<T> arr : messagePackager(list)){
        InteractionImmediateResponseBuilder responder = sci.createImmediateResponder()
        .setContent("Workout List")
        .setFlags(MessageFlag.EPHEMERAL); // Ensure this is visible only to the user
        int index = 0;
            for (T workout : arr) {
            String idStr = String.valueOf(workout.getId());
            responder
            .addComponents(
                ActionRow.of(Button.secondary(
                    index + "," + workout.getClass().getSimpleName() + "," + 
                    idStr, workout.toString2()),  //This edits the workout
                    Button.danger(index + ",Delete," + idStr, "🗑️")  //This deletes the workout

                ));
                index += 1;
            }
            CompletableFuture<InteractionOriginalResponseUpdater> future = responder.respond();
            ret.add(future);
            future.join();
        //ret.add(responder.respond());
    }
    return ret;
    }


*/


























    // edit menu 
    //Idea: pass in action rows for unique workout types!

    public static <T extends Workout> CompletableFuture<InteractionOriginalResponseUpdater> 
        workoutEditMenu(ButtonInteraction b, T workout, ActionRow actionRow){
        //System.out.println(workout.toString());
        int index = 0;
        List<SelectMenuOption> list = createTemplateSelectMenuList(
            workout.getClass().getSimpleName());
    InteractionImmediateResponseBuilder responder = b.createImmediateResponder()
        .setContent("Edit workout")
        .setFlags(MessageFlag.EPHEMERAL) // Ensure this is visible only to the user
        .addComponents(
            actionRow,
            ActionRow.of(
                SelectMenu.createStringMenu(
                    "workoutType", 
                    Template.map.get(workout.getTemplateId()).getName(),
                    list
                )
            ) 
        );
        responder.respond();
        return responder.respond();
    }




public static ActionRow createStrengthActionRow(Strength workout){
    SimpleDateFormat dateFormat = new SimpleDateFormat(workout.getUser().getDateFormatStr());
     return ActionRow.of(
                Button.secondary("date", dateFormat.format(workout.getDate())),
                Button.secondary("weight", workout.getWeight().toString()),
                Button.secondary("editReps", workout.getSet().toString()),
                Button.success("addReps", "+"),
                Button.danger("delReps", "-")
            );
}

public static ActionRow createIsometricActionRow(Isometric workout){
    SimpleDateFormat dateFormat = new SimpleDateFormat(workout.getUser().getDateFormatStr());
     return ActionRow.of(
                Button.secondary("date", dateFormat.format(workout.getDate())),
                Button.secondary("weight", workout.getWeight().toString()),
                Button.secondary("editSet", workout.getSet().toString()),
                Button.success("addTime", "+"),
                Button.danger("delTime", "-")
            );
}

public static ActionRow createCardioActionRow(Cardio workout){
    SimpleDateFormat dateFormat = new SimpleDateFormat(workout.getUser().getDateFormatStr());
     return ActionRow.of(
                Button.secondary("date", dateFormat.format(workout.getDate())),
                Button.secondary("distance", Double.toString(workout.getDistance())),
                Button.secondary("unit", workout.getUnit().toString()),
                Button.secondary("time", workout.getTime().toString())
            );
}














    public static List<SelectMenuOption> createTemplateSelectMenuList(String workoutType){
        Template.map.clear();
        Template.getRequestAll().forEach((t) -> Template.map.putIfAbsent(t.getId(), t));
        ArrayList<SelectMenuOption> templateList = new ArrayList<SelectMenuOption>();
        ArrayList<String> templateNames = new ArrayList<>();
        for (Template t : Template.map.values()){
            if (
                t.getWorkoutType().toLowerCase().equals(workoutType.toLowerCase()) //same workout type
                && !templateNames.contains(t.getName())  //is not a duplicate name
            ) {
                templateList.add(SelectMenuOption.create(t.getName(),String.valueOf(t.getId())));
                templateNames.add(t.getName());
            }
        }
        return templateList;
        }
   

    
public static <T extends Workout> List<CompletableFuture<InteractionOriginalResponseUpdater>> buttonMenu(SlashCommandInteraction sci, List<T> list) {
    List<CompletableFuture<InteractionOriginalResponseUpdater>> ret = new ArrayList<>();
    try {
        for (List<T> arr : messagePackager(list)) {
            InteractionImmediateResponseBuilder responder = sci.createImmediateResponder()
                    .setContent("Workout List")
                    .setFlags(MessageFlag.EPHEMERAL); // Ensure this is visible only to the user
            int index = 0;
            for (T workout : arr) {
                String idStr = String.valueOf(workout.getId());
                responder.addComponents(
                        ActionRow.of(
                                Button.secondary(
                                        index + "," + workout.getClass().getSimpleName() + "," + idStr,
                                        workout.toString2()),  // This edits the workout
                                Button.danger(index + ",Delete," + idStr, "🗑️")  // This deletes the workout
                        ));
                index += 1;
            }
            CompletableFuture<InteractionOriginalResponseUpdater> future = responder.respond();
            ret.add(future);
            future.join(); // Wait for the response to be sent before moving to the next batch
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ret;
}



    public static List<CompletableFuture<InteractionOriginalResponseUpdater>>
    templateButtonMenu(SlashCommandInteraction sci, List<Template> list)
        {
            try{
        //System.out.println(list.toString());
        InteractionImmediateResponseBuilder responder = sci.createImmediateResponder()
            .setContent("Excercise List")
            .setFlags(MessageFlag.EPHEMERAL); // Ensure this is visible only to the user
        List<CompletableFuture<InteractionOriginalResponseUpdater>> ret = new ArrayList<>();
        for (List<Template> arr: messagePackager(list)){
            int index = 0;
            ArrayList<String> templateNameList = new ArrayList<>();
            for (Template template : arr) {
            String idStr = String.valueOf(template.getId());
            if (!templateNameList.contains(template.getName())){                       ///NEW STuFF aHaHaaaaaa
            responder
            .addComponents(
                ActionRow.of(Button.secondary(
                    index + "," + template.getClass().getSimpleName() + "," + idStr,
                    template.toString2()),  // Button to edit the template
                    Button.danger(index + ",Delete," + idStr, "Delete")    // Button to delete the template
                ));
            index += 1;
            templateNameList.add(template.getName());
            }
            }
            responder.respond();
            ret.add(responder.respond());
            TimeUnit.MILLISECONDS.sleep(500);
        }
        return ret;
    } catch (Exception e){
        e.printStackTrace();
        return null;
    }
    }

    //The packager creates Responses with 5 action menus
    public static <T> List<List<T>> messagePackager(List<T> list) {
        List<List<T>> retArr = new ArrayList<>();
        List<T> groupArr = new ArrayList<>();

        for (T object : list) {
            if (groupArr.size() == 5) {
                retArr.add(groupArr);  // Add the current full group to the return list
                groupArr = new ArrayList<>();  // Create a new group list for new elements
            }
            groupArr.add(object);  // Add current object to the current group
        }

        // After loop, check if the last group contains any elements
        if (!groupArr.isEmpty()) {
            retArr.add(groupArr);  // Add the remaining elements as the last group
        }
        System.out.println(retArr);
        return retArr;
    }     
    


   }
    
