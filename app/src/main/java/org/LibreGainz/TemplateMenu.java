package org.LibreGainz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;


public class TemplateMenu{
public static HashMap<Long,TemplateMenu> map = new HashMap<>();
    private Long id;
    private List<MessageBuilder> list;
    public TemplateMenu(SlashCommandInteraction sci, List<Template> tList)
    {
        this.id = sci.getId();
        this.list = templateButtonMenu(sci, tList);
        map.putIfAbsent(sci.getId(),this);
        list.forEach((l) -> l.send(sci.getUser()));
    }


public static List<MessageBuilder> 
    templateButtonMenu(SlashCommandInteraction sci, List<Template> list)
        {
        //System.out.println(list.toString());
        MessageBuilder responder = new MessageBuilder()
            .setContent("Excercise List");
        List<MessageBuilder> ret = new ArrayList<>();
        for (List<Template> objPage: Discord.objectPaginator(list)){
            int index = 0;
            for (Template template : objPage) {
            String idStr = String.valueOf(template.getId());
            responder
            .addComponents(
                ActionRow.of(Button.secondary(
                    index + "," + template.getClass().getSimpleName() + "," + idStr,
                    template.toString()),  // Button to edit the template
                    Button.danger(index + ",Delete," + idStr, "Delete")    // Button to delete the template
                ));
            index += 1;
            }
            ret.add(responder);
        }
        return ret;
    }




    public List<MessageBuilder> getList() {
        return list;
    }
    public void setList(List<MessageBuilder> list) {
        this.list = list;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

