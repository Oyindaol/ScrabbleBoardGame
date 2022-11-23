import org.junit.Test;

import static org.junit.Assert.*;

public class ScrabbleModelTest {

    @Test
    public void getPlayerName() {
        ScrabbleModel model = new ScrabbleModel();
//        Player Player1 = new Player("Test");
//        Player Player2 = new Player("Zein");
//        Player Player3 = new Player("Nat");

        model.addPlayer("Test");
        model.addPlayer("Zein");
        model.addPlayer("Nat");

        assertEquals(model.getPlayerName(1), "Zein");
    }

    @Test
    public void addPlayer() {
        ScrabbleModel model = new ScrabbleModel();
        Player Player1 = new Player("Test");
        //Empty List
        assertEquals(model.getPlayerCount(), 0);

        //Adding one player
        model.addPlayer(String.valueOf(Player1));
        assertEquals(model.getPlayerCount(), 1);
    }

    @Test
    public void calculateScore() {
        int score = 0;

        ScrabbleModel model = new ScrabbleModel();

        //Just letter score test
        score += model.calculateScore('W',4,6);
        score += model.calculateScore('E',4,7);
        score += model.calculateScore('E',4,8);
        score += model.calculateScore('D',4,9);
        assertEquals(score,8);
    }
    @Test
    public void calculateScore1() {
        int score = 0;

        ScrabbleModel model = new ScrabbleModel();
        //Double word score
//        score += model.calculateScore('L',7,7);
//        score += model.calculateScore('I',8,7);
//        score += model.calculateScore('E',9,7);
//        assertEquals(score,6 );

    }
    @Test
    public void calculateScore2() {
        int score = 0;

        ScrabbleModel model = new ScrabbleModel();

        //Double  letter score test
        score += model.calculateScore('H',3,7);
        score += model.calculateScore('A',3,8);
        score += model.calculateScore('T',3,9);
        assertEquals(score,10);
    }
    @Test
    public void calculateScore3() {
        int score = 0;

        ScrabbleModel model = new ScrabbleModel();

        //Triple word score test
//        score += model.calculateScore('W',0,7);
//        score += model.calculateScore('E',1,7);
//        score += model.calculateScore('D',2,7);
//        //score += model.calculateScore('D',3,7);
//        assertEquals(score,21);

    }

    @Test
    public void calculateScore4() {
        int score = 0;

        ScrabbleModel model = new ScrabbleModel();

        //Triple letter score test
        score += model.calculateScore('T',9,5);
        score += model.calculateScore('O',9,6);
        score += model.calculateScore('E',9,7);
        assertEquals(score,5);
    }

    @Test
    public void verifyWord() {
        ScrabbleModel model = new ScrabbleModel();
        String word = "HART";
        String word1 = "jhbch";
        assertTrue(model.verifyWord(word));
        assertFalse(model.verifyWord(word1));
    }
}
