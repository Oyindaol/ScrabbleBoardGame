import java.util.ArrayList;

/**
 * AI player class.
 * Extends the Player class
 * Plays the best possible word that returns the highest score based on the tiles already on the board.
 */
public class AIPlayer extends Player {


    private final String playerName;
    public int playerScore;
    private ArrayList<Character> playerTiles;
    private ArrayList<Character> usedTiles;
    private boolean playerTurn;

    public AIPlayer(String name) {
        super(name);
        this.playerName = "ScrabbleAI";
        this.playerScore = 0;
        playerTiles = new ArrayList<>();
        usedTiles = new ArrayList<>();
        playerTurn = false;
    }

    /**
     * Getter method for player name
     * @return playerName
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getter method for playerScore
     * @return playerScore
     */
    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Getter method for player tiles
     * @return playertiles
     */
    public ArrayList<Character> getPlayerTiles() {
        return playerTiles;
    }

    /**
     * getter method to return played tiles
     * @return usedTiles
     */
    public ArrayList<Character> getUsedTiles() {
        return usedTiles;
    }

    /**
     * Getter method to return tiles on the board so AI is able to make a move
     * @param word
     * @param character
     * @return
     */
    public int getTileOnBoard(char[] word, char character){
        for (int i = 0; i < word.length; i++){
            if (character == word[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * Getter method to return possible words from a combination of tiles on the rack
     * @param character
     * @return possibleWords
     */
    public ArrayList<String> getPossibleWords(char character){

        char[] charac;
        if (character != ' '){
            charac = new char[playerTiles.size() + 1];
        }
        else{
            charac = new char[playerTiles.size()];
        }

        ArrayList<String> possibleWords = new ArrayList<String>();

        return possibleWords;
    }

    /**
     * Getter method to return score of possible words from a combination of tiles on the rack
     * @param word
     * @return score
     */
    public int getPossibleWordScore(String word){
        int score = 0;
        for(Character c : word.toCharArray()){
            score += playerTiles.get(c);
        }
        return score;
    }

    /**
     * Getter method to return the highest score from possible
     * words made from a combination of tiles on the rack
     * @param possibleWords
     * @return
     */
    public String getHighestWordScore(ArrayList<String> possibleWords){
        String word = " ";
        int score = 0;
        for(String s : possibleWords){
            int temp = getPossibleWordScore(s);
            if(temp > score){
                score = temp;
                word = s;
            }
        }
        return word;
    }

}
