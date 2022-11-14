/**
 * Class ScrabbleModel is the Game class in Scrabble.
 * For this milestone (Milestone 2), the game would be played via mouse.
 * To play game, players would have to select the tiles and place them on the boards
 * The first player must start on the centre square.
 * The game keeps track of the information of 2-4 players. Determines the winner from the highest
 * score between the players.
 *
 * @author Oluwatomisin Ajayi (101189490)
 * @version November 13, 2022;
 *
 */
import java.io.*;
import java.util.*;

//	ScrabbleModel class (implemented as a Singleton) which contains all information pertaining to the game.
//	Player information is stored in a private player class within ScrabbleModel, handled by ScrabbleModel methods.


public class ScrabbleModel {
    private static ScrabbleModel instance = null;
    private final char[][] defaultBoard;
    private final char[][] newBoard;
    private final char[][] currentBoard;

    private Player player;
    private final char[] tileBag;
    private final Map<Character, Integer> tiles;
    private static ArrayList<Character> characters;

    private final ArrayList<Player> players;
    private final ArrayList<Integer> turnList;

    private int turnIndex, currentRow, currentCol;
    private boolean isFirstPlay, isDoubleWord, isTripleWord;
    private int turn;

    private List<ScrabbleView> views;

    /**
     * ScrabbleModel constructor
     * Initializes a the default scrabble board and the bonuses
     * Adds the tiles into the tile bag for them to be later distributed to the 2-4 players
     * Manages the views
     */
    public ScrabbleModel() {
        //Default state of a scrabble with bonus values hard-coded
        //0 = blank
        //1 = starting point
        //2 = double letter
        //3 = triple letter
        //4 = double word,
        //5 = triple word
        //initializes the default board according to the integers
        defaultBoard = new char[][] {
                {5,0,0,2,0,0,0,5,0,0,0,2,0,0,5},
                {0,4,0,0,0,3,0,0,0,3,0,0,0,4,0},
                {0,0,4,0,0,0,2,0,2,0,0,0,4,0,0},
                {2,0,0,4,0,0,0,2,0,0,0,4,0,0,2},
                {0,0,0,0,4,0,0,0,0,0,4,0,0,0,0},
                {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0},
                {0,0,2,0,0,0,2,0,2,0,0,0,2,0,0},
                {5,0,0,2,0,0,0,1,0,0,0,2,0,0,5},
                {0,0,2,0,0,0,2,0,2,0,0,0,2,0,0},
                {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0},
                {0,0,0,0,4,0,0,0,0,0,4,0,0,0,0},
                {2,0,0,4,0,0,0,2,0,0,0,4,0,0,2},
                {0,0,4,0,0,0,2,0,2,0,0,0,4,0,0},
                {0,4,0,0,0,3,0,0,0,3,0,0,0,4,0},
                {5,0,0,2,0,0,0,5,0,0,0,2,0,0,5}
        };

        //active scrabble board in the game
        currentBoard = new char[15][15];
        for(int row = 0; row < defaultBoard.length; row++) {
            for (int col = 0; col < defaultBoard.length; col++) {
                currentBoard[row][col] = defaultBoard[row][col];
            }
        }

        //this will initialize a new board when you start a new game
        newBoard = new char[15][15];
        for(int row = 0; row < defaultBoard.length; row++) {
            for (int col = 0; col < defaultBoard.length; col++) {
                newBoard[row][col] = defaultBoard[row][col];
            }
        }

        //Characters are removed and placed into players rack
        //keeps track of the character distribution in the game
        tileBag = new char[] {'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D',
                'D','D','E','E','E','E','E','E','E','E','E','E','E','E','F',
                'F','G','G','G','H','H','I','I','I','I','I','I','I','I','I',
                'J','K','L','L','L','L','M','M','N','N','N','N','N','N','O',
                'O','O','O','O','O','O','O','P','P','Q','R','R','R','R','R',
                'R','S','S','S','S','T','T','T','T','T','T','U','U','U','U',
                'V','V','W','W','X','Y','Y', 'Z',' ',' '};


        // values of each character
        tiles = new HashMap<>();
        tiles.put('A', 1);
        tiles.put('B', 3);
        tiles.put('C', 3);
        tiles.put('D', 2);
        tiles.put('E', 1);
        tiles.put('F', 4);
        tiles.put('G', 2);
        tiles.put('H', 4);
        tiles.put('I', 1);
        tiles.put('J', 8);
        tiles.put('K', 5);
        tiles.put('L', 1);
        tiles.put('M', 3);
        tiles.put('N', 1);
        tiles.put('O', 1);
        tiles.put('P', 3);
        tiles.put('Q', 10);
        tiles.put('R', 1);
        tiles.put('S', 1);
        tiles.put('T', 1);
        tiles.put('U', 1);
        tiles.put('V', 4);
        tiles.put('W', 4);
        tiles.put('X', 8);
        tiles.put('Y', 4);
        tiles.put('Z', 10);

        characters = new ArrayList<>();
        for(char i : tileBag) {
            characters.add(i);
        }

        //Arraylist of the players in the game
        players = new ArrayList<>();

        // turn order list
        turnList = new ArrayList<>();
        turnIndex = 0;
        isFirstPlay = true;
        turn = 1;

        // indicates the current row and col
        currentRow = -1;
        currentCol = -1;

        isDoubleWord = false;
        isTripleWord = false;

        views = new ArrayList<>();
    }

