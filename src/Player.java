import java.util.*;
/**
 * Class player handles all about a player. the constructor takes in the players name,
 * initializes the players scores to 0 and their rack with random letters
 *
 * @author: Iyamu C. Ese
 */
public class Player {
    //private ScrabbleGame game;
    // private TileRack tileRack;
    // private tile tile;

    private String playerName;
    private int lastRoundScore, totalScore;
    //private char gameLetters;
    Random rand = new Random();
    ArrayList<Tile> playerRack = new ArrayList<>(7);
    /**
     * Constructor for the class Player
     * @param name  name of player
     * //@param lastRoundScore  Score for round just played
     * //@param totalScore  Total game score
     * // @param playerRack  Players letters
     */
    public Player(String name){
        playerName = name;
        lastRoundScore = 0;
        totalScore = 0;

        //Initialize players with random letters
//        for (int i = 0; i < playerRack.length; i++){
//            playerRack[i] = (char)(rand.nextInt(27) + 'A');
//            System.out.println(playerRack[i]);
//        }

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


    public ArrayList<Tile> getRack() {
        return playerRack;
    }

    public void setScores(int score) {
        this.lastRoundScore = score;
        this.totalScore += score;
    }
}

