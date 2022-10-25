import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * ScrabbleBoard class which is a representation of the scrabble Board
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version October 25, 2022
 *
 */
public class ScrabbleBoard {

    private Square[][] scrabbleBoard;
    private Square starSquare;
    private ArrayList<Square> filledSquares;
    private final int BOARD_DIMENSION = 14; //15X15 BOARD
    private ArrayList<String> legalWords;
    private int scoreCounter;
    private ArrayList<String> wordsPlayed;
    private String playWord;
    private int wordScore;
    private Tile tile;

    /**
     * ScrabbleBoard constructor method to initialize the ScrabbleBoard.
     *
     * @throws FileNotFoundException
     */
    public ScrabbleBoard() throws FileNotFoundException {

        scrabbleBoard = new Square[BOARD_DIMENSION][BOARD_DIMENSION];
        for(int row = 0; row < BOARD_DIMENSION; row++){
            for(int column = 0; column < BOARD_DIMENSION; column++){
                scrabbleBoard[row][column] = new Square(row,column);
            }
        }

        starSquare = new Square(7,7); //MIDDLE OF BOARD
        filledSquares = new ArrayList<Square>();
        wordsPlayed = new ArrayList<String>();

        legalWords = readWords();

        scoreCounter = 0;
        wordScore = 0;
    }

    /**
     * Accessor method to return scrabble board.
     *
     * @return scrabbleBoard
     */
    public Square[][] getScrabbleBoard() {
        return scrabbleBoard;
    }

    /**
     * Accessor method to return the starSquare indicating the starting point of the game.
     *
     * @return starSquare
     */
    public Square getStarSquare() {
        return starSquare;
    }

    /**
     * Accessor method to return an arraylist of filled squares(the indexes).
     *
     * @return filledSquares
     */
    public ArrayList<Square> getFilledSquares() {
        return filledSquares;
    }

    /**
     * Accessor method to return an arraylist of words played.
     *
     * @return wordsPlayed
     */
    public ArrayList<String> getWordsPlayed() {
        return wordsPlayed;
    }

    /**
     * A method to read the words from the ScrabbleWords text file and verify if the word is legal.
     *
     * @return words
     * @throws FileNotFoundException
     */
    public ArrayList<String> readWords() throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        File acceptedWords = new File("ScrabbleWords.txt");

        Scanner sc = new Scanner(acceptedWords);

        while(sc.hasNextLine()){
            words.add(sc.nextLine());
        }
        return words;
    }

    /**
     * A method to check if the space a player is trying to play on on empty.
     *
     * @param words
     * @param s1
     * @param s2
     * @return true,
     * @return false,
     */
    public boolean checkSpaceEmpty(ArrayList<Tile> words, Square s1, Square s2){
        int x1 = s1.getRowNumber();
        int y1 = s1.getColumnNumber();
        int x2 = s2.getRowNumber();
        int y2 = s2.getColumnNumber();

        if (x1 == x2) {
            int temp = y1;

            for (int i = 0; i < words.size(); i++) {
                if (!scrabbleBoard[x1][temp].isEmpty()){
                    return false;
                }
                temp++;
            }
            return true;
        }
        if (y1 == y2){
            int temp = x1;

            for (int i = 0; i < words.size(); i++) {
                if (!scrabbleBoard[temp][y1].isEmpty()){
                    return false;
                }
                temp++;
            }
            return true;
        }
        return true;
    }

    /**
     * A method to make a possible word from the Tiles the Player has on their TileRack.
     *
     * @param words
     * @return newWord
     */
    public String wordInArray(ArrayList<Tile> words){
        String newWord = "";
        for(int i=0; i<words.size(); i++){
            newWord += words.get(i).getCharacter();
        }
        return newWord;
    }

    /**
     * A method to place a tile on a square.
     *
     * @param tile
     * @param square
     */
    public void placeTile(Tile tile,Square square){
        square.setTile(tile);
        square.setEmpty(false);
    }

    /**
     * A method to place the possible word horizontally.
     *
     * @param words
     * @param start
     * @param end
     * @param currentPlayer
     * @param temp
     * @param constant
     */
    public void placeHorizontal(ArrayList<Tile> words, Square start, Square end, Player currentPlayer, int temp, int constant){
        int score = 0;
        for (int i = 0; i < words.size(); i++) {
            if(scrabbleBoard[constant][temp].isEmpty()) {
                placeTile(words.get(i), scrabbleBoard[constant][temp]);//place the tile at the designated location
                currentPlayer.getRack().remove(words.get(i));//remove the letter currently added to the board from the players rack
                score += words.get(i).getScore();//add the score of the tile to the score
                currentPlayer.setScores(score);
                currentPlayer.getRack().remove(words.get(i));//remove the letter from players rack
            }
            temp++;//increase the value of temp so you can change the location of the placement
        }
    }

    /**
     * A method to place the possible word vertically.
     *
     * @param words
     * @param start
     * @param end
     * @param currentPlayer
     * @param temp
     * @param constant
     */
    public void placeVertically(ArrayList<Tile> words, Square start, Square end, Player currentPlayer, int temp, int constant){
        int score = 0;
        for (int i = 0; i < words.size(); i++) {
            if(scrabbleBoard[constant][temp].isEmpty()) {
                placeTile(words.get(i), scrabbleBoard[temp][constant]);//place the tile at the designated location
                currentPlayer.getRack().remove(words.get(i));//remove the letter currently added to the board from the players rack
                score += words.get(i).getScore();//add the score of the tile to the score
                currentPlayer.setScores(score);
                currentPlayer.getRack().remove(words.get(i));//remove the letter from players rack
            }
            temp++;//increase the value of temp so you can change the location of the placement

        }
    }

    /**
     * A method to place a word.
     *
     * @param words
     * @param start
     * @param end
     * @param currentPlayer
     * @return
     */
    public boolean placeWord(ArrayList<Tile> words, Square start, Square end, Player currentPlayer){
        int x1 = start.getRowNumber();
        int y1 = start.getColumnNumber();
        int x2 = end.getRowNumber();
        int y2 = end.getColumnNumber();

        if (x1 == x2) {
            int temp = y1;
            String word = wordInArray(words);//the resulted word
            if (!checkSpaceEmpty(words, start, end)){//Checks if the space to place all letters in words is empty
                if (legalWords.contains(word)){
                    placeHorizontal(words, start, end, currentPlayer, temp, x1);
                    return true;
                }else{
                    return false;
                }
            }else {
                placeHorizontal(words, start, end, currentPlayer, temp, x1);
            }
        }else if (y1 == y2){
            int temp = x1;
            String word = wordInArray(words);//the resulted word
            if (!checkSpaceEmpty(words, start, end)){//Checks if the space to place all letters in words is empty
                if (legalWords.contains(word)){
                    placeVertically(words, start, end, currentPlayer, temp, y1);
                    return true;
                }else{
                    return false;
                }
            }else {
                placeVertically(words, start, end, currentPlayer, temp, y1);
            }
            return true;
        }
        return false;
    }

    /**
     * A method to display the ScrabbleBoard.
     *
     * @return str
     */
    public String displayBoard() {
        String str = "";
        for (int row = 0; row < BOARD_DIMENSION; row++) {
            for (int column = 0; column < BOARD_DIMENSION; column++) {
                str += scrabbleBoard[row][column];
            }
            str += "\n";
        }
        return str;
    }



//    public String toString(){
//        StringBuilder board;
//        int length = BOARD_DIMENSION * BOARD_DIMENSION;
//        for(int i=0; i<length; i++){
//
//        }
//        return "";
//    }



}

