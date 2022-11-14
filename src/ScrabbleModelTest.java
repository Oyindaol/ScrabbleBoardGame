import org.junit.Test;

import static org.junit.Assert.*;

public class ScrabbleModelTest {
    ScrabbleModel model;

    @Test
    public void testScoreCalculated() {
        model = new ScrabbleModel();
        model.addPlayer("Tomi");
        model.playTile('C', 7, 7);
        model.playTile('U', 7, 8);
        model.playTile('T', 7, 9);
        model.playTile('E', 7, 10);
        model.endTurn();
        int score = ScrabbleModel.getInstance().calculateScore('C', 7, 7);
        score += ScrabbleModel.getInstance().calculateScore('U', 7, 8);
        score += ScrabbleModel.getInstance().calculateScore('T',7, 9);
        score += ScrabbleModel.getInstance().calculateScore('E', 7, 10);
        assertEquals(score, model.getPlayerScore(0));
    }

    @Test
    public void isValid() {
    }
}