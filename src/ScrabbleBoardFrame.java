/**
 * Scrabble Board Frame class, which displays the board GUI.
 * Holds a ScrabbleModel object.
 * Updates the GUI as each play is made.
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version November 13, 2022
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ScrabbleBoardFrame extends JPanel {
    private final ScrabbleModel scrabbleModel = ScrabbleModel.getInstance();

    // made these member data because they need to be directly accessible
    private final BoardPanel boardPanel;
    private final RackPanel rackPanel;
    private final ButtonPanel buttonPanel;
    private final ScorePanel scorePanel;

    ScrabbleController sc = new ScrabbleController(scrabbleModel);


    public ScrabbleBoardFrame() {
        super();
        scorePanel = new ScorePanel();
        buttonPanel = new ButtonPanel();
        boardPanel = new BoardPanel();
        rackPanel = new RackPanel();

        super.setLayout(new BorderLayout());
        super.add(boardPanel, BorderLayout.CENTER);
        super.add(buttonPanel, BorderLayout.NORTH);
        super.add(rackPanel, BorderLayout.SOUTH);
        super.add(scorePanel, BorderLayout.WEST);

        // initialize players(name, tiles, score, turn order)
        scrabbleModel.setUpPlayers();
        rackPanel.setRackPanel();
        buttonPanel.showCurrentPlayer();
        super.setBackground(Color.GRAY);
    }

    /**
     * Nested class for the ScorePanel
     */
    public class ScorePanel extends JPanel {

        private JLabel scores;
        private JLabel infoBox;

        public ScorePanel() {
            super();

            // Initializing infoBox and scrabbleBox Labels
            infoBox = new JLabel("<html>Character points<br/><pre>A-1  B-3  C-3"
                    + "<br/>D-2  E-1  F-1<br/>G-2  H-4  I-1<br/>J-8  K-5  L-1"
                    + "<br/>M-3  N-1  0-1<br/>P-3  Q-10 R-1<br/>S-1  T-1  U-1"
                    + "<br/>V-4  W-4  X-8<br/>Y-4  Z-10<br/>BLANK-0</pre></html>", SwingConstants.CENTER);
            infoBox.setFont(new Font("TimesRoman", Font.PLAIN, 12));

            // Formatting Panel
            super.setLayout(new BorderLayout());
            super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            super.add(infoBox, BorderLayout.SOUTH);
            super.setBackground(Color.PINK);

            updateScores();
        }

        /**
         * update the players score after each play
         */
        public void updateScores() {
            removeAll();
            super.add(infoBox, BorderLayout.SOUTH);
            String info = "<html><b>Scores</b><br/><pre>";
            for(int row = 0; row < scrabbleModel.getPlayerCount(); row++) {
                info += scrabbleModel.getPlayerName(row) + ": " + scrabbleModel.getPlayerScore(row)+"<br/>";
            }
            info += "</pre></html>";
            scores = new JLabel(info, SwingConstants.CENTER);
            scores.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            super.add(scores, BorderLayout.NORTH);
            repaint();
        }
    }

    /**
     * Nested class for the ButtonPanel
     */
    public class ButtonPanel extends JPanel {

        private JLabel playerName;

        public ButtonPanel() {
            super();
            JPanel buttonSection = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonSection.setOpaque(false);

            // Initializing clear Button
            JButton clear = new JButton("clear");
            clear.setFocusPainted(false);
            clear.setContentAreaFilled(false);
            clear.setOpaque(true);
            clear.setBackground(Color.RED);
            clear.addActionListener(sc);

            // Initializing pass Button
            JButton pass = new JButton("pass");
            pass.setFocusPainted(false);
            pass.setContentAreaFilled(false);
            pass.setOpaque(true);
            pass.setBackground(Color.RED);
            pass.addActionListener(sc);

            // Initializing play Button
            JButton play = new JButton("play");
            play.setFocusPainted(false);
            play.setContentAreaFilled(false);
            play.setOpaque(true);
            play.setBackground(Color.RED);
            play.addActionListener(sc);

            // Initializing endGame Button
            JButton endGame = new JButton("End Game");
            endGame.setFocusPainted(false);
            endGame.setContentAreaFilled(false);
            endGame.setOpaque(true);
            endGame.setBackground(Color.RED);
            endGame.addActionListener(sc);

            // Adding Buttons to Button Section
            buttonSection.add(clear);
            buttonSection.add(Box.createHorizontalStrut(5));
            buttonSection.add(play);
            buttonSection.add(Box.createHorizontalStrut(40));
            buttonSection.add(pass);
            buttonSection.add(Box.createHorizontalStrut(5));
            buttonSection.add(endGame);


            // Formatting Panel
            super.setLayout(new BorderLayout());
            super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            super.add(buttonSection, BorderLayout.LINE_END);
            super.setBackground(Color.PINK);

        }

        /**
         * Method to show current player during their turn
         */
        public void showCurrentPlayer() {
            if(playerName == null) {
                playerName = new JLabel(scrabbleModel.getCurrentPlayerName() + "'s Turn" , SwingConstants.CENTER);
                playerName.setFont(new Font("TimesRoman", Font.BOLD, 30));
                super.add(playerName, BorderLayout.LINE_START);
            }
            else playerName.setText(scrabbleModel.getCurrentPlayerName() + "'s Turn" );
            repaint();
        }
    }

    /**
     * Nested class for the BoardPanel
     */
    public class BoardPanel extends JPanel {

        private JLabel[][] board;

        public BoardPanel() {
            super();
            board = new JLabel[15][15];
            buildPanel();
            super.setBackground(Color.WHITE);
        }


        /**
         * resets the board
         */
        public void reset() {
            for(int row = 0; row < 15; row++) {
                for(int col = 0; col < 15; col++) {
                    switch (scrabbleModel.getSquare(row, col)) {
                        case 1:
                            board[row][col].setText("Double Word Score");
                            board[row][col].setFont(new Font("TimesRoman", Font.BOLD, 10));
                            break;
                        case 2:
                            board[row][col].setText("Double Letter Score");
                            board[row][col].setFont(new Font("TimesRoman", Font.BOLD, 10));
                            break;
                        case 3:
                            board[row][col].setText("Triple Letter Score");
                            board[row][col].setFont(new Font("TimesRoman", Font.BOLD, 10));
                            break;
                        case 4:
                            board[row][col].setText("Double Word Score");
                            board[row][col].setFont(new Font("TimesRoman", Font.BOLD, 10));
                            break;
                        case 5:
                            board[row][col].setText("Triple Word Score");
                            board[row][col].setFont(new Font("TimesRoman", Font.BOLD, 10));
                            break;
                        default:
                            board[row][col].setText(Character.toString(scrabbleModel.getSquare(row, col)));
                            break;
                    }
                    board[row][col].setText("<html><div style='text-align: center;'>" + board[row][col].getText() + "</div></html>");
                }
            }
        }

        /**
         * Builds the board panel with Grid layout
         */
        private void buildPanel() {
            // Formatting Panel and Generating Grid
            super.setLayout(new GridLayout(15, 15));
            for(int row = 0; row < 15; row++) {
                for(int col = 0; col < 15; col++) {
                    final int index1 = row, index2 = col;
                    board[row][col] = new JLabel();
                    board[row][col].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(rackPanel.getCurrentTile() != null) {
                                // Functionality for Blank Pieces
                                if(rackPanel.getCurrentTile().getText().charAt(0) == '*') {
                                    JDialog dialog = new JDialog();
                                    JPanel dialogPanel = new JPanel();
                                    dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

                                    JLabel info = new JLabel("<html>Select the letter you would like to replace the "
                                            + "blank piece with, then press ENTER.</html>");
                                    info.setFont(new Font("TimesRoman", Font.PLAIN, 12));
                                    info.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                    info.setSize(100, 50);
                                    info.setBorder(new EmptyBorder(10, 40, 10, 40));

                                    JTextField letter = new JTextField("");
                                    letter.setSize(new Dimension(100, 50));
                                    letter.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dialog.setVisible(false);
                                            // Add the letter to the grid if it is valid
                                            if (scrabbleModel.isValid(index1, index2)) {
                                                rackPanel.getCurrentTile().setText(letter.getText().substring(0, 1).toUpperCase());
                                                board[index1][index2].setText(rackPanel.getCurrentTile().getText());
                                                board[index1][index2].setFont(new Font("TimesRoman", Font.BOLD, 34));
                                                scrabbleModel.removePlayerTile(rackPanel.getCurrentTile().getText().charAt(0));
                                                rackPanel.getCurrentTile().removeAll();
                                                rackPanel.resetCurrentTile();
                                                scrabbleModel.playTile(board[index1][index2].getText().charAt(0), index1, index2);
                                            }
                                        }
                                    });
                                    dialogPanel.add(info);
                                    dialogPanel.add(letter);
                                    dialog.add(dialogPanel);
                                    dialog.setSize(300, 150);
                                    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    dialog.setUndecorated(true);
                                    dialog.setLocationRelativeTo(null);
                                    dialog.setVisible(true);
                                }
                                // If it isn't a blank piece, and isn't null
                                else {
                                    // Add the letter to the grid if it is valid
                                    if(scrabbleModel.isValid(index1, index2)) {
                                        board[index1][index2].setText(rackPanel.getCurrentTile().getText());
                                        board[index1][index2].setFont(new Font("TimesRoman", Font.BOLD, 34));
                                        rackPanel.getCurrentTile().setVisible(false);
                                        scrabbleModel.removePlayerTile(rackPanel.getCurrentTile().getText().charAt(0));
                                        rackPanel.getCurrentTile().removeAll();
                                        rackPanel.resetCurrentTile();
                                        scrabbleModel.playTile(board[index1][index2].getText().charAt(0), index1, index2);
                                    }
                                }
                            }
                        }
                    });

                    switch (scrabbleModel.getSquare(row, col)) {
                        case 1:
                            board[row][col].setText("Double Word Score");
                            board[row][col].setBackground(Color.RED);
                            break;
                        case 2:
                            board[row][col].setText("Double Letter Score");
                            board[row][col].setBackground(Color.BLUE);
                            break;
                        case 3:
                            board[row][col].setText("Triple Letter Score");
                            board[row][col].setBackground(Color.MAGENTA);
                            break;
                        case 4:
                            board[row][col].setText("Double Word Score");
                            board[row][col].setBackground(Color.CYAN);
                            break;
                        case 5:
                            board[row][col].setText("Triple Word Score");
                            board[row][col].setBackground(Color.ORANGE);
                            break;
                        default:
                            board[row][col].setText(" ");
                            board[row][col].setBackground(Color.lightGray);
                            break;
                    }

                    board[row][col].setOpaque(true);
                    board[row][col].setBorder(new MatteBorder(1, 1, 0, 0, Color.BLACK));
                    board[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                    board[row][col].setVerticalAlignment(SwingConstants.CENTER);
                    board[row][col].setText("<html><div style='text-align: center;'>" + board[row][col].getText() + "</div></html>");
                    board[row][col].setFont(new Font("TimesRoman", Font.BOLD, 10));
                    this.add(board[row][col]);
                }
            }
        }
    }

    /**
     * Nested class for the RackPanel
     */
    public class RackPanel extends JPanel{
        private JLabel currentTile;
        private ArrayList<Character> rack;


        /**
         * creates panels that represent the rack and tiles
         */
        public void setRackPanel(){
            removeAll();
            rack = scrabbleModel.getCurrentPlayerRack();
            super.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
            for(Character i : rack) {
                JLabel tile = new JLabel(i.toString());
                tile.setOpaque(true);
                tile.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
                tile.setFont(new Font("TimesRoman", Font.BOLD, 34));
                tile.setBackground(Color.YELLOW);
                tile.setPreferredSize(new Dimension(50,50));
                tile.setHorizontalAlignment(SwingConstants.CENTER);
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(currentTile != null)
                            currentTile.setBackground(Color.YELLOW);

                        tile.setBackground(Color.GREEN);
                        currentTile = tile;
                    }
                });
                super.add(tile);
            }
            super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            super.setBackground(Color.PINK);
        }

        /**
         * Return the the tile selected (Jlabel)
         * @return currentTile
         */
        public JLabel getCurrentTile() {
            return currentTile;
        }

        /**
         * reset selected tile (used for the clear button)
         */
        public void resetCurrentTile() {
            currentTile = null;
        }
    }
}
