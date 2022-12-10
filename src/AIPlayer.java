/**
 * Class AI player handles all about an AI player.
 * Extends Player class and gives it the same ability as a human player.
 *
 * @version December 9th, 2022.
 */

import java.io.*;
import java.util.ArrayList;

public class AIPlayer extends Player implements Serializable {


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

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    public ArrayList<Character> getPlayerTiles() {
        return playerTiles;
    }

    public ArrayList<Character> getUsedTiles() {
        return usedTiles;
    }

    public int getTileOnBoard(char[] word, char character){
        for (int i = 0; i < word.length; i++){
            if (character == word[i]){
                return i;
            }
        }
        return -1;
    }

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

    public int getPossibleWordScore(String word){
        int score = 0;
        for(Character c : word.toCharArray()){
            score += playerTiles.get(c);
        }
        return score;
    }

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
