package org.LibreGainz;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class TestDrive {
    public static void main(String[] args){
       /*  Template t1 = new Template(0);
        Template t2 = new Template(2);
        Template t3 = new Template(3);
        User bill = new User("BillMetric");
        User tina = new User("Tina");
        tina.setId(0);
        bill.setId(9);
        bill.setWeightUnit(Unit.KG);
        bill.setLongDistanceUnit(Unit.KM);
        bill.setDateFormatStr("dd/MM/yyyy");
        User.csvOverwrite(); */
       //System.out.print(TimeConversion.convertToSqlTime("0h 3m 3s"));

       System.out.print(TimeConversion.convertToSqlTime("3s 4m"));
        
       // Device.setUser(bill);
        //tina.setId(2);
        //bill.postRequest(); //System.out.println(Cardio.getRequest(tina));
       // System.out.println(Cardio.getRequestId(18));


        //System.out.println(Strength.getRequestUser(bill).toString());
        //tina.setId(2);
        //bill.postRequest(); //System.out.println(Cardio.getRequest(tina));
        //System.out.println(Cardio.getRequestId(18));
        System.out.println(User.getRequestName("remllez").toString());
        System.out.println(User.getRequestId(16).toString());
    


    java.util.Date date = new java.util.Date();
    java.sql.Date endDate = new Date(date.getTime()); //get today
    java.sql.Date startDate= (new Date(System.currentTimeMillis()-168*60*60*1000)); //get last week



        System.out.println(
        Strength.getRequestDate(User.getRequestName("remllez"), startDate, endDate, 10).toString()
        );

        //This is broken. Not sure why.
        System.out.println(
        Strength.getRequestDate(User.getRequestName("remllez"),3, startDate, endDate, 10).toString()
        );


       // Template template = new Template(12);
       // template.setName("BenchPress");
       // template.setUserId(16);
       // template.setDesc("Obnoxious chest bouncing");
       // template.setWorkoutType("Strength");
       // System.out.println(template.toString());
       // template.postRequest();
        //System.out.println(User.getRequestName("remllez").toString());
        //System.out.println(User.getRequestId(16).toString());

        //System.out.println(Cardio.getRequestUser(tina).toString());
        //System.out.println(Strength.getRequestUser(tina).toString());
        //System.out.println(Isometric.getRequestUser(tina).toString());
        


      


        //Template.csvLoad("data//Template.csv");
    //    System.out.println(Strength.jsonParse().toString());
    //    System.out.println(Isometric.jsonParse().toString());
    //    System.out.println(Cardio.jsonParse().toString());
    //Strength.jsonPost("http://remllez.com:8080/0/strength", (List<Strength>) Strength.map.values());
    //Cardio c = new Cardio(2);
    //c.setAnnotation("Esker");
    //c.setDistance(123.123);
    //c.setTags(Workout.strToTags("Esker, Drumlin"));
    //c.setTime(Time.valueOf("33:22:11"));
    //c.setUnit(Unit.KM);
    //System.out.println(Cardio.map.toString());

    /* 
    Cardio.getRequestAll().forEach((w) ->
       {
        System.out.println(w.toString());
        Cardio.map.putIfAbsent(w.workoutId, w);
        w.setDistance(500.000);
        w.patchRequest();
        System.out.println(w.toString());
        }
    );

    */

    
    //Cardio.map.values().forEach((w) ->
    //{
    //w.jsonDelete();
    //}
    //);
    
    //System.out.println(Cardio.jsonParse().toString());
    //Cardio.map.values().forEach((w)-> w.jsonDelete());
    //Cardio.csvLoad("data//Cardio.csv");
    //Cardio.jsonPost();
    //System.out.println(Cardio.jsonParse().toString());

    }

}
