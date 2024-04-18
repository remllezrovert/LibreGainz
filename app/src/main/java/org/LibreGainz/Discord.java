package org.LibreGainz;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;


import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;

import org.javacord.api.interaction.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;


public class Discord {

    public static void main(String[] args) {
        // Insert your bot's token here
        String token = "";
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        SlashCommand.with("ping", "A simple ping pong command!").createGlobal(api).join();



        // This adds a new user
        api.addSlashCommandCreateListener(event -> {
         SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
         if (slashCommandInteraction.getCommandName().equals("ping")) {

            String name = slashCommandInteraction.getUser().getName();


             slashCommandInteraction.createImmediateResponder()
                 .setContent(name)
                 .setFlags(MessageFlag.EPHEMERAL) // Ensure this is visible only to the user
                 .respond();

         }
     });


    api.addSlashCommandCreateListener(event -> {
    if (event.getSlashCommandInteraction().getCommandName().equals("bing")) {
        event.getSlashCommandInteraction().createImmediateResponder().setContent("Bong!").respond();
    }

});
    }

   
}  
