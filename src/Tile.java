/**
 * The Tile class represents a single tile in the scrabble game which holds
 * one specific character.
 *
 * @author Edidiong Okon (101204818)
 * @version October 25, 2022
 */

public class Tile {
    public int score;
    private String character;

    /**
     * Constructor for objects of class Tile.
     * Creates a new tile with a specific character and value (score).
     * @param character
     * @param score
     */
    public Tile(String character, int score){
        this.score = score;
        this.character = character;

//        for (int i = 0; i < tileLetters.length(); i++){
//            this.tileLetters.add(tileLetters.charAt(i));
//        }
    }
    /**
     * Returns a tile's character.
     * @return the character held by a tile
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Returns the value of a tile.
     * @return the score/value of a tile
     */
    public int getScore() {
        return score;
    }
}
