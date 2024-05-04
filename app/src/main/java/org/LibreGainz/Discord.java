package org.LibreGainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.sql.Date;

import org.checkerframework.checker.units.qual.A;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.MessageUpdater;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.Component;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.entity.message.component.SelectMenu;
import org.javacord.api.entity.message.component.SelectMenuOption;
import org.javacord.api.entity.message.component.TextInput;
import org.javacord.api.entity.message.component.TextInputStyle;
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
import org.javacord.api.interaction.ModalInteraction;
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
    for (Template t : Template.getRequestAll()){
        if (t.getWorkoutType().toLowerCase().equals(workoutType.toLowerCase())
            && !templateNameList.contains(t.getName())
        ){
            templateList.add(SlashCommandOptionChoice.create(t.getName(),String.valueOf(t.getId())));
        }
    }
    return templateList;
}


    public static void main(String[] args) {
        String token = "";// Insert your bot's token here
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





     SlashCommand editstrengthCommand = SlashCommand.with("editstrength", "lists strength workouts",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();
    SlashCommand editisometricCommand = SlashCommand.with("editisometric", "lists isometric workouts for editing",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();

SlashCommand editcardioCommand = SlashCommand.with("editcardio", "lists cardio workouts for editing",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();

SlashCommand editallCommand = SlashCommand.with("editall", "lists all workouts for editing",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();

SlashCommand editexcerciseCommand = SlashCommand.with("editexcercise", "lists excercises for editing",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("today","today"),
            SlashCommandOptionChoice.create("week","week"),
            SlashCommandOptionChoice.create("month","month")
            ))
        )).createGlobal(api).join();
























    //This is the strength command
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
 
        Long id = Long.parseLong(
            event.getButtonInteraction()
            .getCustomId()
            .split(",")[2]
            .replaceAll("[^0-9]", "")
        );



        
        //System.out.println(btnStr);


        if (btnStr.equals("Strength")){

            Strength strength = Strength.getRequestId(id).get(0);
            workoutEditMenu(b,                    
            strength,
            createStrengthActionRow(strength)
            );
        }
        if (btnStr.equals("Isometric")){

            Isometric isometric = Isometric.getRequestId(id).get(0);
            workoutEditMenu(b,                    
                isometric,
                createIsometricActionRow(isometric)
            );
        }
        if (btnStr.equals("Cardio")){

            Cardio cardio = Cardio.getRequestId(id).get(0);
            workoutEditMenu(b,                    
            cardio,
            createCardioActionRow(cardio)
            );
        }
        
        

        if (btnStr.equals("Delete")){
            Message message = b.getMessage();
            MessageUpdater updater =  message.createUpdater();
            try {
                 List<HighLevelComponent> existingActionRows = new ArrayList<>(message.getComponents());
            if (!existingActionRows.isEmpty()) {
                existingActionRows.remove(index);
            }
            for (HighLevelComponent a : existingActionRows)
                updater.addComponents(a);
            CompletableFuture<Message> updateFuture = updater.applyChanges();
            Workout.deleteRequest(id);
            }

            catch (Exception e){
                e.printStackTrace();
            }
            event.getButtonInteraction()
            .createImmediateResponder()
            .setContent("Deleting Workout!")
            .setFlags(MessageFlag.EPHEMERAL) // Ensure this is visible only to the user
            .respond();
        }



        //Edit the date of any workout type
        if (btnStr.equals("date")){
            b.respondWithModal("0,date,"+id, "Enter a date",
            ActionRow.of(
                TextInput.create(
                    TextInputStyle.SHORT, 
                    "1,date,"+id, 
                    "This is a Text Input Field"))
            );
        }






        // Handle strength edit button presses
        if (btnStr.equals("strengthEditSet")){
            b.respondWithModal("0,strengthEditSet,"+id, "Type a new set example:    1, 2, 3, 4",
            ActionRow.of(TextInput.create(TextInputStyle.SHORT, "1,strengthEditSet,"+id, "This is a Text Input Field"))
            );
            //Strength s = Strength.getRequestId(id).get(0);
        }
        if (btnStr.equals("strengthAddSet")){
            b.respondWithModal("0,strengthAddSet,"+id, "Type a new set example:    1, 2, 3, 4",
            ActionRow.of(TextInput.create(
                TextInputStyle.SHORT, "1,strengthAddSet,"+id, "This is a Text Input Field"))
            );
        }
        if (btnStr.equals("strengthDelSet")){
            Strength strength = Strength.getRequestId(id).get(0);
            if (!strength.getSet().isEmpty()){
                strength.delReps( (short)(strength.getSet().size() - 1));
            }
        }






        //Handle isometric edit button pushes
        if (btnStr.equals("isometricEditSet")){
            b.respondWithModal("0,isometricEditSet,"+id, "Type a new set example:    1h 2m 1s,   2m 1s,  3s,  1h 2m 1s",
            ActionRow.of(
                TextInput.create(
                    TextInputStyle.SHORT,
                    "1,isometricEditSet,"+id, 
                    "This is a Text Input Field"))
            );
        }
        if (btnStr.equals("isometricAddSet")){
            b.respondWithModal("0,isometricAddSet,"+id, "Add one or many to the current set:   2m 3s,   1h 2m 3s,   2s",
            ActionRow.of(TextInput.create(
                TextInputStyle.SHORT, "1,isometricAddSet,"+id, "This is a Text Input Field"))
            );
        }
        if (btnStr.equals("isometricDelSet")){
            Strength strength = Strength.getRequestId(id).get(0);
            if (!strength.getSet().isEmpty()){
                strength.delReps( (short)(strength.getSet().size() - 1));
            }
        }


        //Handle cardio edit button pushes
    if (btnStr.equals("cardioTime")){
            b.respondWithModal("0,cardioTime,"+id, "Type a new time, example:   1h 2m 1s",
            ActionRow.of(
                TextInput.create(
                    TextInputStyle.SHORT,
                    "1,cardioTime,"+id,
                    "This is a Text Input Field"
                )
            )
            );
        }
        if (btnStr.equals("cardioDistance")){
            b.respondWithModal("0,cardioDistance,"+id, "Edit distance, example:   2.1 MI",
            ActionRow.of(TextInput.create(
                TextInputStyle.SHORT, "1,cardioDistance,"+id, "This is a Text Input Field"))
            );
        }








    }

);


    //This is the modal listener!!
    api.addModalSubmitListener(event -> {
        ModalInteraction mdl = event.getModalInteraction();
        int index = Integer.parseInt(
            event.getModalInteraction()
            .getCustomId()
            .split(",")[0]
            .replaceAll("[^0-9]", "")
        );
        String mdlStr = event.
        getModalInteraction()
            .getCustomId()
            .split(",")[1]
            .replaceAll("[^a-zA-Z]", "");
 
        Long id = Long.parseLong(
            event.getModalInteraction()
            .getCustomId()
            .split(",")[2]
            .replaceAll("[^0-9]", "")
        );

        if (mdlStr.equals("date")){
            try {
            Workout w = Workout.getRequestId(id).get(0);
            String input = mdl.getTextInputValues().get(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat(w.getUser().getDateFormatStr());
            w.setDate(dateFormat.parse(input));
            w.patchRequest();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (mdlStr.startsWith("strength")){
            Strength s = Strength.getRequestId(id).get(0);
            String input = mdl.getTextInputValues().get(0);
            switch(mdlStr){
            case "strengthEditSet":
                s.setSet(
                    Strength.strToSet(input)
                );
                break;
            case "strengthAddSet":
                Strength.strToSet(input)
                    .forEach((reps) -> s.addReps(reps));
                break;
            }
            s.patchRequest();

            mdl.createImmediateResponder().setContent("edited");
            
        }


    if (mdlStr.startsWith("isometric")){
        Isometric i = Isometric.getRequestId(id).get(0);
        String input = mdl.getTextInputValues().get(0);
        switch(mdlStr){
            case "isometricEditSet":
                i.setSet(
                    Isometric.strToSet(input)
                );
            break;
            case "isometricAddSet":
                Isometric.strToSet(input)
                    .forEach((time) -> i.addTime(time.toString()));
            break;
        }
        i.patchRequest();
    }

    if (mdlStr.startsWith("cardio")){
        Cardio c = Cardio.getRequestId(id).get(0);
        String input = mdl.getTextInputValues().get(0);
        System.out.println("input is: "+ input);
        switch(mdlStr){
            case "cardioTime":
                c.setTime(TimeConversion.convertToSqlTime(input));            
            break;
            case "cardioDistance":
                c.setDistanceStr(input);
            break;
        }
        c.patchRequest();
        }
    });

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
                respondPrivate(sci,listFormatter(getStrengthDate(sci)));
                //messagePackager(getStrengthDate(sci)).forEach((arr) -> buttonMenu(sci, arr));
                break;
            case "listcardio":
                respondPrivate(sci,listFormatter(getCardioDate(sci)));
                break;
            case "listisometric":
                respondPrivate(sci,listFormatter(getIsometricDate(sci)));
                break;
            case "listall":
                ArrayList<Workout> listAll = new ArrayList<>();   // THIS DOES NOT WORK
                listAll.addAll(getStrengthDate(sci));
                listAll.addAll(getIsometricDate(sci));
                listAll.addAll(getCardioDate(sci));
                respondPrivate(sci,listFormatter(listAll));
                break;
            case "excercise":
                postTemplate(sci);
                strengthCommand.createSlashCommandUpdater().updateGlobal(api);
                isometricCommand.createSlashCommandUpdater().updateGlobal(api);
                cardioCommand.createSlashCommandUpdater().updateGlobal(api);
                break;
            case "listexcercise":
                String eListStr = "";
                for (Template t: Template.getRequestAll()){
                    eListStr += t.toString() + "\n";
                }
                respondPrivate(sci,eListStr);
                break;
            case "editstrength":
                for (MessageBuilder mb : new ButtonMenu(sci,getStrengthDate(sci)).getList())
                    mb.send(sci.getUser())
                    .thenAccept(message -> {
                    MessageDeletionScheduler deletionScheduler = new MessageDeletionScheduler();
                    deletionScheduler.scheduleMessageDeletion(message, 2); // 2 minutes
                }); 
                break;
            case "editisometric":
                for (MessageBuilder mb : new ButtonMenu(sci,getIsometricDate(sci)).getList())
                    mb.send(sci.getUser())
                    .thenAccept(message -> {
                    MessageDeletionScheduler deletionScheduler = new MessageDeletionScheduler();
                    deletionScheduler.scheduleMessageDeletion(message, 2); // 2 minutes
                }); 

                break;
            case "editcardio":
                for (MessageBuilder mb : new ButtonMenu(sci,getCardioDate(sci)).getList())
                    mb.send(sci.getUser())
                    .thenAccept(message -> {
                    MessageDeletionScheduler deletionScheduler = new MessageDeletionScheduler();
                    deletionScheduler.scheduleMessageDeletion(message, 2); // 2 minutes
                }); 

                break;
            case "editall":
                ArrayList<Workout> editAll = new ArrayList<>();
                editAll.addAll(getStrengthDate(sci));
                editAll.addAll(getIsometricDate(sci));
                editAll.addAll(getCardioDate(sci));
                for (MessageBuilder mb : new ButtonMenu(sci,editAll).getList())
                    mb.send(sci.getUser())
                    .thenAccept(message -> {
                    MessageDeletionScheduler deletionScheduler = new MessageDeletionScheduler();
                    deletionScheduler.scheduleMessageDeletion(message, 2); // 2 minutes
                }); 

                break;
            case "editexcercise":
                for (MessageBuilder mb : new TemplateMenu(sci,Template.getRequestAll()).getList())
                    mb.send(sci.getUser())
                    .thenAccept(message -> {
                    MessageDeletionScheduler deletionScheduler = new MessageDeletionScheduler();
                    deletionScheduler.scheduleMessageDeletion(message, 2); // 2 minutes
                }); 

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
    Long id = workout.getId();
     return ActionRow.of(
                Button.secondary("0,date,"+id, dateFormat.format(workout.getDate())),
                Button.secondary("1,strengthWeight,"+id , workout.getWeight().toString()),
                Button.secondary("2,strengthEditSet,"+id, workout.getSet().toString()),
                Button.success("3,strengthAddSet,"+id, "+"),
                Button.danger("4,strengthDelSet,"+id, "-")
            );
}

public static ActionRow createIsometricActionRow(Isometric workout){
    Long id = workout.getId();
    SimpleDateFormat dateFormat = new SimpleDateFormat(workout.getUser().getDateFormatStr());
     return ActionRow.of(
                Button.secondary("0,date,"+id, dateFormat.format(workout.getDate())),
                Button.secondary("1,isometricWeight,"+id, workout.getWeight().toString()),
                Button.secondary("2,isometricEditSet,"+id, workout.getSet().toString()),
                Button.success("3,isometricAddSet,"+id, "+"),
                Button.danger("4,isometricDelSet,"+id, "-")
            );
}

public static ActionRow createCardioActionRow(Cardio workout){
    SimpleDateFormat dateFormat = new SimpleDateFormat(workout.getUser().getDateFormatStr());
    Long id = workout.getId();
     return ActionRow.of(
                Button.secondary("0,date,"+id, dateFormat.format(workout.getDate())),
                Button.secondary("1,cardioDistance,"+id, 
                    Double.toString(workout.getDistance()) + workout.getUnit().toString()),
                Button.secondary("2,cardioTime,"+id, workout.getTime().toString())
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
   

    

public static <T> List<List<T>> objectPaginator(List<T> list) {
        List<List<T>> ret = new ArrayList<>();
        List<T> groupList = new ArrayList<>();
        for (T object : list) {
            if (groupList.size() == 5) {
                ret.add(groupList);
                groupList= new ArrayList<>();
            }
            groupList.add(object);
        }
        if (!groupList.isEmpty()) {
            ret.add(groupList);
        }
        return ret;
    }     
    


  







    public static <T extends Workout> String listFormatter(List<T> list){
        String ret = "";
        for (T t: list){
            ret += t.toString2() + "\n";
        }
        return ret;
    }
    

   }

   














/**
 * This class deletes old messages after two minutes
 */
class MessageDeletionScheduler {

    private final ScheduledExecutorService scheduler;

    public MessageDeletionScheduler() {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void scheduleMessageDeletion(Message message, long delayMinutes) {
        scheduler.schedule(() -> deleteMessage(message), delayMinutes, TimeUnit.MINUTES);
    }

    private void deleteMessage(Message message) {
        message.delete();
    }
}
