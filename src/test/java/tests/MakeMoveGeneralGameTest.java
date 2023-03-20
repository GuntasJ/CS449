package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MakeMoveGeneralGameTest {

    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = new SOSGameLogic(3, Player.RED_PLAYER, GameMode.GENERAL);
    }

    //AC 6.1
    @Test
    public void testSuccessfulMoveInGeneralGame() {
        String selection = "S";
        gameLogic.setCurrentChoice(selection, Player.RED_PLAYER);
        gameLogic.makeMove(1, 1);
        Assert.assertEquals("S", gameLogic.getGameBoard()[1][1]);
    }

    //AC 6.3
    @Test
    public void testInvalidMoveOnNonEmptyTileInGeneralGame() {
        String selection = "S";
        gameLogic.setCurrentChoice(selection, Player.RED_PLAYER);
        gameLogic.makeMove(1, 1);
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Choice must be placed on empty square.", e.getMessage());
        }
    }

    //AC 6.4
    @Test
    public void testInvalidMoveWithNoSelectionOnValidTileInGeneralGame() {
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Selection cannot be null.", e.getMessage());
        }
    }
    //AC 6.5
    @Test
    public void testInvalidMoveWithNoSelectionOnNonEmptyTileInGeneralGame() {
        testSuccessfulMoveInGeneralGame();
        try {
            gameLogic.setCurrentChoice(" ", Player.BLUE_PLAYER);
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Current choice must be S or O", e.getMessage());
        }
    }

}