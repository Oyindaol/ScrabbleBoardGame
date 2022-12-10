/**
 * Scrabble controller class to handle the action listeners
 *
 * @author Ese C. Iyamu (101081699)
 * @version December 9th, 2022.
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrabbleController implements ActionListener {
    private ScrabbleModel model;
    private ScrabbleBoardFrame scrabbleBoardFrame;
    private ScrabbleBoardFrame.BoardPanel boardPanel;
    private ScrabbleBoardFrame.ButtonPanel buttonPanel;
    private ScrabbleBoardFrame.RackPanel rackPanel;
    private ScrabbleBoardFrame.ScorePanel scorePanel;


    public ScrabbleController(ScrabbleModel model){
        this.model = model;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "play"){
            if(model.endTurn()) {
                model.refillCurrentPlayer();
                model.nextPlayer();
                boardPanel.reset();
                rackPanel.setRackPanel();
                buttonPanel.showCurrentPlayer();
                scorePanel.updateScores();
            }
            else {
                model.resetBoard();
                model.resetCurrentPlayer();
                boardPanel.reset();
                rackPanel.setRackPanel();
            }
        }
        else if(e.getActionCommand() == "pass"){
            model.resetBoard();
            model.resetCurrentPlayer();
            model.nextPlayer();
            boardPanel.reset();
            rackPanel.setRackPanel();
            buttonPanel.showCurrentPlayer();
            scorePanel.updateScores();
        }
        else if(e.getActionCommand() == "clear"){
            model.resetBoard();
            model.resetCurrentPlayer();
            boardPanel.reset();
            rackPanel.setRackPanel();
        }
        else if(e.getActionCommand() == "End Game"){
            if(model.getTurn() > model.getPlayerCount()) {
                JDialog dialog = new JDialog();
                JPanel dialogPanel = new JPanel();
                dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

                JLabel info = new JLabel();
                info.setFont(new Font("TimesRoman", Font.PLAIN, 24));
                info.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                info.setSize(100, 50);
                info.setBorder(new EmptyBorder(10, 40, 10, 40));

                //multiple winners
                if(model.getWinners().size() == 1) info.setText("<html>Congratulations<br/>"+ model.getPlayerName(model.getWinners().get(0))+"</html>");
                else {
                    String text = "<html>Congratulations<br/>";
                    for(int i : model.getWinners()) {
                        text+= model.getPlayerName(model.getWinners().get(i))+"<br/>";
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
        else if(e.getActionCommand() == "undo"){
            model.undo();
            boardPanel.resetTile();
            rackPanel.setTileInRack();
            //if (scrabbleModel.undoStack.isEmpty()) undo.setEnabled(false);
        }
        else if(e.getActionCommand() == "redo"){
            model.redo();
            boardPanel.resetTile();
            rackPanel.removeTileFromRack();
            //if (scrabbleModel.redoStack.isEmpty()) redo.setEnabled(false);
        }

    }
}
