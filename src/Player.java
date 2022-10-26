import java.util.*;
/**
 * Class player handles all about a player. The constructor takes in the players name,
 * initializes the players scores to 0.
 *
 * @author: Iyamu C. Ese (101081699)
 * @version October 25th, 2022.
 */
public class Player {


    private String playerName;
    private int lastRoundScore, totalScore;
    ArrayList<Tile> playerRack = new ArrayList<>(7);
    /**
     * Constructor for the class Player
     * @param name  name of player
     */
    public Player(String name){
        playerName = name;
        lastRoundScore = 0;
        totalScore = 0;
    }

    /**
     * Getter method for variable name
     * @return name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getter method for variable lastRoundScore
     * @return lastRoundScore
     */
    public int getLastRoundScore() {
        return lastRoundScore;
    }

    /**
     * Getter method for variable totalScore;
     * @return totalScore
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * getRack gets players tiles rack
     * @return playerRack
     */
    public ArrayList<Tile> getRack() {
        return playerRack;
    }

    /**
     * setScores keeps track of each player's score.
     * @param score
     */
    public void setScores(int score) {
        this.lastRoundScore = score;
        this.totalScore += score;
    }
}

