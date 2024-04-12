package org.LibreGainz;

public class TestDrive {
    public static void main(String[] args){
        Template t1 = new Template(0);
        Template t2 = new Template(2);
        Template t3 = new Template(3);
        System.out.println(Strength.jsonParse().toString());
        System.out.println(Isometric.jsonParse().toString());
        System.out.println(Cardio.jsonParse().toString());

    }

}
