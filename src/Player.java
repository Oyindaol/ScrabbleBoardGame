/**
 * Class player handles all about a player. The constructor takes in the players name,
 * initializes the players scores to 0.
 *
 * @author: Iyamu C. Ese (101081699)
 * @version December 9th, 2022.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Serializable {
    private final String playerName;
    public int playerScore;
    private ArrayList<Character> playerTiles;
    private ArrayList<Character> usedTiles;


    public Player(String name) {
        if(name.length() == 0)
            name = "Player X";

        playerName = name;
        playerScore = 0;
        playerTiles = new ArrayList<>();
        usedTiles = new ArrayList<>();
    }

    /**
     * Getter method to return Player's first tile
     * @return playerTiles.get(0)
     */
    public char getFirstTile() {
        return playerTiles.get(0);
    }

    /**
     * Getter method to return the players rack
     *
     * @return playerTiles
     */
    public ArrayList<Character> getRack() {
        return playerTiles;
    }

    /**
     * Getter method to return the players name
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getter method to return the players score
     *
     * @return playerScore
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Setter method to fill the rack to 7 tiles at the beginning of each turn
     * disables when there are no more tiles in the bag
     */
    public void fillRack() {
        Random r = new Random();

        for(int i = 0; i < 7; i++){
            if(!ScrabbleModel.getCharacters().isEmpty() && playerTiles.size() < 7){
                int e = r.nextInt(ScrabbleModel.getCharacters().size());
                playerTiles.add(ScrabbleModel.getCharacters().get(e));
                ScrabbleModel.getCharacters().remove(e);
            }
        }
        usedTiles.clear();
        usedTiles.addAll(playerTiles);
    }

    /**
     * Setter method to clear the rack.
     */
    public void resetRack() {
        playerTiles.clear();
        playerTiles.addAll(usedTiles);
    }
}