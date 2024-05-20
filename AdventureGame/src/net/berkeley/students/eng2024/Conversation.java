public class Conversation implements Event{
    private Npc npc;
    private Player player;
    public Conversation(Npc npc, Player player){
        this.npc = npc;
        this.player = player;
    }

    public String getPossibleAsks(){
        String returnStr = "";
        Lines.helloTriggers.forEach(str -> returnStr+=str + ", ");
        Lines.byeTriggers.forEach(str -> returnStr+=str + ", ");
        npc.getUiqueTriggers().forEach(str -> returnStr+=str + ", ");
    
        return returnStr;
    }


    public String diolog(String ask){
        return npc.diolog(ask);
    }



}