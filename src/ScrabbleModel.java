/**
 * Class ScrabbleModel is the Game class in Scrabble.
 * For this milestone (Milestone 2), the game would be played via mouse.
 * To play game, players would have to select the tiles and place them on the boards
 * The first player must start on the centre square.
 * The game keeps track of the information of 2-4 players. Determines the winner from the highest
 * score between the players.
 *
 * @author Oluwatomisin Ajayi (101189490)
 * @version December 9th, 2022.
 *
 */
import java.io.*;
import java.util.*;


public class ScrabbleModel implements Serializable {
    private static ScrabbleModel instance = null;
    private final ScrabbleBoard scrabbleBoard;

    private final TileBag newTileBag;
    private static ArrayList<Character> characters;

    private final ArrayList<Player> players;
    private final ArrayList<Integer> turnList;

    private AIPlayer aiPlayer;

    private int turnIndex, currentRow, currentCol;
    private boolean isFirstPlay, isDoubleWord, isTripleWord;
    private int turn;

    private List<ScrabbleView> views;

    protected Stack<Character> undoStack;
    protected Stack redoStack;

    protected int row;
    protected int col;
    private List rowColListUndo, rowColListRedo;
    private String lastIndexUndo, lastIndexRedo;
    protected char rackTile;

    /**
     * ScrabbleModel constructor
     * Initializes a the default scrabble board and the bonuses
     * Adds the tiles into the tile bag for them to be later distributed to the 2-4 players
     * Manages the views
     */
    public ScrabbleModel() {
        scrabbleBoard = new ScrabbleBoard();

        newTileBag = new TileBag();

        characters = new ArrayList<>();
        for(char i : newTileBag.tileBag) {
            characters.add(i);
        }

        //Arraylist of the players in the game
        players = new ArrayList<>();

        aiPlayer = new AIPlayer("ScrabbleAI");

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

        undoStack = new Stack<>();
        redoStack = new Stack<>();
        rowColListUndo = new ArrayList<>();
        rowColListRedo = new ArrayList<>();
    }

    /**
     * Adds a view to the Scrabble game
     *
     * @param v
     */
    public void addScrabbleView(ScrabbleView v){
        views.add(v);
    }

    /**
     * Removes a view from the Scrabble game
     * @param v
     */
    public void removeScrabbleView(ScrabbleView v){
        views.remove(v);
    }

    /**
     * Returns the Scrabble views
     *
     * @return views
     */
    public List<ScrabbleView> getScrabbleViews(){
        return views;
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
        for(int i = 1; i < players.size(); i++) {
            if(players.get(i).getPlayerScore() >= players.get(index).getPlayerScore()) {
                index = i;
            }
        }
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getPlayerScore() == players.get(index).getPlayerScore()) {
                winningPlayer.add(i);
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
        return scrabbleBoard.defaultBoard[row][col];
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

        for(int i = 0; i < list.size(); i++) {
            if(Character.toLowerCase(list.get(i)) == (Character.toLowerCase(c))) {
                list.remove(i);
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
        scrabbleBoard.defaultBoard[row][col] = letter;
        undoStack.push(letter);
        rowColListUndo.add(row + "&" + col);
    }

    /**
     * Method to calculate score of tile placed on bonus squares
     * @param c
     * @param row
     * @param col
     * @return
     */
    public int calculateScore(char c, int row, int col) {
        switch (scrabbleBoard.currentBoard[row][col]) {
            case 1: //start of the game and also double word bonus
                isDoubleWord = true;
                return newTileBag.tiles.get(Character.toUpperCase(c));
            case 2:
                return newTileBag.tiles.get(Character.toUpperCase(c)) * 2;
            case 3:
                return newTileBag.tiles.get(Character.toUpperCase(c)) * 3;
            case 4:
                isDoubleWord = true;
                return newTileBag.tiles.get(Character.toUpperCase(c));
            case 5:
                isTripleWord = true;
                return newTileBag.tiles.get(Character.toUpperCase(c));
            default:
                return newTileBag.tiles.get(Character.toUpperCase(c));
        }
    }

    /**
     * Method to add score to current player after each play
     * @param points
     */
    public void addScore(int points) {

        players.get(turnList.get(turnIndex)).playerScore += points;
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
            builder.append(scrabbleBoard.defaultBoard[row][col]);
            scoreTotal += calculateScore(scrabbleBoard.defaultBoard[row][col], row, col);
        }

        if(verifyWord(builder.reverse().toString())) {
            completeTurn = true;
            if(isDoubleWord && isTripleWord) {
                addScore(scoreTotal * 2 * 3);
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
            builder.append(scrabbleBoard.defaultBoard[row][col]);
            scoreTotal += calculateScore(scrabbleBoard.defaultBoard[row][col], row, col);
        }

        if(verifyWord(builder.reverse().toString())) {
            completeTurn = true;
            if(isDoubleWord && isTripleWord) {
                addScore(scoreTotal * 2 * 3);
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
            returnValue = Character.isLetter(scrabbleBoard.defaultBoard[row][col]);
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
        for(int row = 0; row < scrabbleBoard.defaultBoard.length; row++) {
            for (int col = 0; col < scrabbleBoard.defaultBoard.length; col++) {
                scrabbleBoard.defaultBoard[row][col] = scrabbleBoard.newBoard[row][col];
            }
        }
    }

    /**
     * Method to update state when its next players turn
     */
    public void updateState() {
        turn++;
        for(int row = 0; row < scrabbleBoard.defaultBoard.length; row++) {
            for (int col = 0; col < scrabbleBoard.defaultBoard.length; col++) {
                scrabbleBoard.newBoard[row][col] = scrabbleBoard.defaultBoard[row][col];
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

    /**
     * Method to undo the player's last move
     */
    public void undo(){
        lastIndexUndo = (String) rowColListUndo.get(rowColListUndo.size() - 1);
        String[] rowCol = lastIndexUndo.split("&");
        this.row = Integer.parseInt(rowCol[0]);
        this.col = Integer.parseInt(rowCol[1]);
        clearTile(this.row, this.col);
        char temp = undoStack.pop();
        redoStack.push(temp);
        rackTile = temp;
        rowColListRedo.add(this.row + "&" + this.col);
        rowColListUndo.remove(rowColListUndo.size() - 1);
    }

    /**
     * Method to clear a tile at a specific board location
     * @param row
     * @param col
     */
    private void clearTile(int row, int col) {
        scrabbleBoard.defaultBoard[row][col] = scrabbleBoard.newBoard[row][col];
    }

    /**
     * Method to redo the player's last move
     */
    public void redo(){
        lastIndexRedo = (String) rowColListRedo.get(rowColListRedo.size() - 1);
        String[] rowCol = lastIndexRedo.split("&");
        this.row = Integer.parseInt(rowCol[0]);
        this.col = Integer.parseInt(rowCol[1]);
        playTile((Character) redoStack.lastElement(), this.row, this.col);
        redoStack.pop();
        rowColListRedo.remove(rowColListRedo.size() - 1);
    }

    /**
     * Saves the current game
     *
     * @param o
     * @throws IOException
     */
    public static void saveGame(Object o) throws IOException {

        FileOutputStream fos = new FileOutputStream("saveLoadFile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
//        oosBoard.flush();
//        oosBoard.close();
        System.out.println("ScrabbleGame Saved!\n");

    }

    /**
     * Loads the previously saved game
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ScrabbleModel loadGame() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("saveLoadFile.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ScrabbleModel model = (ScrabbleModel) ois.readObject();
        return model;
        //ois.close();
        //System.out.println("\nScrabbleGame Loaded!");

    }
}