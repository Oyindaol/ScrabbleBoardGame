/**
 * Starting screen for the game.
 * Stylistic choice.
 * has an instance of the model also.
 *
 * @author Edidiong Okon (101204818)
 * @version November 13, 2022
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ScrabbleStart extends JPanel {
    private final ScrabbleModel scrabbleModel = ScrabbleModel.getInstance();

    public ScrabbleStart() {
        super();
        super.setLayout(new CardLayout());


        JPanel cards = this;

        // Organizing Card 1 (Starting Panel)
        JPanel card1 = new JPanel();
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("SCRABBLE GAME!");
        title.setFont(new Font("TimesRoman", Font.BOLD, 70));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton newGame = new JButton("New Game");
        JButton exitGame = new JButton("Exit");

        newGame.addActionListener(new ActionListener() {
            @Override
            // Swap to gameCard when "New Game" is pressed
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, "gameCard");
            }
        });

        exitGame.addActionListener(new ActionListener() {
            @Override
            // Exit when the exit button is pressed
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        card1.add(title);
        card1.add(Box.createVerticalStrut(40));
        card1.add(stylizeButton(newGame));
        card1.add(Box.createVerticalStrut(20));
        //card1.add(stylizeButton(infoButton));
        card1.add(Box.createVerticalStrut(20));
        card1.add(stylizeButton(exitGame));

        // Organizing Card 2 (Game Information)
        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, "startCard");
            }
        });

        //card2.add(infoBox, BorderLayout.NORTH);
        card2.add(stylizeButton(backButton), BorderLayout.SOUTH);

        // Organizing Card 3 (Game Setup)
        JPanel card3 = new JPanel();
        card3.setLayout(new BoxLayout(card3, BoxLayout.Y_AXIS));

        JLabel newGameInfo = new JLabel("<html>Please enter your player names below. The games can have 2-4 players </html>");
        newGameInfo.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        newGameInfo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        newGameInfo.setSize(400, 300);
        newGameInfo.setBorder(new EmptyBorder(10, 40, 10, 40));

        JTextField playerInfo = new JTextField("Player One Name");
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
                JFrame frame = (JFrame) SwingUtilities.getRoot(cards);
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
                    playerInfo.setText("All Player Slots Filled");
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
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, "startCard");

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

        card3.add(newGameInfo);
        card3.add(Box.createVerticalStrut(40));
        card3.add(addPlayers);
        card3.add(lastButtons);

        // Adding cards to card layout
        super.add(card1, "startCard");
        super.add(card3, "gameCard");
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
