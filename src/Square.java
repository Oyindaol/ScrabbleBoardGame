/**
 * Square class that holds information of each square on the ScrabbleBoard
 * @author Oyindamola Taiwo-Olupeka 101155729
 *
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

    public int getDoubleLetterSquare(Tile tile){ //should pass in a reference to the tile score for Tile class
        return tile.score * 2;
    } // score var can be changed

    public int getTripleLetterSquare(Tile tile){//should pass in a reference to the tile score
        return tile.score * 3;
    }

    public int getDoubleWordSquare() { // needs work
        return doubleWordSquare * 2;
    }

    public int getTripleWordSquare(){ //needs work
        return tripleWordSquare * 3;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public void setEmpty(Boolean value){
        this.empty = value;
    }

}
