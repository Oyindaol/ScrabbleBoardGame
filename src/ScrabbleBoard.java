import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * ScrabbleBoard class which is a representation of the scrabble Board
 * @author Oyindamola Taiwo-Olupeka 101155729
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

    public Square[][] getScrabbleBoard() {
        return scrabbleBoard;
    }

    public Square getStarSquare() {
        return starSquare;
    }

    public ArrayList<Square> getFilledSquares() {
        return filledSquares;
    }

    public ArrayList<String> getWordsPlayed() {
        return wordsPlayed;
    }

    public ArrayList<String> readWords() throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        File acceptedWords = new File("ScrabbleWords.txt");

        Scanner sc = new Scanner(acceptedWords);

        while(sc.hasNextLine()){
            words.add(sc.nextLine());
        }
        return words;
    }

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

    public String wordInArray(ArrayList<Tile> words){
        String newWord = "";
        for(int i=0; i<words.size(); i++){
            newWord += words.get(i).getCharacter();
        }
        return newWord;
    }

    public void placeTile(Tile tile,Square square){
        square.setTile(tile);
        square.setEmpty(false);
    }

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




    public String toString(){
        StringBuilder board;
        int length = BOARD_DIMENSION * BOARD_DIMENSION;
        for(int i=0; i<length; i++){

        }

        return "";
    }
}