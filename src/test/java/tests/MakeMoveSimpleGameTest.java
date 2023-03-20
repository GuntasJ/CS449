package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MakeMoveSimpleGameTest {

    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = new SOSGameLogic(3, Player.RED_PLAYER, GameMode.SIMPLE);
    }

    //AC 4.1
    @Test
    public void testSuccessfulMoveInSimpleGame() {
        String selection = "S";
        gameLogic.setCurrentChoice(selection, Player.RED_PLAYER);
        gameLogic.makeMove(1, 1);
        Assert.assertEquals("S", gameLogic.getGameBoard()[1][1]);
    }

    //AC 4.2
    @Test
    public void testInvalidMoveOnNonEmptyTileInSimpleGame() {
        String selection = "S";
        gameLogic.setCurrentChoice(selection, Player.RED_PLAYER);
        gameLogic.makeMove(1, 1);
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Choice must be placed on empty square.", e.getMessage());
        }
    }

    //AC 4.3
    @Test
    public void testInvalidMoveWithNoSelectionOnValidTileInSimpleGame() {
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Selection cannot be null.", e.getMessage());
        }
    }
    //AC 4.4
    @Test
    public void testInvalidMoveWithNoSelectionOnNonEmptyTileInSimpleGame() {
        testSuccessfulMoveInSimpleGame();
        try {
            gameLogic.setCurrentChoice(" ", Player.BLUE_PLAYER);
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Current choice must be S or O", e.getMessage());
        }
    }
}
