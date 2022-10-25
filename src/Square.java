/**
 * Square class that holds information of each square on the ScrabbleBoard
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version October 25, 2022
 */
public class Square {
    private Tile tile; //should hold the character on the tile and the score.
    private int rowNumber;
    private int columnNumber;
    private int doubleLetterSquare;
    private int tripleLetterSquare;
    private int doubleWordSquare;
    private int tripleWordSquare;
    private boolean empty;


    /**
     * Square class constructor method to initialize the Square row and column.
     *
     * @param rowNumber
     * @param columnNumber
     */
    public Square(int rowNumber, int columnNumber){
        tile = null;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.empty = true;

    }

    /**
     * Accessor method to return row number.
     *
     * @return rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * Accessor method to return column number.
     *
     * @return columnNumber
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * Accessor method to return the tile.
     *
     * @return tile
     */
    public Tile getTile(){
        return this.tile;
    }

    /**
     * Boolean method to check if the square is empty.
     *
     * @return True, if the square is empty
     * @return False, if the square has a tile on it
     */
    public boolean isEmpty(){
        return this.empty;
    }

    /**
     * playTile method to place a tile on the square.
     *
     * @param tile
     */
    public void playTile(Tile tile){
        if(isEmpty()){
            this.tile = tile;
        }
    }

    /**
     * Method to remove tile from a square.
     */
    public void removeTile(){
        if(!isEmpty()) {
            this.tile = null;
        }
    }

    /**
     * A mutator method to set the Tile.
     *
     * @param tile
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * A mutator method to make the square empty.
     *
     * @param value
     */
    public void setEmpty(Boolean value){
        this.empty = value;
    }

    /**
     * Method that returns the updated score of a tile placed on a double letter square.
     *
     * @param tile
     * @return tile.score * 2
     */
    public int getDoubleLetterSquare(Tile tile){ //should pass in a reference to the tile score for Tile class
        return tile.score * 2;
    }

    /**
     * Method that returns the updated score of a tile placed on a triple letter square.
     *
     * @param tile
     * @return tile.score * 3;
     */
    public int getTripleLetterSquare(Tile tile){//should pass in a reference to the tile score
        return tile.score * 3;
    }

    /**
     * Method that returns the updated score of a word placed on a double word square.
     * Incomplete.
     *
     * @return
     */
    public int getDoubleWordSquare() { // needs work
        return doubleWordSquare * 2;
    }

    /**
     * Method that returns the updated score of a word placed on a triple word square.
     * Incomplete.
     *
     * @return
     */
    public int getTripleWordSquare(){ //needs work
        return tripleWordSquare * 3;
    }



}
