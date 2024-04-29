public class Map{
    private List[] rooms = new ArrayList<>();

    private EmptyItem ei = new EmptyItem();

    public void loadRooms(){
        //item[][] Grid, String Distckripshon, int num, int botem, int left, int right, int top
        rooms.add(new room(
            new IItem[][]{{ei,ei,ei},{ei,IItem.POTION_OF_HEALING,ei},{ei,ei,ei}},
            "A room with a potion!!!!!! YOOOO",
            1,
            null,
            null,
            null,
            null
        ));
    }
}