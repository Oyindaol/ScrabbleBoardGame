/**
 * Player class handles all about a player. The constructor takes in the players name,
 * initializes the players scores to 0.
 *
 * @author Ese C. Iyamu (101081699)
 * @version November 13, 2022
 */

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final String playerName;
    private int playerScore;
    private ArrayList<Character> playerTiles;
    private ArrayList<Character> usedTiles;


    // CONSTRUCTOR
    public Player(String name) {
        if(name.length() == 0)
            name = "Player X";

        playerName = name;
        playerScore = 0;
        playerTiles = new ArrayList<>();
        usedTiles = new ArrayList<>();
    }

    // GETTERS
    // only really used for turn order
    public char getFirstTile() {
        return playerTiles.get(0);
    }

    public ArrayList<Character> getRack() {
        return playerTiles;
    }


    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    // SETTERS
    // assigns new tiles to player no matter if empty or partly filled
    // stops when 7 tiles is reached or no more tiles available
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
    public void resetRack() {
        playerTiles.clear();
        playerTiles.addAll(usedTiles);
    }
}