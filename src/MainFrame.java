/**
 * Main class that runs the Scrabble game
 * Initializes the start screen, Asking for the player names
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version November 13, 2022
 *
 */
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("ScrabbleModel Game - Group 17");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScrabbleStart scrabbleStart = new ScrabbleStart();
        super.setLayout(new BorderLayout());

        //Start screen of the game to add the players
        super.getContentPane().removeAll();
        super.add(scrabbleStart);
        super.revalidate();
        super.repaint();
        super.setSize(new Dimension(800, 450));
        super.setMinimumSize(super.getSize());
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        //MainFrame frame =
        new MainFrame();
    }
}