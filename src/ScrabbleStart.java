/**
 * Starting screen for the game.
 * Stylistic choice.
 * has an instance of the model also.
 *
 * @author Edidiong Okon (101204818)
 * @version December 9th, 2022.
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ScrabbleStart extends JPanel {
    private final ScrabbleModel scrabbleModel = ScrabbleModel.getInstance();

    public ScrabbleStart() {
        super();
        super.setLayout(new CardLayout());
        JPanel panel = this;

        //Starting Panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("SCRABBLE GAME!");
        title.setFont(new Font("TimesRoman", Font.BOLD, 70));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton newGame = new JButton("New Game");
        JButton exitGame = new JButton("Exit");
        JButton loadGame = new JButton("Load Game");

        newGame.addActionListener(new ActionListener() {
            @Override
            // Swap to gameCard when "New Game" is pressed
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) (panel.getLayout());
                layout.show(panel, "gameCard");
            }
        });

        exitGame.addActionListener(new ActionListener() {
            @Override
            // Exit when the exit button is pressed
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loadGame.addActionListener(new ActionListener() {
            @Override
            // Load a previous game when the load button is pressed
            public void actionPerformed(ActionEvent e) {
                int loadButton = JOptionPane.showConfirmDialog(loadGame, "Are You Sure You Want To Load The Saved Game?\nThe current game will be lost", "LOAD GAME", JOptionPane.YES_NO_OPTION);
                if (loadButton == JOptionPane.YES_OPTION) {
                    try {
                        ScrabbleModel.loadGame();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        panel1.add(title);
        panel1.add(Box.createVerticalStrut(40));
        panel1.add(stylizeButton(newGame));
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(stylizeButton(loadGame));
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(stylizeButton(exitGame));

        //Game init panel
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) (panel.getLayout());
                layout.show(panel, "startCard");
            }
        });

        //panel2.add(infoBox, BorderLayout.NORTH);
        panel2.add(stylizeButton(backButton), BorderLayout.SOUTH);

        //Player init panel
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        JLabel newGameInfo = new JLabel("<html>Please enter your player names below. The games can have 2-4 players </html>");
        newGameInfo.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        newGameInfo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        newGameInfo.setSize(400, 300);
        newGameInfo.setBorder(new EmptyBorder(10, 40, 10, 40));

        JTextField playerInfo = new JTextField("Enter Player Name");
        JButton back = new JButton("Back");
        JButton startGame = new JButton("Start Game");
        JButton addButton = new JButton("Add Player");

        playerInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButton.doClick();
            }
        });
        playerInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                playerInfo.select(0, playerInfo.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                playerInfo.select(0, 0);
            }
        });
        playerInfo.setPreferredSize(new Dimension(200, 30));

        startGame.setVisible(false);
        startGame.addActionListener(new ActionListener() {
            @Override
            // Tells the JFrame that it can begin the game
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(panel);
                frame.getContentPane().removeAll();
                frame.add(new ScrabbleBoardFrame());
                frame.revalidate();
                frame.repaint();
                frame.setSize(new Dimension(1280, 720));
                frame.setMinimumSize(frame.getSize());
                frame.setResizable(true);
                frame.setVisible(true);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            // Add designated player name to ScrabbleModel list of players
            public void actionPerformed(ActionEvent e) {
                if(scrabbleModel.getPlayerCount() < 3) {
                    scrabbleModel.addPlayer(playerInfo.getText());
                    playerInfo.setText("");
                }
                else if(scrabbleModel.getPlayerCount() == 3) {
                    scrabbleModel.addPlayer(playerInfo.getText());
                    playerInfo.setText("Maximum Player's Reached!");
                    playerInfo.setBackground(Color.GRAY);
                    addButton.setBackground(Color.GRAY);
                }

                if(scrabbleModel.getPlayerCount() > 1)
                    startGame.setVisible(true);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) (panel.getLayout());
                layout.show(panel, "startCard");

                // Reset Card 3
                scrabbleModel.resetPlayers();
                startGame.setVisible(false);
                playerInfo.setText("Player One Name");
                playerInfo.setBackground(Color.WHITE);
                addButton.setBackground(Color.WHITE);
            }
        });

        JPanel addPlayers = new JPanel();
        addPlayers.add(playerInfo);
        addPlayers.add(stylizeButton(addButton));

        JPanel lastButtons = new JPanel();
        lastButtons.add(stylizeButton(back));
        lastButtons.add(stylizeButton(startGame));

        panel3.add(newGameInfo);
        panel3.add(Box.createVerticalStrut(40));
        panel3.add(addPlayers);
        panel3.add(lastButtons);


        //Board selection Panel
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));




        // Adding panel to card layout
        super.add(panel1, "startCard");
        super.add(panel3, "gameCard");
    }

    /**
     * Makes the buttons all looks the same.
     * Stylistic choice.
     * @param jb
     * @return
     */
    private JButton stylizeButton(JButton jb) {
        jb.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        jb.setAlignmentX(JButton.CENTER_ALIGNMENT);
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setOpaque(true);
        jb.setBackground(Color.WHITE);
        jb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
        return jb;
    }

}
