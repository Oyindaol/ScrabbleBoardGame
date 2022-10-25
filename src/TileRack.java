import java.util.ArrayList;

public class TileRack {
    private ArrayList<Tile> rack = new ArrayList<>(7);

    public ArrayList<Tile> getRack(){
        return rack;
    }
    public void put(Tile tile){
        rack.add(tile);
    }
    public void remove(Tile tile){
        rack.remove(tile);
    }
}

