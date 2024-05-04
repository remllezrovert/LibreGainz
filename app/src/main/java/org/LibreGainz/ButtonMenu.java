package org.LibreGainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;

public class ButtonMenu{

    public static HashMap<Long,ButtonMenu> map = new HashMap<>();
    private Long id;
    private List<MessageBuilder> list;
    public <T extends Workout> ButtonMenu(SlashCommandInteraction sci, List<T> list)
    {
        this.id = sci.getId();
        this.list = workoutButtonMenu(sci, list);
        map.putIfAbsent(sci.getId(),this);
    }

//constructor takes sci and list<t>
//object has attribute list
// listIndex,seeMore,menuId
 
public <T extends Workout>  List<MessageBuilder> 
workoutButtonMenu(SlashCommandInteraction sci, List<T> list2) {
    List<MessageBuilder> ret = new ArrayList<>();
        for (List<T> objPage : Discord.objectPaginator(list2)) {
            MessageBuilder responder = new MessageBuilder()
                    .setContent("                            ");
            int index = 0;
            for (T workout : objPage) {
                String idStr = String.valueOf(workout.getId());
                responder.addComponents(createActionRow(workout, index, idStr));
                index += 1;
            }
            ret.add(responder);
        }
    return ret;
}


public static <T extends Workout> HighLevelComponent createActionRow(T workout, int index, String idStr){
    return ActionRow.of(
        Button.secondary(
                index + "," + workout.getClass().getSimpleName() + "," + idStr,
                workout.toString2()),  // This edits the workout
        Button.danger(index + ",Delete," + idStr, "🗑️")  // This deletes the workout
    );

};











   
    public List<MessageBuilder> getList() {
        return list;
    }
    public void setList(List<MessageBuilder> list) {
        this.list = list;
    }

    


    
}
