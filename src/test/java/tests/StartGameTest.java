package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import graphics.GameFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionListener;

public class StartGameTest {

    SOSGameLogic gameLogic;
    GameFrame gameFrame;
    Robot robot;

    @Before
    public void setUp() throws AWTException {
        gameLogic = new SOSGameLogic(Player.RED_PLAYER);
        gameFrame = new GameFrame();
        robot = new Robot();
    }

    //AC 3.1
    @Test
    public void testSuccessfulNewGame() {
        gameLogic.setSize(5);
        gameLogic.setGameMode(GameMode.SIMPLE);
        gameLogic.clearBoard();

        for(String[] row : gameLogic.getGameBoard()) {
            for(String value : row) {
                Assert.assertEquals(" ", value);
            }
        }
    }
    //AC 3.2
    @Test
    public void testInvalidNewGameNoGameMode() {
        gameLogic.setSize(5);
        gameLogic.clearBoard();
        gameLogic.setCurrentChoiceRed("S");
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Game mode cannot be null.", e.getMessage());
        }
    }
}
