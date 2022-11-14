/**
 * Updates the Scrabble view
 *
 * @author Oluwatomisin Ajayi (101189490)
 * @version November 13, 2022
 */
public interface ScrabbleView {
    //void update(ScrabbleModel e);
    void update(ScrabbleModel model, ScrabbleBoardFrame board, int i, int j, boolean turn);
}
