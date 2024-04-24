package org.LibreGainz;

import java.util.Arrays;
import java.util.List;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;


import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionChoice;
import org.javacord.api.interaction.SlashCommandOptionType;
import org.javacord.api.interaction.SlashCommand;


public class Discord {

    public static void main(String[] args) {
        String token = "";  // Insert your bot's token here
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        Template.getRequestAll().forEach((t) -> Template.map.putIfAbsent(t.getId(), t));






        //this is information for the demolist command, and all of the autofill options in discord
        SlashCommand.with("demolist", "list strength",
        Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "search", "Type of list",true,
            Arrays.asList(
            SlashCommandOptionChoice.create("user","select for this user"),
            SlashCommandOptionChoice.create("top","select the top of all time"),
            SlashCommandOptionChoice.create("all","all in general")
            ))
        )).createGlobal(api).join();







    //this is information for the strength command, all the autofill options in discord
    SlashCommand.with("strength", "Testing for to see if I can get args.", 
    Arrays.asList(
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "templateId", "template id", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "weight", "weight", true),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "unit", true,
                Arrays.asList(
                        SlashCommandOptionChoice.create("KG", "KG"),
                        SlashCommandOptionChoice.create("LB", "LB")
            )),
            SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "set", "the set of reps (1,2,3,4,)", true)
        )).createGlobal(api).join();







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
                break;
            case "isometric":
            case "i":
                break;
            case "demolist":
            case "ls":
                getStrengthUser(sci);
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
        //User user = User.getRequestId(16);
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






}  
