package org.LibreGainz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.interaction.SlashCommandInteraction;


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
        List<MessageBuilder> ret = new ArrayList<>();
        for (List<Template> objPage: Discord.objectPaginator(list)){
            MessageBuilder responder = new MessageBuilder()
            .setContent("                    ");
            int index = 0;
            for (Template template : objPage) {
                String idStr = String.valueOf(template.getId());
                responder.addComponents(createActionRow(template, index, idStr));
                index += 1;
            }
            ret.add(responder);
        }
        return ret;
    }



public static HighLevelComponent createActionRow(Template template, int index, String idStr){
    System.out.println(template.toString());
    return ActionRow.of(
        Button.secondary(
                index + ",template," + idStr,
                template.getName()
        ),  // This edits the workout
        Button.danger(index + ",templateDelete," + idStr, "🗑️")  // This deletes the workout
    );

};


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

