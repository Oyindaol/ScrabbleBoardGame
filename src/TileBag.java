/**
 * Tile Bag class is used to distribute tiles for the game and map the values to the tiles.
 * Used by the Model.
 *
 * @version December 9th, 2022.
 */

import java.util.HashMap;
import java.util.Map;

public class TileBag {
    protected final char[] tileBag;
    protected final Map<Character, Integer> tiles;

    public TileBag(){
        //Characters are removed and placed into players rack
        //keeps track of the character distribution in the game
        tileBag = new char[] {'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D',
                'D','D','E','E','E','E','E','E','E','E','E','E','E','E','F',
                'F','G','G','G','H','H','I','I','I','I','I','I','I','I','I',
                'J','K','L','L','L','L','M','M','N','N','N','N','N','N','O',
                'O','O','O','O','O','O','O','P','P','Q','R','R','R','R','R',
                'R','S','S','S','S','T','T','T','T','T','T','U','U','U','U',
                'V','V','W','W','X','Y','Y','Z',' ',' '};


        //Mapping each character to their points
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
        tiles.put(' ', 0);
    }
}
