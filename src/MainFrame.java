/**
 * Main class that runs the Scrabble game
 * Initializes the start screen, Asking for the player names
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version December 9th, 2022.
 *
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu OptionsMenu;
    private JMenuItem saveGame, loadGame;
    private ScrabbleModel scrabbleModel;

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
        saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int saveButton = JOptionPane.showConfirmDialog(saveGame,"Are You Sure You Want To Save This Game?","SAVE GAME",JOptionPane.YES_NO_OPTION);
                if (saveButton == JOptionPane.YES_OPTION){
                    try {
                        ScrabbleModel.saveGame(scrabbleModel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{

                }
            }
        });
        saveGame.setEnabled(true);

        loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int loadButton = JOptionPane.showConfirmDialog(loadGame, "Are You Sure You Want To Load The Saved Game?\nThe current game will be lost", "LOAD GAME", JOptionPane.YES_NO_OPTION);
                if (loadButton == JOptionPane.YES_OPTION) {
                    try {
                        ScrabbleModel.loadGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        loadGame.setEnabled(true);


        //Adding Menus to the Menu Bar
        menuBar.add(OptionsMenu);

        //Adding Menu Items to the Menus
        OptionsMenu.add(saveGame);
        OptionsMenu.add(loadGame);


        JButton clear = new JButton("clear");
        clear.setFocusPainted(false);
        clear.setContentAreaFilled(false);
        clear.setOpaque(true);
        clear.setBackground(Color.RED);


        super.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}