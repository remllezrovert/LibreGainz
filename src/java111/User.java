package java111;
import java.text.SimpleDateFormat;
/**
 * @author Remllez
 * This class is for users, which have a name and unqiue id.
 * Possible plans to impliment multi-user mode
 */
public class User{
    private static String dateFormatStr = "MM/dd/yyyy";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
    public static Unit weightUnit = Unit.LB;
    public static Unit longDistanceUnit = Unit.MI;
    public static Unit shortDistanceUnit = Unit.M;

    private int userId = 1;
    private String userName;
    public User(String userName){
        this.userName = userName;
    }
    /**
     * Get the name of the user
     * @return userName
     */
    public String getUserName(){
        return userName;
    }
    /**
     * Get the id of the user
     * @return userId
     */
    public int getUserId(){
        return userId;
    }
}
