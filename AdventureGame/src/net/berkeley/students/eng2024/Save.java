package net.berkeley.students.eng2024;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;

static class Save{
    public static AdventureGame download(String path){
        BufferedReader br;
        try{
            br=new BufferedReader(Path.of(path));
        }
        catch(IOException e){
            System.out.println("The file you are trying to load a save state from does not exist.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void upload(String path){
        FileWriter fw;
        try{
            fw=new FileWriter(path);
        }
        catch(IOException io){
            if(System.Console.readLine("This file does not exist, create it? (y/n)").equals("y")){
                File f=new File(path);
                f.createNewFile();
                fw=new FileWriter(f);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        fw.write(toCSV());
    }
    public static String toCSV(){
        GameMap gm=AdventureGame.map;
        Player p=AdventureGame.player;
        Room[] rooms=gm.getRooms();
        Passage[] passages=gm.getPassages();
        Item[] inventory=p.getItems().toArray();
        String roomString=roomsToCSV(rooms);
        String passageString=passagesToCSV(passages);
        String inventoryString=inventoryToCSV(inventory);
        String visited=roomsToCSV(p.getVisitedRooms());
        String passagesTaken=passagesToCSV(p.getPassages());
    }
    public static String roomsToCSV(Room[] rooms){
        StringBuilder sb=foo;
        for(Room r: rooms){
            sb.append("{"+roomToCSV(r)+"}");
        }
        return sb.toString();
    }
    public static String roomToCSV(Room room){
        StringBuilder foo;
        foo.append(room.getName()+";"+room.getDescription());
        foo.append("pas{"+passagesToCSV(room.getPassages())+"}");
        foo.append("ent{"+entitiesToCSV(room.getEntities())+"}");
        return foo.toString();
    }
    public static String passagesToCSV(Passage[] passes){
        StringBuilder sb;
        for(Passage p:passes){
            sb.append("{"+passageToCSV(p)+"}");
        }
        return sb.toString();
    }
    public static String passageToCSV(Passage pass){
        StringBuilder foo;
        foo.append(pass.getName()+"{");
        for(String n:pass.getAllNames()){
            foo.append(n+",");
        }
        foo.deleteCharAt(foo.length()-1);
        foo.append("}"+pass.getMovementDescription()+"{"+roomToCSV(pass.getRoom1())+"},{"+roomToCSV(pass.getRoom2())+"}"+pass.isAccessible());
        return foo.toString();
    }
    public static String inventoryToCSV(Item[] inv){
        StringBuilder sb;
        for(Item i:inv){
            sb.append("{"+itemToCSV(i)+"}");
        }
        return sb.toString();
    }
    public static String itemToCSV(Item i){
        StringBuilder foo;
        
    }
    public static String entitiesToCSV(Entity[] entities){
        StringBuilder sb;
        for(Entity e:entities){
            sb.append("{"+entityToCSV(i)+"}");
        }
    }
    public static String entityToCSV(Entity e){

    }
}