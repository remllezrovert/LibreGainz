package org.LibreGainz;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
    private long id;
    private int userId;
    @JsonProperty("timestamp")
    private Timestamp sync;
    private static String baseUrl = "http://remllez.com:8080";
    private static User user = new User("tina");

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static User getUser(){
        return user;
    }

    public static void setUser(User newUser){
        user = newUser;
    }

    public Timestamp getSync() {
        return sync;
    }
    public void setSync(Timestamp sync) {
        this.sync = sync;
    }

     public static String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String newBaseUrl) {
        baseUrl = newBaseUrl;
    }




}
