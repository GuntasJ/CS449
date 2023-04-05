package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import gamelogic.Tile;
import graphics.GameFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class StartGameTest {

    SOSGameLogic gameLogic;
    GameFrame gameFrame;
    Robot robot;

    @Before
    public void setUp() throws AWTException {
        gameLogic = SOSGameLogic.newBuilder().build();
        gameFrame = new GameFrame();
        robot = new Robot();
    }

    //AC 3.1
    @Test
    public void testSuccessfulNewGame() {
        gameLogic.setSize(5);
        gameLogic.setGameMode(GameMode.SIMPLE);
        gameLogic.clearBoard();

        for(Tile[] row : gameLogic.getGameBoard()) {
            for(Tile value : row) {
                Assert.assertEquals(" ", value.getSelection());
            }
        }
    }
    //AC 3.2
    @Test
    public void testInvalidNewGameNoGameMode() {
        gameLogic.setSize(5);
        gameLogic.clearBoard();
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.setGameMode(null);
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Game mode cannot be null.", e.getMessage());
        }
    }
}
