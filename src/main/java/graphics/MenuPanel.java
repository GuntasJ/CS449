package graphics;

import gamelogic.SOSGameLogic;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final SOSGameLogic.GameState gameState;
    private final JButton playAgainButton;
    private final JButton endGameButton;
    private final JLabel gameStateLabel;
    MenuPanel(SOSGameLogic.GameState gameState) {
        this.gameState = gameState;
        this.playAgainButton = new JButton("Play Again");
        this.endGameButton = new JButton("End Game");
        this.gameStateLabel = new JLabel("", SwingConstants.CENTER);

        setPreferredSize(new Dimension(300, 200));
        setUpLabels();
        setUpButtons();
    }

    private void setUpButtons() {
        playAgainButton.setPreferredSize(new Dimension(100, 50));
        endGameButton.setPreferredSize(new Dimension(100, 50));

        playAgainButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
            MenuFrame menuFrame = (MenuFrame) SwingUtilities.getWindowAncestor(this);
            menuFrame.dispose();
        });
        endGameButton.addActionListener(e -> System.exit(0));

        playAgainButton.setFocusPainted(false);
        endGameButton.setFocusPainted(false);

        add(playAgainButton);
        add(endGameButton);
    }

    private void setUpLabels() {
        String label = switch (gameState) {
            case BLUE_WON -> "Blue Player Won!";
            case RED_WON -> "Red Player Won!";
            case TIE -> "The Game Is A Tie!";
            default -> "";
        };
        gameStateLabel.setPreferredSize(new Dimension(300, 100));
        gameStateLabel.setText(label);

        add(gameStateLabel);
    }


}
