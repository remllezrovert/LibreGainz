package java111;
import java.util.ArrayList;
import java.io.FileWriter;
/**
 * @author Remllez
 */
public class Template{
public static ArrayList<Template> allTemplates = new ArrayList<Template>();
private int userId;
private int templateId;
private String desc;
private ArrayList<String> tags = new ArrayList<String>();
Template(int userId){
this.userId = userId;
this.templateId = allTemplates.size();
allTemplates.add(this);
}
/**
 * Get the id of this template
 * @return
 */
public int getTemplateId(){
    return templateId;
}
/**
 * Get the id of the user that owns this template
 * @return
 */
public int getUserId(){
return this.userId;
}
/**
 * Get a string describing the workout
 * @return
 */
public String getDesc(){
return desc;
}
/**
 * Set the description for this object
 * @param newDesc
 */
public void setDesc(String newDesc){
this.desc = newDesc;
}
/**
 * Return a ArrayList<String> with all of the tags 
 * @return TagArray
 */
public ArrayList<String> getTags(){
    return tags;
}
/**
 * Replace the current tags array with a new one
 * @param newTags
 */
public void setTags(ArrayList<String> newTags){
tags = newTags;
}
/**
 * Add a new tag to the tag ArrayList<String>
 * @param newTag
 */
public void addTag(String newTag){
tags.add(newTag);
}
/**
 * Delete a tag from the ArrayList<String>
 * @param tagIndex
 */
public void delTag(int tagIndex){
tags.remove(tagIndex);
}
/**
 * Get a String representing this object for use in CSV files
 */
public String toString(){
return userId +
    "," +  templateId +
    ",\"" + desc + "\"," + 
    "\"" + tags.toString().substring(1, tags.toString().length() - 1) + "\"";
}
/*
 * Append the toString() for this object onto a CSV file.
 */
public void csvAppend(){
    try(FileWriter writer = new FileWriter("data//Template.csv", true)){
        writer.write(this.toString()+"\n");
    } catch(Exception e){

    }
}

}