    /**
     * Adds the views of the model to an arraylist of views
     *
     * @param v
     */
    public void addScrabbleView(ScrabbleView v){
        views.add(v);
    }

    /**
     * Getter method to return the tile character
     *
     * @return characters
     */
    public static ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * Getter method to return which players turn it is.
     * Players are assigned integer values to represent them.
     *
     * @return turn
     */
    public int getTurn(){
        return turn;
    }

    /**
     * Getter method to return the winning player by getting the highest score
     *
     * @return winningPlayer
     */
    public ArrayList<Integer> getWinners() {
        int index = 0;
        ArrayList<Integer> winningPlayer = new ArrayList<>();
        for(int row = 1; row < players.size(); row++) {
            if(players.get(row).getPlayerScore() >= players.get(index).getPlayerScore()) {
                index = row;
            }
        }
        for(int row = 0; row < players.size(); row++) {
            if(players.get(row).getPlayerScore() == players.get(index).getPlayerScore()) {
                winningPlayer.add(row);
            }
        }
        return winningPlayer;
    }

    /**
     * Getter method to return the character stored in a square(row,col)
     * @param row
     * @param col
     *
     * @return defaultBoard[row][col]
     */
    public char getSquare(int row, int col) {
        return defaultBoard[row][col];
    }

    /**
     * Getter method to return number of players in the game
     *
     * @return players.size()
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Getter method to return the racks of all players
     *
     * @return players.get(turnList.get(turnIndex)).getRack();
     */
    public ArrayList<Character> getCurrentPlayerRack(){
        return players.get(turnList.get(turnIndex)).getRack();
    }

    /**
     * Getter method to return the name of the player
     *
     * @return players.get(turnList.get(turnIndex)).getPlayerName();
     */
    public String getCurrentPlayerName() {
        return players.get(turnList.get(turnIndex)).getPlayerName();
    }

    /**
     * Getter method to return a player name based on index, used to show all scores
     * @param index
     * @return players.get(index).getPlayerName();
     */
    public String getPlayerName(int index) {
        return players.get(index).getPlayerName();
    }

    /**
     * Getter method to return a player score based on index, used to show all scores
     * @param index
     * @return players.get(index).getPlayerScore();
     */
    public int getPlayerScore(int index) {
        return players.get(index).getPlayerScore();
    }

    /**
     * Setter method to reset the players rack
     */
    public void resetPlayerRack() {
        players.get(turnList.get(turnIndex)).resetRack();
    }

    /**
     * Setter method to add a player with the given name
     * @param name
     */
    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    /**
     * Method to remove the selected tile from players rack
     * @param c
     */
    public void removePlayerTile(char c) {
        ArrayList<Character> list = players.get(turnList.get(turnIndex)).getRack();

        for(int row = 0; row < list.size(); row++) {
            if(Character.toLowerCase(list.get(row)) == (Character.toLowerCase(c))) {
                list.remove(row);
                break;
            }
        }
    }

    /**
     * Method to play the selected tile on the board
     * @param letter
     * @param row
     * @param col
     */
    public void playTile(char letter, int row, int col) {
        defaultBoard[row][col] = letter;
    }

    /**
     * Method to calculate score of tile placed on bonus squares
     * @param c
     * @param row
     * @param col
     * @return
     */
    public int calculateScore(char c, int row, int col) {
        switch (currentBoard[row][col]) {
            case 1: //start of the game and also double word bonus
                isDoubleWord = true;
                return tiles.get(Character.toUpperCase(c));
            case 2:
                return tiles.get(Character.toUpperCase(c)) * 2;
            case 3:
                return tiles.get(Character.toUpperCase(c)) * 3;
            case 4:
                isDoubleWord = true;
                return tiles.get(Character.toUpperCase(c));
            case 5:
                isTripleWord = true;
                return tiles.get(Character.toUpperCase(c));
            default:
                return tiles.get(Character.toUpperCase(c));
        }
    }

    /**
     * Method to add score to current player after each play
     * @param score
     */
    public void addScore(int score) {
        int tempScore = players.get(turnList.get(turnIndex)).getPlayerScore();
        tempScore += score;
    }

    /**
     * Clear players
     */
    public void resetPlayers() {
        players.clear();
    }

    /**
     * Used to initialize the players, their racks, and keep track of their turns
     */
    public void setUpPlayers(){
        TreeMap<String,Integer> turnsInfo = new TreeMap<>();
        int e = 0;
        for(Player i : players) {
            i.fillRack();
            turnsInfo.put(i.getFirstTile()+String.valueOf(e),e);
            e++;
        }
        // populate the turn list
        for(Map.Entry<String,Integer> i : turnsInfo.entrySet()) {
            turnList.add(i.getValue());
        }
    }

