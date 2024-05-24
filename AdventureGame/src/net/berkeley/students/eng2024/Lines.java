package net.berkeley.students.eng2024;

import java.util.*;

public class Lines{
    private String hello;
    private String bye;
    private String hurt;
    private String healed;
    private String confused;

    private ArrayList<String> uniqueLines;
    private ArrayList<String> uniqueLineTriggers;


    public static ArrayList<String> helloTriggers = new ArrayList<String>(List.of("Hello", "hello"));
    public static ArrayList<String> byeTriggers = new ArrayList<String>(List.of("bye", "Bye"));

    
    public Lines(String hello, String bye, String hurt, String healed, ArrayList<String> ul, ArrayList<String> ult, String confused){
        this.hello = hello;
        this.bye = bye;
        this.hurt = hurt;
        this.healed = healed;
        this.uniqueLines = ul;
        this.uniqueLineTriggers = ult;
        this.confused = confused;

    }
    public ArrayList<String> getUniqueLines(){
        return this.uniqueLines;
    } 
    public ArrayList<String> getTriggers(){
        return this.uniqueLineTriggers;
    }
    public String getHello(){
        return this.hello;
    }
    public String getBye(){
        return this.bye;
    }
    public String getHurt(){
        return this.hurt;
    }
    public String getHealed(){
        return this.healed;
    }  
    public String getConfused(){
        return this.confused;
    }
    public String returnResponse(String ask){
        if(helloTriggers.indexOf(ask)!=-1){
            return this.getHello();
        }
        else if(byeTriggers.indexOf(ask)!=-1){
            return this.getBye();
        }
        else if(uniqueLineTriggers.indexOf(ask)!=-1){
            return this.uniqueLines.get(this.uniqueLineTriggers.indexOf(ask));
        }
        else{
            return this.getConfused();
        }

        
    }
}