/**
 * Main class that runs the Scrabble game
 * Initializes the start screen, Asking for the player names
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version November 13, 2022
 *
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu OptionsMenu;
    private JMenuItem save, load;
    private SaveLoad saveLoad;

    public MainFrame() {
        super("ScrabbleModel Game - Group 17");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScrabbleStart scrabbleStart = new ScrabbleStart();
        saveLoad = new SaveLoad();

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
        save = new JMenuItem("Save Game");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveLoad.saveGame();
                JOptionPane.showMessageDialog(save,"SCRABBLE GAME SAVED!", "SAVE GAME", JOptionPane.OK_OPTION);

            }
        });
        save.setEnabled(true);

        load = new JMenuItem("Load Game");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int loadDialogButton = JOptionPane.YES_NO_OPTION;
                JOptionPane.showConfirmDialog(load,"Are you sure you want to load the previous game?\nThis will override the current game.","LOAD GAME",loadDialogButton);
                if(loadDialogButton == JOptionPane.YES_OPTION){
                    saveLoad.loadGame();
                    JOptionPane.showMessageDialog(load,"SCRABBLE GAME LOADED!", "LOAD GAME", JOptionPane.OK_OPTION);

                }
                else if (loadDialogButton == JOptionPane.NO_OPTION){
                    remove(loadDialogButton);
                }
            }
        });
        load.setEnabled(true);


        //Adding Menus to the Menu Bar
        menuBar.add(OptionsMenu);

        //Adding Menu Items to the Menus
        OptionsMenu.add(save);
        OptionsMenu.add(load);


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