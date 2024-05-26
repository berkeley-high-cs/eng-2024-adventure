package net.berkeley.students.eng2024;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.util.Arrays;

public class Save{
    // public static AdventureGame download(String path){
    //     BufferedReader br;
    //     try{
    //         br=new BufferedReader(Path.of(path));
    //     }
    //     catch(IOException e){
    //         System.out.println("The file you are trying to load a save state from does not exist.");
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }
    public static void upload(String path){
        FileWriter fw;
        try{
            fw=new FileWriter(path);
        }
        catch(IOException io){
            if(System.console().readLine("This file does not exist, create it? (y/n)").equals("y")){
                try{
                    File f=new File(path);
                    f.createNewFile();
                    fw=new FileWriter(f);
                    fw.write(toCSV());
                    fw.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String toCSV(){
        GameMap gm=AdventureGame.map;
        Player p=AdventureGame.player;
        Room[] rooms=gm.rooms();
        Passage[] passages=gm.passages();
        List<Item> inventory=p.items();
        String roomString=roomsToCSV(Arrays.asList(rooms));
        String passageString=passagesToCSV(Arrays.asList(passages));
        String inventoryString=inventoryToCSV(inventory);
        String visited=roomsToCSV(p.visitedRooms());
        String passagesTaken=passagesToCSV(p.getPassages());
        return "{"+roomString+"},{"+passageString+"},{"+inventoryString+"},{"+visited+"},{"+passagesTaken+"}";
    }
    public static String roomsToCSV(List<Room> rooms){
        StringBuilder sb=new StringBuilder();
        for(Room r: rooms){
            sb.append("{"+roomToCSV(r)+"}");
        }
        return sb.toString();
    }
    public static String roomToCSV(Room room){
        StringBuilder foo=new StringBuilder();
        foo.append(room.codeName()+";"+room.description());
        foo.append("pas{"+passagesToCSV(room.passages())+"}");
        foo.append("ent{"+entitiesToCSV(room.entities())+"}");
        return foo.toString();
    }
    public static String passagesToCSV(List<Passage> passes){
        StringBuilder sb=new StringBuilder();
        for(Passage p:passes){
            sb.append("{"+passageToCSV(p)+"}");
        }
        return sb.toString();
    }
    public static String passageToCSV(Passage pass){
        StringBuilder foo=new StringBuilder();
        foo.append(pass.getName()+"{");
        for(String n:pass.getAllNames()){
            foo.append(n+",");
        }
        foo.deleteCharAt(foo.length()-1);
        foo.append("}"+pass.getMovementDescription()+"{"+roomToCSV(pass.getRoom1())+"},{"+roomToCSV(pass.getRoom2())+"}"+pass.isAccessible());
        return foo.toString();
    }
    public static String inventoryToCSV(List<Item> inv){
        StringBuilder sb=new StringBuilder();
        for(Item i:inv){
            sb.append("{"+itemToCSV(i)+"}");
        }
        return sb.toString();
    }
    public static String itemToCSV(Item i){
        StringBuilder foo=new StringBuilder();

        return foo.toString();
        
    }
    public static String entitiesToCSV(List<Entity> entities){
        StringBuilder sb;
        for(Entity e:entities){
            sb.append("{"+entityToCSV(i)+"}");
        }
    }
    public static String entityToCSV(Entity e){
        StringBuilder foo=new StringBuilder();

        return foo.toString();
    }
}