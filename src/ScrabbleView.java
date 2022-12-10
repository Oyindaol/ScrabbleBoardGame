/**
 * Updates the Scrabble view
 *
 * @author Oluwatomisin Ajayi (101189490)
 * @version December 9th, 2022.
 */
public interface ScrabbleView {

    void handlePlay();

    void handleClear();

    void handlePass();

    void handleEndGame();

    void handleUndo();

    void handleRedo();
}
