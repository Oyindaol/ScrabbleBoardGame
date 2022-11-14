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
        int actualScore = model.calculateScore(model.getSquare(7,7), 7,7);
        actualScore += model.calculateScore(model.getSquare(7,8), 7,8);
        actualScore += model.calculateScore(model.getSquare(7,9), 7,9);
        actualScore += model.calculateScore(model.getSquare(7,10), 7,10);
        int score = ScrabbleModel.getInstance().calculateScore('C', 7, 7);
        score += ScrabbleModel.getInstance().calculateScore('U', 7, 8);
        score += ScrabbleModel.getInstance().calculateScore('T',7, 9);
        score += ScrabbleModel.getInstance().calculateScore('E', 7, 10);
        assertEquals(score, actualScore);
    }

    @Test
    public void isValid() {
    }
}
