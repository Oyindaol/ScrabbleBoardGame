import java.util.ArrayList;

/**
 * The TileRack class represents a rack in the scrabble game which holds
 * seven random character/letter tiles.
 *
 * @author Edidiong Okon (101204818)
 * @version October 25, 2022
 */

public class TileRack {
    private ArrayList<Tile> rack;
    //private ArrayList<Tile> rack = new ArrayList<>(7);

    /**
     * Constructor for rack object of class TileRack which holds seven tiles.
     */
    public TileRack(){
        rack = new ArrayList <>(7);
    }

    /**
     * Returns the current rack.
     * @return the rack (that is, the characters in the current rack)
     */
    public ArrayList<Tile> getRack(){
        return rack;
    }

    /**
     * Adds the specified tile to a rack.
     * @param tile The tile that is added to the rack
     */
    public void put(Tile tile){
        rack.add(tile);
    }

    /**
     * Removes the specified tile from the current rack.
     * @param tile The tile that is removed from the rack
     */
    public void remove(Tile tile){
        rack.remove(tile);
    }
}

