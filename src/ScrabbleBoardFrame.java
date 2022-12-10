/**
 * This class is the Graphical User Interface(GUI) which has an instance of the ScrabbleModel class
 * and is how PLayers are meant to interact with board.
 *Contains nested classes to build the frame and its panels
 *
 * @author Oyindamola Taiwo-Olupeka (101155729)
 * @version December 9th, 2022.
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


/**
 * Main panel where all the nested panels are placed on
 */
public class ScrabbleBoardFrame extends JPanel implements ScrabbleView, Serializable {
    private final ScrabbleModel scrabbleModel = ScrabbleModel.getInstance();

    private final BoardPanel boardPanel;
    private final RackPanel rackPanel;
    private final ButtonPanel buttonPanel;
    private final ScorePanel scorePanel;

    private ScrabbleController sc;

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

        scrabbleModel.addScrabbleView(this);
        sc = new ScrabbleController(scrabbleModel);

    }

    /**
     * Nested ScorePanel class to create the score panel and the scores associated to each tile
     * Left side of the ScrabbleBoardFrame panel.
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
         * Method called whenever a turn is made, updates current player scores
         */
        public void updateScores() {
            removeAll();
            super.add(infoBox, BorderLayout.SOUTH);
            String info = "<html><b>Scores</b><br/><pre>";
            for(int i = 0; i < scrabbleModel.getPlayerCount(); i++) {
                info += scrabbleModel.getPlayerName(i) + ": " + scrabbleModel.getPlayerScore(i)+"<br/>";
            }
            info += "</pre></html>";
            scores = new JLabel(info, SwingConstants.CENTER);
            scores.setFont(new Font("Serif", Font.PLAIN, 14));
            super.add(scores, BorderLayout.NORTH);
            repaint();
        }
    }

    /**
     * Nested ButtonPanel class that the buttons required the game flow are placed
     * Also lets us know which players turn it is
     * Top of the ScrabbleBoardFrame panel.
     */
    public class ButtonPanel extends JPanel {

        private JLabel playerName;

        public ButtonPanel() {
            super();
            JPanel buttonSection = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonSection.setOpaque(false);

            //Initializing undo button
            JButton undo = new JButton("undo");
            undo.setFocusPainted(false);
            undo.setContentAreaFilled(false);
            undo.setOpaque(true);
            undo.setBackground(Color.RED);
            undo.setActionCommand("undo");
            undo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    handleUndo();
                }
            });

            //Initializing redo button
            JButton redo = new JButton("redo");
            redo.setFocusPainted(false);
            redo.setContentAreaFilled(false);
            redo.setOpaque(true);
            redo.setBackground(Color.RED);
            redo.setActionCommand("redo");
            redo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    handleRedo();
                }
            });

            // Initializing clear Button
            JButton clear = new JButton("clear");
            clear.setFocusPainted(false);
            clear.setContentAreaFilled(false);
            clear.setOpaque(true);
            clear.setBackground(Color.RED);

            clear.setActionCommand("clear");
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    handleClear();
                }
            });

            // Initializing pass Button
            JButton pass = new JButton("pass");
            pass.setFocusPainted(false);
            pass.setContentAreaFilled(false);
            pass.setOpaque(true);
            pass.setBackground(Color.RED);
            pass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handlePass();
                }
            });

            // Initializing play Button
            JButton play = new JButton("play");
            play.setFocusPainted(false);
            play.setContentAreaFilled(false);
            play.setOpaque(true);
            play.setBackground(Color.RED);
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handlePlay();
                }
            });

            // Initializing endGame Button
            JButton endGame = new JButton("End Game");
            endGame.setFocusPainted(false);
            endGame.setContentAreaFilled(false);
            endGame.setOpaque(true);
            endGame.setBackground(Color.RED);
            endGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(scrabbleModel.getTurn() > scrabbleModel.getPlayerCount()) {
                        handleEndGame();
                    }
                }
            });

            // Adding Buttons to Button Section
            buttonSection.add(undo);
            buttonSection.add(Box.createHorizontalStrut(5));
            buttonSection.add(redo);
            buttonSection.add(Box.createHorizontalStrut(40));
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
         * Method called whenever a turn is made, updates current player at top
         * should be called after players have been initialized, not in this panel's constructor
         */
        public void showCurrentPlayer() {
            if(playerName == null) {
                playerName = new JLabel(scrabbleModel.getCurrentPlayerName() + "'s Turn" , SwingConstants.CENTER);
                playerName.setFont(new Font("Serif", Font.BOLD, 30));
                super.add(playerName, BorderLayout.LINE_START);
            }
            else playerName.setText(scrabbleModel.getCurrentPlayerName() + "'s Turn" );
            repaint();
        }
    }

    /**
     * Nested BoardPanel class that contains the default grid for the game
     * Centre of the ScrabbleBoardFrame panel.
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
         * A reset method used for clearing the board
         */
        public void reset() {
            for(int row = 0; row < 15; row++) {
                for(int col = 0; col < 15; col++) {
                    switch (scrabbleModel.getSquare(row, col)) {
                        case 1:
                            board[row][col].setText("Double Word Score");
                            board[row][col].setFont(new Font("Serif", Font.BOLD, 10));
                            break;
                        case 2:
                            board[row][col].setText("Double Letter Score");
                            board[row][col].setFont(new Font("Serif", Font.BOLD, 10));
                            break;
                        case 3:
                            board[row][col].setText("Triple Letter Score");
                            board[row][col].setFont(new Font("Serif", Font.BOLD, 10));
                            break;
                        case 4:
                            board[row][col].setText("Double Word Score");
                            board[row][col].setFont(new Font("Serif", Font.BOLD, 10));
                            break;
                        case 5:
                            board[row][col].setText("Triple Word Score");
                            board[row][col].setFont(new Font("Serif", Font.BOLD, 10));
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
         * Method to reset the tiles
         */
        public void resetTile(){
            switch (scrabbleModel.getSquare(scrabbleModel.row, scrabbleModel.col)) {
                case 1:
                    board[scrabbleModel.row][scrabbleModel.col].setText("Double Word Score");
                    board[scrabbleModel.row][scrabbleModel.col].setFont(new Font("Serif", Font.BOLD, 10));
                    break;
                case 2:
                    board[scrabbleModel.row][scrabbleModel.col].setText("Double Letter Score");
                    board[scrabbleModel.row][scrabbleModel.col].setFont(new Font("Serif", Font.BOLD, 10));
                    break;
                case 3:
                    board[scrabbleModel.row][scrabbleModel.col].setText("Triple Letter Score");
                    board[scrabbleModel.row][scrabbleModel.col].setFont(new Font("Serif", Font.BOLD, 10));
                    break;
                case 4:
                    board[scrabbleModel.row][scrabbleModel.col].setText("Double Word Score");
                    board[scrabbleModel.row][scrabbleModel.col].setFont(new Font("Serif", Font.BOLD, 10));
                    break;
                case 5:
                    board[scrabbleModel.row][scrabbleModel.col].setText("Triple Word Score");
                    board[scrabbleModel.row][scrabbleModel.col].setFont(new Font("Serif", Font.BOLD, 10));
                    break;
                default:
                    board[scrabbleModel.row][scrabbleModel.col].setText(Character.toString(scrabbleModel.getSquare(scrabbleModel.row, scrabbleModel.col)));
                    break;
            }
            board[scrabbleModel.row][scrabbleModel.col].setText("<html><div style='text-align: center;'>" + board[scrabbleModel.row][scrabbleModel.col].getText() + "</div></html>");
        }

        /**
         * Method to build the grid panel
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
                            if(rackPanel.getCurrentPiece() != null) {
                                // Functionality for Blank Pieces
                                if(rackPanel.getCurrentPiece().getText().charAt(0) == ' ') {
                                    JDialog dialog = new JDialog();
                                    JPanel dialogPanel = new JPanel();
                                    dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

                                    JLabel info = new JLabel("<html>Select the letter you would like to replace the "
                                            + "blank tile with, then press ENTER.</html>");
                                    info.setFont(new Font("Helvetica", Font.PLAIN, 12));
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
                                                rackPanel.getCurrentPiece().setText(letter.getText().substring(0, 1).toUpperCase());
                                                board[index1][index2].setText(rackPanel.getCurrentPiece().getText());
                                                board[index1][index2].setFont(new Font("Serif", Font.BOLD, 34));
                                                scrabbleModel.removePlayerTile(rackPanel.getCurrentPiece().getText().charAt(0));
                                                rackPanel.getCurrentPiece().removeAll();
                                                rackPanel.resetCurrentPiece();
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
                                        board[index1][index2].setText(rackPanel.getCurrentPiece().getText());
                                        board[index1][index2].setFont(new Font("Serif", Font.BOLD, 34));
                                        rackPanel.getCurrentPiece().setVisible(false);
                                        scrabbleModel.removePlayerTile(rackPanel.getCurrentPiece().getText().charAt(0));
                                        rackPanel.getCurrentPiece().removeAll();
                                        rackPanel.resetCurrentPiece();
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
                    board[row][col].setFont(new Font("Serif", Font.BOLD, 10));
                    this.add(board[row][col]);
                }
            }
        }
    }

    /**
     * Nested RackPanel class that holds the players racks
     * Bottom of the ScrabbleBoardFrame panel.
     */
    public class RackPanel extends JPanel{
        private JLabel currentPiece;
        private ArrayList<Character> rack;
        private Stack<Character> redoRack = new Stack();


        /**
         * Method creates jpanels for all current players tiles, is called whenever turn is ended
         * needs to be function which is explicitly called
         * cannot be done in constructor because players will not have been initialized yet
         */
        public void setRackPanel(){
            removeAll();
            rack = scrabbleModel.getCurrentPlayerRack();
            super.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
            for(Character i : rack) {
                super.add(stylizeTile(i));
            }
            super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            super.setBackground(Color.PINK);
        }

        /**
         * Method to put tile back in rack after undo move
         */
        public void setTileInRack(){
            Character characterTile = scrabbleModel.rackTile;
            rack = scrabbleModel.getCurrentPlayerRack();
            redoRack.push(characterTile);
            super.add(stylizeTile(characterTile));
        }

        /**
         * Method to remove tile from rack after redo move
         */
        public void removeTileFromRack(){
            setRackPanel();
            redoRack.pop();
            for (char ch : redoRack){
                super.add(stylizeTile(ch));
            }
        }

        /**
         * Method for duplicated code in setRackPanel and setTileInRack methods
         *
         * @param c
         * @return
         */
        private JLabel stylizeTile(Character c){    //duplicated code
            JLabel tile = new JLabel(c.toString());
            tile.setOpaque(true);
            tile.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            tile.setFont(new Font("Serif", Font.BOLD, 34));
            tile.setBackground(Color.YELLOW);
            tile.setPreferredSize(new Dimension(50,50));
            tile.setHorizontalAlignment(SwingConstants.CENTER);
            tile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(currentPiece != null)
                        currentPiece.setBackground(Color.YELLOW);

                    tile.setBackground(Color.GREEN);
                    currentPiece = tile;
                }
            });
            return tile;
        }

        /**
         * Getter method to return the current piece
         *
         * @return currentPiece
         */
        public JLabel getCurrentPiece() {
            return currentPiece;
        }

        /**
         * Method to reset current piece
         */
        public void resetCurrentPiece() {
            currentPiece = null;
        }
    }


    @Override
    public void handlePlay() {
        if(scrabbleModel.endTurn()) {
            scrabbleModel.refillCurrentPlayer();
            scrabbleModel.nextPlayer();
            boardPanel.reset();
            rackPanel.setRackPanel();
            buttonPanel.showCurrentPlayer();
            scorePanel.updateScores();
        }
        else {
            scrabbleModel.resetBoard();
            scrabbleModel.resetCurrentPlayer();
            boardPanel.reset();
            rackPanel.setRackPanel();
        }
    }

    @Override
    public void handleClear() {
        scrabbleModel.resetBoard();
        scrabbleModel.resetCurrentPlayer();
        boardPanel.reset();
        rackPanel.setRackPanel();
    }

    @Override
    public void handlePass() {
        scrabbleModel.resetBoard();
        scrabbleModel.resetCurrentPlayer();
        scrabbleModel.nextPlayer();
        boardPanel.reset();
        rackPanel.setRackPanel();
        buttonPanel.showCurrentPlayer();
        scorePanel.updateScores();
    }

    @Override
    public void handleEndGame() {
        if(scrabbleModel.getTurn() > scrabbleModel.getPlayerCount()) {
            JDialog dialog = new JDialog();
            JPanel dialogPanel = new JPanel();
            dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

            JLabel info = new JLabel();
            info.setFont(new Font("Helvetica", Font.PLAIN, 24));
            info.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            info.setSize(100, 50);
            info.setBorder(new EmptyBorder(10, 40, 10, 40));

            //multiple winners
            if(scrabbleModel.getWinners().size() == 1) info.setText("<html>Congratulations<br/>"+ scrabbleModel.getPlayerName(scrabbleModel.getWinners().get(0))+"</html>");
            else {
                String text = "<html>Congratulations<br/>";
                for(int i : scrabbleModel.getWinners()) {
                    text+= scrabbleModel.getPlayerName(scrabbleModel.getWinners().get(i))+"<br/>";
                }
                text +="</html>";
                info.setText(text);
            }

            dialogPanel.add(info);
            JButton exitBtn = new JButton("Exit");
            exitBtn.setFocusPainted(false);
            exitBtn.setContentAreaFilled(false);
            exitBtn.setOpaque(true);
            exitBtn.setBackground(Color.PINK);
            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            dialogPanel.add(exitBtn);
            dialog.add(dialogPanel);
            dialog.setSize(300, 150);
            dialog.setUndecorated(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    @Override
    public void handleUndo() {
        scrabbleModel.undo();
        boardPanel.resetTile();
        rackPanel.setTileInRack();
        //if (scrabbleModel.undoStack.isEmpty()) undo.setEnabled(false);
    }

    @Override
    public void handleRedo() {
        scrabbleModel.redo();
        boardPanel.resetTile();
        rackPanel.removeTileFromRack();
        //if (scrabbleModel.redoStack.isEmpty()) redo.setEnabled(false);
    }
}