    /**
     * Method to check if a players turn is over
     * @return completeTurn
     */
    public boolean endTurn() {
        boolean completeTurn = false;

        // return false if no changes have been made
        if(currentRow == -1 && currentCol == -1) {
            return completeTurn;
        }

        // check all words that have been made
        int row = currentRow;
        int col = currentCol;
        int scoreTotal = 0;
        StringBuilder builder = new StringBuilder();

        // move marker horizontally to end of letters
        while(isLetter(row, col)) {
            row++;
        }

        // move backwards to start of word, capturing all letters
        while(isLetter(--row, col)) {
            builder.append(defaultBoard[row][col]);
            scoreTotal += calculateScore(defaultBoard[row][col], row, col);
        }

        if(verifyWord(builder.reverse().toString())) {
            completeTurn = true;
            if(isDoubleWord && isTripleWord) {
                addScore(scoreTotal * 3 * 2);
                isDoubleWord = false;
                isTripleWord = false;
            }
            else if(isDoubleWord) {
                addScore(scoreTotal * 2);
                isDoubleWord = false;
            }
            else if(isTripleWord) {
                addScore(scoreTotal * 3);
                isTripleWord = false;
            }
            else {
                addScore(scoreTotal);
            }
        }

        // clear builder
        builder = new StringBuilder();
        // reset values
        row = currentRow;
        col = currentCol;
        scoreTotal = 0;

        // move marker vertically to end of letters
        while(isLetter(row, col)) {
            col++;
        }

        // move vertically to start of word, capturing all letters
        while(isLetter(row, --col)) {
            builder.append(defaultBoard[row][col]);
            scoreTotal += calculateScore(defaultBoard[row][col], row, col);
        }

        if(verifyWord(builder.reverse().toString())) {
            completeTurn = true;
            if(isDoubleWord && isTripleWord) {
                addScore(scoreTotal * 3 * 2);
                isDoubleWord = false;
                isTripleWord = false;
            }
            else if(isDoubleWord) {
                addScore(scoreTotal * 2);
                isDoubleWord = false;
            }
            else if(isTripleWord) {
                addScore(scoreTotal * 3);
                isTripleWord = false;
            }
            else {
                addScore(scoreTotal);
            }
        }

        // Reset lingering variables
        isDoubleWord = false;
        isTripleWord = false;
        isFirstPlay = false;
        currentRow = -1;
        currentCol = -1;

        if(completeTurn) {
            updateState();
        }
        return completeTurn;
    }

    /**
     * Method to move to the next player
     */
    public void nextPlayer() {
        turnIndex = ++turnIndex % players.size();
    }

    /**
     * Method to refill the track of a player after a turn is made, until tiles are finished in bag
     */
    public void refillCurrentPlayer() {
        players.get(turnList.get(turnIndex)).fillRack();
    }

    /**
     * Method to reset th players 'play' before play button is clicked
     */
    public void resetCurrentPlayer() {
        players.get(turnList.get(turnIndex)).resetRack();
    }

    /**
     * Method to verify if the word played is legal
     * Checks the ScrabbleWords.txt file for legal words with a buffered reader
     * @param word
     * @return
     */
    public boolean verifyWord(String word) {
        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("ScrabbleWords.txt")))) {
            while((line = reader.readLine()) != null) {
                if(line.equalsIgnoreCase(word))
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method that checks to see it the play is legal after word placement
     * @param row
     * @param col
     * @return
     */
    public boolean isValid(int row, int col) {
        // if there is currently a letter there, it is not valid
        if(isLetter(row, col)) {
            return false;
        }

        // if it's not the first move
        if(!isFirstPlay) {
            if(isLetter(row+1, col) || isLetter(row-1, col) || isLetter(row, col+1) || isLetter(row, col-1)) {
                currentRow = row;
                currentCol = col;
                return true;
            }
        }
        // if it is the first turn
        else {
            // must start in the middle
            if(row == 7 && col == 7) {
                currentRow = row;
                currentCol = col;
                isFirstPlay = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Method that checks if the square is a character.
     * if it's out of bounds, catch exception and return false
     * @param row
     * @param col
     * @return
     */
    public boolean isLetter(int row, int col) {
        boolean returnValue;
        try {
            returnValue = Character.isLetter(defaultBoard[row][col]);
            return returnValue;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Method to reset board to defaultBoard before player tile placement
     */
    public void resetBoard() {
        if(turn == 1) {
            isFirstPlay = true;
        }
        for(int row = 0; row < defaultBoard.length; row++) {
            for (int col = 0; col < defaultBoard.length; col++) {
                defaultBoard[row][col] = newBoard[row][col];
            }
        }
    }

    /**
     * Method to update state when its next players turn
     */
    public void updateState() {
        turn++;
        for(int row = 0; row < defaultBoard.length; row++) {
            for (int col = 0; col < defaultBoard.length; col++) {
                newBoard[row][col] = defaultBoard[row][col];
            }
        }
    }

    /**
     * Instance of the Scrabble model
     * @return instance
     */
    public static ScrabbleModel getInstance() {
        if(instance == null)
            instance = new ScrabbleModel();
        return instance;
    }
}