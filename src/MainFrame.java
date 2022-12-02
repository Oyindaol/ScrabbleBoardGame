/**
 * Main class that runs the Scrabble game
 * Initializes the start screen, Asking for the player names
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version December 5, 2022
 *
 */
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu OptionsMenu;
    private JMenuItem Load, Save;

    public MainFrame() {
        super("ScrabbleModel Game - Group 17");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScrabbleStart scrabbleStart = new ScrabbleStart();
        super.setLayout(new BorderLayout());

        //Initializes the Frame for the Scrabble Game
        super.getContentPane().removeAll();
        super.add(scrabbleStart);
        super.revalidate();
        super.repaint();
        super.setSize(new Dimension(800, 450));
        super.setMinimumSize(super.getSize());
        super.setResizable(false);

        //Menu Bar
        menuBar = new JMenuBar();
        super.setJMenuBar(menuBar);

        //Menu
        OptionsMenu = new JMenu("Options");
        OptionsMenu.setEnabled(true);

        //Menu Items
        Load = new JMenuItem("Load Game");
        //Load.addActionListener();
        Load.setEnabled(true);

        Save = new JMenuItem("Save Game");
        //Save.addActionListener();
        Save.setEnabled(true);

        //Adding Menus to the Menu Bar
        menuBar.add(OptionsMenu);

        //Adding Menu Items to the Menus
        OptionsMenu.add(Load);
        OptionsMenu.add(Save);

        super.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}