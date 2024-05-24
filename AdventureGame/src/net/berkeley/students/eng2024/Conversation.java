import java.lang.reflect.Array;
import java.util.ArrayList;

public class Conversation implements Event{
    private Npc npc;
    public Conversation(Npc npc){
        this.npc = npc;
    }

    public String getPossibleAsks(){
        ArrayList<String> listAr = new ArrayList<String>();
        listAr.addAll(Lines.helloTriggers);
        listAr.addAll(Lines.byeTriggers);
        listAr.addAll(this.npc.getUiqueTriggers());


        String str = "";
        for(int i = 0; i < listAr.size(); i++){
            str += listAr.get(i) + ", ";
        }
        return str;
    }

        
    public String diolog(String ask){
        return npc.diolog(ask);
    }



}