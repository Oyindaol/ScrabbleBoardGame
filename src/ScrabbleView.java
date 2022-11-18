/**
 * Updates the Scrabble view
 *
 * @author Oluwatomisin Ajayi (101189490)
 * @version November 13, 2022
 */
public interface ScrabbleView {
    void update(ScrabbleModel model, ScrabbleBoardFrame board, int row, int col, boolean turn);
}
