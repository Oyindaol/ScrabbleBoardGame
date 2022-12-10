/**
 * ScrabbleBoard class to initialize the boards of the game.
 * Used by the Model.
 *
 * @version December 9th, 2022.
 */

public class ScrabbleBoard {
    protected final char[][] defaultBoard;
    protected final char[][] newBoard;
    protected final char[][] currentBoard;
    protected char[][] alternateBoard1;
    protected char[][] alternateBoard2;

    public ScrabbleBoard(){
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

        alternateBoard1 = AlternateBoard1();
        alternateBoard2 = AlternateBoard2();
    }

    public char[][] AlternateBoard1(){
        return alternateBoard1 = new char[][]{
                {0, 0, 0, 2, 0, 0, 5, 0, 0, 0, 0, 2, 0, 0, 5},
                {0, 3, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 4, 0},
                {2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 4, 0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 2},
                {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0},
                {2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0},
                {0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 0, 2, 0, 0, 5},
                {2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0},
                {0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0},
                {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 2},
                {2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 3, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 4, 0},
                {0, 0, 0, 2, 0, 0, 5, 0, 0, 0, 0, 2, 0, 0, 5}
        };
    }

    public char[][] AlternateBoard2(){
        return alternateBoard2 = new char[][]{
                {0, 0, 0, 2, 0, 0, 5, 5, 5, 0, 0, 2, 0, 0, 0},
                {0, 3, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 3, 0},
                {2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 0, 0, 0, 2},
                {0, 0, 0, 4, 0, 0, 2, 0, 2, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0},
                {2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2},
                {0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0},
                {2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2},
                {0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0},
                {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 4, 0, 0, 2, 0, 2, 0, 0, 4, 0, 0, 0},
                {2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 0, 0, 0, 2},
                {0, 3, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 3, 0},
                {0, 0, 0, 2, 0, 0, 5, 5, 5, 0, 0, 2, 0, 0, 0}
        };
    }
}
