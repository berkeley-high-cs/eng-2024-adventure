import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;

class Save{
    private FileWriter fw;
    private BufferedReader br;
    public Save(String path, AdventureGame game){
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
    }
    public Save(String path){
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
    public AdventureGame download(){
        
    }
    public void upload(){

    }
}